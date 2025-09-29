package com.example.events.dtos;

import java.time.Instant;

public class ReleaseFunds {
    private String correlationid;
    private String eventype;
    private Instant timestamp;
    public ReleaseFunds(String correlationid) {
        this.correlationid = correlationid;
        this.eventype = ReleaseFunds.class.getSimpleName();
        this.timestamp = Instant.now();
    }
    public ReleaseFunds() {}

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
