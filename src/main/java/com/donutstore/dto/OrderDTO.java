package com.donutstore.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;


public class OrderDTO
{

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @NotNull
    @JsonProperty("orderId")
    private Integer orderId;

    @NotNull
    @JsonProperty("quantity")
    private Integer quantity;

    @NotNull
    @JsonProperty("customerId")
    private Integer customerId;

    private Long creationTime;

    private Integer waitTime;

    private Integer position;


    public Long getCreationTime()
    {
        return creationTime;
    }

    public OrderDTO setCreationTime(Long creationTime)
    {
        this.creationTime = creationTime;
        return this;
    }

    public Integer getWaitTime()
    {
        return waitTime;
    }

    public OrderDTO setWaitTime(Integer waitTime)
    {
        this.waitTime = waitTime;
        return this;
    }

    public Integer getPosition()
    {
        return position;
    }

    public OrderDTO setPosition(Integer position)
    {
        this.position = position;
        return this;
    }

    public Integer getQuantity()
    {
        return quantity;
    }

    public OrderDTO setQuantity(Integer quantity)
    {
        this.quantity = quantity;
        return this;
    }

    public Integer getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(Integer customerId)
    {
        this.customerId = customerId;
    }
}
