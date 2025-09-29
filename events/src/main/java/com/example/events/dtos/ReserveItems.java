package com.example.events.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReserveItems {
    private String correlationId;
    private String eventype;
    private Instant timestamp;
    private Integer[] itemIds;
    private Integer[] quantity;
    private Integer Status;

    // Constructor
    public ReserveItems(String correlationId, Integer[] itemIds, Integer[] quantity) {
        this.correlationId = correlationId;
        this.eventype=ReserveItems.class.getSimpleName();
        this.timestamp = Instant.now();
        this.itemIds = itemIds;
        this.quantity = quantity;
        // If timestamp is null, set it to current time
//        this.Status = 0;
    }

    public String getEventype() {
        return eventype;
    }

    public void setEventype(String eventype) {
        this.eventype = eventype;
    }

    public ReserveItems(){}

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
