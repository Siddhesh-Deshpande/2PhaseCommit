package com.example.coordinator.cache;

import com.example.coordinator.entity.Order;
import com.example.events.dtos.*;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {

    @Autowired
    private KafkaTemplate<String, CancelOrder> ordertemplate;

    @Autowired
    private KafkaTemplate<String, ReleaseItems> inventorytemplate;

    @Autowired
    private KafkaTemplate<String, ReleaseFunds> paymenttemplate;


    @Bean
    public Cache<String, Order> guavaCache() {
        return CacheBuilder.newBuilder()
                .expireAfterWrite(30, TimeUnit.SECONDS)  // entry expires 10 sec after write
                .removalListener((RemovalNotification<String,Order> notification) -> {
                    Order order = notification.getValue();
                    HashMap<Integer,Boolean> map = order.getResponses();
                    for(Integer key : map.keySet()){
                        if(map.get(key))
                        {
                            if(key==0)
                            {
                                ordertemplate.send("order-service",new CancelOrder(notification.getKey()));
                            }
                            else if(key==1)
                            {
                                inventorytemplate.send("inventory-service",new ReleaseItems(notification.getKey()));
                            }
                            else
                            {
                                paymenttemplate.send("payment-service",new ReleaseFunds(notification.getKey()));
                            }
                        }
                    }

                })
                .build();
    }
}
