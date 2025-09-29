package com.example.events.dtos;

import java.time.Instant;

public class CancelOrder {
    private String correlationId;
    private String eventype;
    private Instant timestamp;
    public CancelOrder(String correlationId) {
        this.correlationId = correlationId;
        this.eventype=CancelOrder.class.getSimpleName();
        this.timestamp = Instant.now();
    }
    public CancelOrder() {}

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

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
