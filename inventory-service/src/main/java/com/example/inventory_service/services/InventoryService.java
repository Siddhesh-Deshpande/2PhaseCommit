package com.example.inventory_service.services;

import com.example.events.dtos.DeductItems;
import com.example.events.dtos.ReleaseItems;
import com.example.events.dtos.ReserveItems;
import com.example.inventory_service.entity.Item;
import com.example.inventory_service.repository.ItemRepository;
import com.google.common.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@KafkaListener(topics="inventory-service")
public class InventoryService {

    @Autowired
    private Cache<String, ReserveItems> guavaCache;
    @Autowired
    private ItemRepository itemRepository;

    @KafkaHandler
    public void ReserveItems(ReserveItems items)
    {
        guavaCache.put(items.getCorrelationId(), items);
    }
    @KafkaHandler
    public void deductitems(DeductItems deductItems)
    {
        if(guavaCache.asMap().containsKey(deductItems.getCorrelationid()))
        {
            guavaCache.asMap().get(deductItems.getCorrelationid()).setStatus(1);
        }
    }
    @KafkaHandler
    public void releaseitems(ReleaseItems items)
    {
       if(guavaCache.asMap().containsKey(items.getCorrelationid()))
       {
           guavaCache.invalidate(items.getCorrelationid());
       }
    }
}
