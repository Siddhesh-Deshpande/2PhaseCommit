package com.example.order_service.scheduler;

import com.example.events.dtos.CreateOrder;
import com.example.events.dtos.OrderResponse;
import com.example.order_service.entity.Order;
import com.example.order_service.repository.OrderRepository;
import com.google.common.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component("orderTaskScheduler")
public class TaskScheduler {

    @Autowired
    private Cache<String, CreateOrder> guavaCache;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private KafkaTemplate<String, OrderResponse> kafkaTemplate;

    @Scheduled(fixedDelay = 1000)
    public void runjob()
    {
        HashSet<String> keys = new HashSet<>();
        for(String key : guavaCache.asMap().keySet())
        {
            CreateOrder order = guavaCache.asMap().get(key);
            Order insertorder = new Order(order.getClientid(),order.getItemIds(), order.getQuantity(), order.getAmount());
            orderRepository.save(insertorder);
            kafkaTemplate.send("coor-service",new OrderResponse(order.getCorrelationId(),true));
            keys.add(key);
        }
        for(String key : keys)
        {
            guavaCache.invalidate(key);
        }
    }
}
