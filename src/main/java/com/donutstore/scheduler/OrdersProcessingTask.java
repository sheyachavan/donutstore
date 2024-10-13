package com.donutstore.scheduler;


import com.donutstore.model.Order;
import com.donutstore.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrdersProcessingTask
{
    private static final Logger LOG = LoggerFactory.getLogger(OrdersProcessingTask.class);

    @Autowired
    OrderService orderService;

    @Scheduled(cron = "* 0/6 * * * *")
    public void scheduleTask()
    {
        final List<Order> orders = orderService.nextDelivery();

        if (orders == null || orders.isEmpty())
            LOG.warn("Error in retrieving  orders ");

        else
        {
            orders.forEach(order -> {
                LOG.info("Processing order : "+ order.toString());

                orderService.deleteOrderByCustomerId(order.getCustomer().getCustId());
                orderService.clearOrderAfterProcess(order);
            });
        }
    }
}
