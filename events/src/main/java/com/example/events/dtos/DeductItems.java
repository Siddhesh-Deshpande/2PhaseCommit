package com.example.events.dtos;

public class DeductItems {
    private String correlationid;

    public DeductItems(String correlationid) {
        this.correlationid = correlationid;
    }
    public DeductItems() {}

    public String getCorrelationid() {
        return correlationid;
    }

    public void setCorrelationid(String correlationid) {
        this.correlationid = correlationid;
    }
}
