package com.example.events.dtos;

public class OrderResponse extends Response {

    public OrderResponse(String correlationId, boolean status) {
        super(correlationId, status);
    }

}
