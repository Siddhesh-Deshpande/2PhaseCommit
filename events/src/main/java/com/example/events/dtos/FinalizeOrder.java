package com.example.events.dtos;

import java.time.Instant;

public class FinalizeOrder {
    private String correlationid;
    private String eventype;
    private Instant timestamp;
    private Integer order_id;
    public FinalizeOrder(String correlationid, Integer id) {
        this.correlationid = correlationid;
        this.eventype=FinalizeOrder.class.getSimpleName();
        this.timestamp=Instant.now();
        this.order_id = id;

    }
    public FinalizeOrder() {}

    public String getCorrelationid() {
        return correlationid;
    }

    public void setCorrelationid(String correlationid) {
        this.correlationid = correlationid;
    }

    public Integer getOrder_id() { return order_id; }
    public void setOrder_id(Integer order_id) { this.order_id = order_id; }

    public String getEventype() {
        return eventype;
    }

    public void setEventype(String eventype) {
        this.eventype = eventype;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
