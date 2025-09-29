package com.example.events.dtos;

import java.time.Instant;

public class DeductItems {
    private String correlationid;
    private String eventype;
    private Instant timestamp;
    public DeductItems(String correlationid) {
        this.correlationid = correlationid;
        this.eventype=DeductItems.class.getSimpleName();
        this.timestamp=Instant.now();
    }
    public DeductItems() {}

    public String getCorrelationid() {
        return correlationid;
    }

    public void setCorrelationid(String correlationid) {
        this.correlationid = correlationid;
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
