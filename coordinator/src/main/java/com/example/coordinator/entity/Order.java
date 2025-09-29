package com.example.coordinator.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Order {

    private Integer clientid;
    private List<Item> items;  // <-- Matches the JSON
    private Integer phase;     // 0 means order just arrived
    private HashMap<Integer, Boolean> response;
    private Integer order_id;

    public Order(Integer clientid,List<Item> items) {
        this.phase = 0;
        this.response = new HashMap<>();
        this.order_id = -1;
        this.clientid = clientid;
        this.items = items;

    }
    public Integer getClientid() {
        return clientid;
    }

    public void setClientid(Integer clientid) {
        this.clientid = clientid;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Integer getPhase() {
        return phase;
    }

    public void setPhase(Integer phase) {
        this.phase = phase;
    }

    public HashMap<Integer, Boolean> getResponses() {
        return response;
    }

    public void setResponse(HashMap<Integer, Boolean> response) {
        this.response = response;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }


}
