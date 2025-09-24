package com.example.inventory_service.scheduler;

import com.example.events.dtos.InventoryResponse;
import com.example.events.dtos.ReserveItems;
import com.example.inventory_service.entity.Item;
import com.example.inventory_service.entity.ReservedItems;
import com.example.inventory_service.repository.ItemRepository;
import com.example.inventory_service.repository.ReserveItemRepository;
import com.google.common.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component("inventoryTaskScheduler")
public class TaskScheduler {
    @Autowired
    private Cache<String, ReserveItems> guavaCache;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ReserveItemRepository reserveItemRepository;
    @Autowired
    private KafkaTemplate<String, InventoryResponse> kafkaTemplate;

    @Scheduled(fixedDelay = 1000)//decide what time to keep in future
    public void ReserveItems()
    {

        HashSet<String> keys = new HashSet<>();
        for (String key : guavaCache.asMap().keySet())
        {
            ReserveItems task =  guavaCache.asMap().get(key);
            Integer[] item_ids = task.getItemIds();
            Integer[] quantity = task.getQuantity();
            if(task.getStatus()==0) // perform reservation
            {
                boolean flag = true;
                for(int i = 0; i<quantity.length; i++)
                {
                    Item item = itemRepository.findById(item_ids[i]).orElse(null);
                    ReservedItems reserveItems = reserveItemRepository.findById(item_ids[i]).orElse(null);
                    int q = (reserveItems==null  ? 0:reserveItems.getReserved_quantity());
                    if(item!=null &&  item.getQuantity()-q<quantity[i])
                    {
                        flag=false;
                        keys.add(key);
                        break;
                    }
                }
                if(flag)
                {
                    for(int i = 0; i<quantity.length; i++)
                    {
                        ReservedItems reserveItems = reserveItemRepository.findById(item_ids[i]).orElse(null);
                        if(reserveItems!=null)
                        {
                            reserveItems.setReserved_quantity(quantity[i]+reserveItems.getReserved_quantity());
                            reserveItemRepository.save(reserveItems);
                        }
                        else
                        {
                            ReservedItems reservedItems = new  ReservedItems(item_ids[i],quantity[i]);
                            reserveItemRepository.save(reservedItems);
                        }
                    }
                    kafkaTemplate.send("coor-service",new InventoryResponse(key,true));
                    task.setStatus(2);//this means reservatino done
                }
                else
                {
                    kafkaTemplate.send("coor-service",new InventoryResponse(key,false));
                }

            }
        }
        for(String key : keys)
        {
            guavaCache.invalidate(key);
        }
    }
    @Scheduled(fixedDelay = 1000)
    public void DeductItems()
    {
        HashSet<String> keys = new HashSet<>();
        for(String key : guavaCache.asMap().keySet())
        {
            ReserveItems task = guavaCache.asMap().get(key);
            Integer[] item_ids = task.getItemIds();
            Integer[] quantity = task.getQuantity();
            if (task.getStatus() == 1)
            {
                for(int i = 0; i<quantity.length; i++)
                {
                    Item item =  itemRepository.findById(item_ids[i]).orElse(null);
                    ReservedItems reserveItems = reserveItemRepository.findById(item_ids[i]).orElse(null);
                    item.setQuantity(item.getQuantity()-quantity[i]);
                    reserveItems.setReserved_quantity(reserveItems.getReserved_quantity()-quantity[i]);
                    reserveItemRepository.save(reserveItems);
                    itemRepository.save(item);
                }
                kafkaTemplate.send("coor-service",new InventoryResponse(key,true));
                keys.add(key);
                task.setStatus(3);
            }
        }
        for(String key : keys)
        {
            guavaCache.invalidate(key);
        }
    }

}
