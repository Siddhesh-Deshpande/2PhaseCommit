package com.example.events.dtos;

public class ReleaseItems {
    private String correlationid;

    public ReleaseItems(String correlationid) {
        this.correlationid = correlationid;
    }
    public ReleaseItems() {}
    public String getCorrelationid() {
        return correlationid;
    }

    public void setCorrelationid(String correlationid) {
        this.correlationid = correlationid;
    }
}
