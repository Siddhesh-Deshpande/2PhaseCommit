package com.example.coordinator.services;

import com.example.coordinator.entity.Order;
import com.example.events.dtos.InventoryResponse;
import com.example.events.dtos.OrderResponse;
import com.example.events.dtos.PaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.google.common.cache.Cache;

@Service
public class CoordinatorService {
    private Cache<String, Order> guavaCache;

    @Autowired
    public CoordinatorService(Cache<String, Order> guavaCache) {
        this.guavaCache = guavaCache;
    }
    @KafkaListener(topics="order-service")
    public void OrderListener(OrderResponse response)
    {
        guavaCache.asMap().get(response.getCorrelationId()).getResponses().put(0,response.getStatus());
    }
    @KafkaListener(topics="inventory-service")
    public void InventoryListener(InventoryResponse response)
    {
        guavaCache.asMap().get(response.getCorrelationId()).getResponses().put(1,response.getStatus());
    }
    @KafkaListener(topics="payment-service")
    public void PaymentListener(PaymentResponse response)
    {
        guavaCache.asMap().get(response.getCorrelationId()).getResponses().put(2,response.getStatus());
    }


}
