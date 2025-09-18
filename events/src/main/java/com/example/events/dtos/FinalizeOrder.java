package com.example.events.dtos;

public class FinalizeOrder {
    private String correlationid;

    public FinalizeOrder(String correlationid) {
        this.correlationid = correlationid;
    }

    public String getCorrelationid() {
        return correlationid;
    }

    public void setCorrelationid(String correlationid) {
        this.correlationid = correlationid;
    }
}
