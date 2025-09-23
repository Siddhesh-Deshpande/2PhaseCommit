package com.example.coordinator.services;

import com.example.coordinator.entity.Order;
import com.example.events.dtos.InventoryResponse;
import com.example.events.dtos.OrderResponse;
import com.example.events.dtos.PaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.google.common.cache.Cache;

@Service
@KafkaListener(topics="coor-service")
public class CoordinatorService {
    private Cache<String, Order> guavaCache;

    @Autowired
    public CoordinatorService(Cache<String, Order> guavaCache) {
        this.guavaCache = guavaCache;
    }
    @KafkaHandler
    public void OrderListener(OrderResponse response)
    {
        System.out.println("Order Response Recieved from order service");
        guavaCache.asMap().get(response.getCorrelationId()).getResponses().put(0,response.getStatus());
    }
    @KafkaHandler
    public void InventoryListener(InventoryResponse response)
    {
        System.out.println("Inventory Response Recieved from inventory service");
        guavaCache.asMap().get(response.getCorrelationId()).getResponses().put(1,response.getStatus());
    }
    @KafkaHandler
    public void PaymentListener(PaymentResponse response)
    {
        System.out.println("Payment Response Recieved from payment service");
        guavaCache.asMap().get(response.getCorrelationId()).getResponses().put(2,response.getStatus());
    }


}
