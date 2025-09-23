package com.example.order_service.services;

import com.example.events.dtos.*;
import com.example.order_service.entity.Order;
import com.example.order_service.repository.OrderRepository;
import com.google.common.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@KafkaListener(topics="order-service")
public class OrderService {

    @Autowired
    private Cache<String, CreateOrder> guavaCache;

    @Autowired
    private KafkaTemplate<String, OrderResponse> responsetemplate;

    @Autowired
    private OrderRepository orderRepository;

    @KafkaHandler
    public void CreateOrderListener(CreateOrder response)
    {
        guavaCache.put(response.getCorrelationId(), response);
    }

    @KafkaHandler
    public void CancelEventListener(CancelOrder response)
    {
        String id = response.getCorrelationId();
        guavaCache.invalidate(id);
    }

    @KafkaHandler
    public void FinalizeEventListener(FinalizeOrder response)
    {
        CreateOrder new_order = guavaCache.asMap().get(response.getCorrelationid());

        Order order = new Order(new_order.getClientid(),new_order.getItemIds(), new_order.getQuantity(), new_order.getAmount());
        order.setStatus(ORDER_STATUS.ORDER_COMPLETED.toString());
        orderRepository.save(order);

        System.out.println("Order Saved Successfully");
    }
}
