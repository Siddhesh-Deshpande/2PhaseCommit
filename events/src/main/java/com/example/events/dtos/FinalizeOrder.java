package com.example.events.dtos;

public class FinalizeOrder {
    private String correlationid;
    private Integer id;
    public FinalizeOrder(String correlationid, Integer id) {
        this.correlationid = correlationid;
        this.id = id;
    }
    public FinalizeOrder() {}

    public String getCorrelationid() {
        return correlationid;
    }

    public void setCorrelationid(String correlationid) {
        this.correlationid = correlationid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
