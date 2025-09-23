package com.example.events.dtos;

import java.time.Instant;

public class CreateOrder {
    private String correlationId;
    private Integer[] itemIds;
    private Integer[] quantity;
    private Instant timestamp;
    private Integer clientid;
    private Integer amount;

    public CreateOrder(String correlationId, Integer[] itemIds, Integer[] quantity,Integer clientid,Integer amount) {
        this.correlationId = correlationId;
        this.itemIds = itemIds;
        this.quantity = quantity;
        this.timestamp = Instant.now();
        this.clientid = clientid;
        this.amount = amount;
    }
    public CreateOrder() {}
    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public Integer[] getItemIds() {
        return itemIds;
    }

    public void setItemIds(Integer[] itemIds) {
        this.itemIds = itemIds;
    }

    public Integer[] getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer[] quantity) {
        this.quantity = quantity;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getClientid() {
        return clientid;
    }

    public void setClientid(Integer clientid) {
        this.clientid = clientid;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
