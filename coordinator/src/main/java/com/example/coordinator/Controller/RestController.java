package com.example.coordinator.Controller;

import com.example.coordinator.entity.Order;
import com.google.common.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.UUID;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/ecomm")
public class RestController {

    @Autowired
    private Cache<String, Order> guavaCache; //usually a redis cache in real world

    @PostMapping("/order")
    public void SendOrder(@RequestBody Order order) //in real world just put in redis cache
    {
        guavaCache.put(UUID.randomUUID().toString(), order);
    }

}
