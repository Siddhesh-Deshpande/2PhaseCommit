package com.example.events.dtos;

public abstract class Response {
    String correlationId;
    boolean status;

    public Response(String correlationId, boolean status) {
        this.correlationId = correlationId;
        this.status = status;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
