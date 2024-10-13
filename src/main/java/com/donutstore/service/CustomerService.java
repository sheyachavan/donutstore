package com.donutstore.service;

import com.donutstore.repository.CustomerRepository;
import com.donutstore.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService
{

    @Autowired
    CustomerRepository customerRepository;

    public Customer addCustomer(Customer customer)
    {
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Integer custId)
    {
        customerRepository.deleteById(custId);
    }

    public Optional<Customer> getCustomer(Integer custId)
    {
        return customerRepository.findById(custId);
    }
}
