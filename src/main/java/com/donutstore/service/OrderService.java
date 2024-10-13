package com.donutstore.service;

import com.donutstore.constants.Constants;
import com.donutstore.repository.OrderRepository;
import com.donutstore.exception.DonutStoreException;
import com.donutstore.model.Customer;
import com.donutstore.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class OrderService
{

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerService customerService;

    private static final LinkedList<Order> orderQueue = new LinkedList<>();

    public Order addOrder(final Order order) {
        final Optional<Order> alreadyPresent = orderRepository.getOrderByCustomerId(order.getCustomer().getCustId());

        if (alreadyPresent.isPresent())
            throw new DonutStoreException(Constants.EC_ORDER_IN_PROCESS, Constants.EM_ORDER_IN_PROCESS);

        final Optional<Customer> customerPresent = customerService.getCustomer(order.getCustomer().getCustId());

        if (customerPresent.isPresent()) {
            throw new DonutStoreException(Constants.EC_EXISTING_CUSTOMER, Constants.EM_EXISTING_CUSTOMER);
        }
        order.setCustomer(order.getCustomer());
        return orderRepository.save(order);
    }
    public Order findByOrderId(final Integer orderId)
    {
        final Optional<Order> orderOp = orderRepository.findById(orderId);

        if (orderOp.isEmpty())
            throw new DonutStoreException(Constants.EC_ORDER_NOT_EXISTS, Constants.EM_ORDER_NOT_EXISTS);

        return orderOp.get();
    }

    public List<Order> fetchOrdersBySubmission()
    {
        final List<Order> orders = orderRepository.fetchOrdersBySubmission();

        if (orders.isEmpty())
            throw new DonutStoreException(Constants.EC_ORDER_NOT_EXISTS, Constants.EM_ORDER_NOT_EXISTS);

        final AtomicInteger waitTime = new AtomicInteger();
        final AtomicInteger position = new AtomicInteger();

        orders.forEach(o ->{
                    o.setWaitTime(waitTime.incrementAndGet());
                    o.setPosition(position.incrementAndGet());
                }
        );

        orderQueue.clear();
        orderQueue.addAll(orders);

        return orderQueue;
    }


    public Order getQueuePositionAndWaitTime(final Integer customerId)
    {
        return orderQueue.stream()
                .filter(o -> o.getCustomer().getCustId().equals(customerId))
                .findFirst()
                .orElseThrow(() -> new DonutStoreException(Constants.EC_ORDER_NOT_EXISTS, Constants.EM_ORDER_NOT_EXISTS));
    }

    public List<Order> getAllOrdersAndWaitTime()
    {
        return orderQueue;
    }

    public List<Order> nextDelivery()
    {
        if (orderQueue.isEmpty())
            throw new DonutStoreException(Constants.EC_ORDER_NOT_EXISTS, Constants.EM_ORDER_NOT_EXISTS);

        final List<Order> orders = new ArrayList<>();
        synchronized (this)
        {
            int quantity = 0;

            while(quantity < 50)
            {
                if (orderQueue.size() > 0) {
                    final Order order = orderQueue.peek();
                    quantity += order.getQuantity();
                    if (quantity <= 50) {
                        orders.add(orderQueue.poll());
                    }
                }
                else break;
            }
        }
        return orders;
    }

    public int deleteOrderByCustomerId(final Integer customerId)
    {
        int res = orderRepository.deleteOrder(customerId);

        if (res == 0)
            throw new DonutStoreException(Constants.EC_ORDER_NOT_EXISTS, Constants.EM_ORDER_NOT_EXISTS);

        customerService.deleteCustomer(customerId);

        return res;
    }

    public synchronized void clearOrderAfterProcess(final Order order)
    {
        orderQueue.remove(order);

        orderQueue.forEach(o ->
                    o.setWaitTime(o.getWaitTime() - 1)
                            .setPosition(o.getPosition() - 1)
                );
    }
}
