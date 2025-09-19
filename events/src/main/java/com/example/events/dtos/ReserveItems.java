package com.example.events.dtos;

import java.time.Instant;

public class ReserveItems {
    private String correlationId;
    private Integer[] itemIds;
    private Integer[] quantity;
    private Instant timestamp;
    private Integer Status;

    // Constructor
    public ReserveItems(String correlationId, Integer[] itemIds, Integer[] quantity) {
        this.correlationId = correlationId;
        this.itemIds = itemIds;
        this.quantity = quantity;
        // If timestamp is null, set it to current time
        this.timestamp = Instant.now();
        this.Status = 0;
    }

    // Getters and setters
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

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }
}
