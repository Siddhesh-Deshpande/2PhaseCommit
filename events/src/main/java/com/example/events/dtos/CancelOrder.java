package com.example.events.dtos;

public class CancelOrder {
    private String correlationId;

    public CancelOrder(String correlationId) {
        this.correlationId = correlationId;
    }
    public CancelOrder() {}

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }
}
