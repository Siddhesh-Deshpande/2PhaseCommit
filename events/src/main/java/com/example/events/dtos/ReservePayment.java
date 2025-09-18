package com.example.events.dtos;

import java.time.Instant;

public class ReservePayment {
    private String correlationId;
    private Integer clientid;
    private Integer amount;
    private Instant timestamp;

    public ReservePayment(String correlationId, Integer clientid, Integer amount) {
        this.correlationId = correlationId;
        this.clientid = clientid;
        this.amount = amount;
        this.timestamp = Instant.now();
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getClientid() {
        return clientid;
    }

    public void setClientid(Integer clientid) {
        this.clientid = clientid;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }
}
