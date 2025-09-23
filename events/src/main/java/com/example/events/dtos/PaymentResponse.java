package com.example.events.dtos;

public class PaymentResponse extends Response {
    public PaymentResponse(String correlationId, boolean status) {
        super(correlationId, status);
    }
    public PaymentResponse(){}
}
