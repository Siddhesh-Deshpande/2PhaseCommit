package com.example.events.dtos;

public class ReleaseFunds {
    private String correlationid;

    public ReleaseFunds(String correlationid) {
        this.correlationid = correlationid;
    }

    public String getCorrelationid() {
        return correlationid;
    }

    public void setCorrelationid(String correlationid) {
        this.correlationid = correlationid;
    }
}
