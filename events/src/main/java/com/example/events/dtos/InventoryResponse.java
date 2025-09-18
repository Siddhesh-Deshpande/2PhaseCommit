package com.example.events.dtos;

public class InventoryResponse extends  Response{


    public InventoryResponse(String correlationId, boolean status) {
        super(correlationId, status);
    }
}
