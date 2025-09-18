package com.example.events.dtos;

public class ChargeMoney {
    private String correlationid;

    public ChargeMoney(String correlationid) {
        this.correlationid = correlationid;
    }

    public String getCorrelationid() {
        return correlationid;
    }

    public void setCorrelationid(String correlationid) {
        this.correlationid = correlationid;
    }
}
