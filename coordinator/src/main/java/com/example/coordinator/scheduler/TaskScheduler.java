package com.example.coordinator.scheduler;

import com.example.coordinator.entity.Order;
import com.example.events.dtos.*;
import com.google.common.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskScheduler {

    // Runs every 1 second
    @Autowired
    private Cache<String, Order> guavaCache;

    @Autowired
    private KafkaTemplate<String, CreateOrder> ordertemplate;

    @Autowired
    private KafkaTemplate<String, ReserveItems> inventorytemplate;

    @Autowired
    private KafkaTemplate<String, ReservePayment> paymenttemplate;

    @Autowired
    private KafkaTemplate<String, FinalizeOrder> finalizeordertemplate;

    @Autowired
    private KafkaTemplate<String, DeductItems> deducttemplate;

    @Autowired
    private KafkaTemplate<String,ChargeMoney>  chargeMoneytemplate;

    @Scheduled(fixedRate = 1000)
    public void runjob() {
        for (String key : guavaCache.asMap().keySet()) {
            Order order = guavaCache.asMap().get(key);
            if (order.getPhase() == 0)
            {
                order.setPhase(1);

                int sum = 0;
                for (int i = 0; i < order.getItemids().length; i++) {
                    sum += order.getPrices()[i] * order.getQuantities()[i];
                }

                CreateOrder orderevent = new CreateOrder(
                        key,
                        order.getItemids(),
                        order.getQuantities(),
                        order.getClientid(),
                        sum
                );

                ReserveItems itemevent = new ReserveItems(
                        key,
                        order.getItemids(),
                        order.getQuantities()
                );

                ReservePayment paymentevent = new ReservePayment(
                        key,
                        order.getClientid(),
                        sum
                );

                ordertemplate.send("order-service", orderevent);
                inventorytemplate.send("inventory-service", itemevent);
                paymenttemplate.send("payment-service", paymentevent);
            }
            else if(order.getResponses().size()==3) {
                if (order.getResponses().get(0) && order.getResponses().get(1) && order.getResponses().get(2))
                {
                    //Commit phase starts
                    finalizeordertemplate.send("order-service", new FinalizeOrder(key));
                    deducttemplate.send("payment-service", new DeductItems(key));
                    chargeMoneytemplate.send("payment-service", new ChargeMoney(key));
                    System.out.println("Order Placed Successfully! Congrats.");
                }

            }

        }
    }
}
