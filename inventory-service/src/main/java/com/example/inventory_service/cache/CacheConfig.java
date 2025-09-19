package com.example.inventory_service.cache;

import com.example.inventory_service.entity.ReservedItems;
import com.example.inventory_service.repository.ReserveItemRepository;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.common.cache.Cache;
import org.springframework.stereotype.Component;
import com.example.events.dtos.ReserveItems;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {
    @Autowired
    private ReserveItemRepository reserveItemRepository;

    @Bean
    public Cache<String, ReserveItems> guavaCache() {
        return CacheBuilder.newBuilder()
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .removalListener((RemovalNotification<String,ReserveItems> notification) -> {
                    ReserveItems item =  notification.getValue();
                    if(item.getStatus()==1)
                    {
                        Integer[] quantity = item.getQuantity();
                        Integer[] item_id = item.getItemIds();
                        for(int i=0;i<quantity.length;i++)
                        {
                            ReservedItems reservedItems = reserveItemRepository.findById(item_id[i]).orElse(null);
                            reservedItems.setReserved_quantity(reservedItems.getReserved_quantity()-quantity[i]);
                            reserveItemRepository.save(reservedItems);
                        }
                    }

                })
                .build();
    }
}
