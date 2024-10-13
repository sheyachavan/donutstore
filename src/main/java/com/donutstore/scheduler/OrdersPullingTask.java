package com.donutstore.scheduler;


import com.donutstore.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OrdersPullingTask
{
    private static final Logger LOG = LoggerFactory.getLogger(OrdersPullingTask.class);

    @Autowired
    OrderService orderService;

    @Scheduled(cron = "* 0/5 * * * *")
    public void scheduleTask()
    {
        orderService.fetchOrdersBySubmission();
        LOG.info("Orders queue updated...");
    }
}
