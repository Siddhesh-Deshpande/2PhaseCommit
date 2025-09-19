package com.example.order_service.services;

import com.example.events.dtos.CancelOrder;
import com.example.events.dtos.CreateOrder;
import com.example.events.dtos.FinalizeOrder;
import com.example.events.dtos.OrderResponse;
import com.example.order_service.entity.Order;
import com.example.order_service.repository.OrderRepository;
import com.google.common.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private Cache<String, CreateOrder> guavaCache;

    @Autowired
    private KafkaTemplate<String, OrderResponse> responsetemplate;

    @Autowired
    private OrderRepository orderRepository;

    @KafkaListener(topics = "order-service")
    public void CreateOrderListener(CreateOrder response)
    {
        guavaCache.put(response.getCorrelationId(), response);
    }

    @KafkaListener(topics = "order-service")
    public void CancelEventListener(CancelOrder response)
    {
        String id = response.getCorrelationId();
        guavaCache.invalidate(id);
    }

    @KafkaListener(topics = "order-service")
    public void FinalizeEventListener(FinalizeOrder response)
    {
        CreateOrder new_order = guavaCache.asMap().get(response.getCorrelationid());
        Order order = new Order(new_order.getClientid(),new_order.getItemIds(), new_order.getQuantity(), new_order.getAmount());

        orderRepository.save(order);

        System.out.println("Order Saved Successfully");
    }
}
