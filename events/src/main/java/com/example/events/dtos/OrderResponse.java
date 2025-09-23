package com.example.events.dtos;

import org.springframework.core.annotation.Order;

public class OrderResponse extends Response {

    public OrderResponse(String correlationId, boolean status) {
        super(correlationId, status);
    }
    public OrderResponse(){}

}
