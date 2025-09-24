package com.example.coordinator.entity;

import java.util.ArrayList;
import java.util.HashMap;

public class Order {
    private Integer clientid;
    private Integer[] itemids;
    private Integer[] quantities;
    private Integer[] prices;
    private Integer phase;//0 means order just arrived and we haven't processed yet.
    private HashMap<Integer,Boolean> response;
    private Integer order_id;
    public Order(Integer clientid, Integer[] itemids, Integer[] quantities, Integer[] prices) {
        this.clientid = clientid;
        this.itemids = itemids;
        this.quantities = quantities;
        this.prices = prices;
        this.response = new HashMap<>();
        this.phase = 0;
        this.order_id = -1;
    }

    public Integer getPhase() {
        return phase;
    }

    public void setPhase(Integer phase) {
        this.phase = phase;
    }

    public Integer getClientid() {
        return clientid;
    }

    public void setClientid(Integer clientid) {
        this.clientid = clientid;
    }

    public Integer[] getItemids() {
        return itemids;
    }

    public void setItemids(Integer[] itemids) {
        this.itemids = itemids;
    }

    public Integer[] getQuantities() {
        return quantities;
    }

    public void setQuantities(Integer[] quantities) {
        this.quantities = quantities;
    }

    public Integer[] getPrices() {
        return prices;
    }

    public void setPrices(Integer[] prices) {
        this.prices = prices;
    }

    public HashMap<Integer,Boolean> getResponses() {
        return response;
    }

    public void setResponses(HashMap<Integer,Boolean> responses) {
        this.response = responses;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }
}
