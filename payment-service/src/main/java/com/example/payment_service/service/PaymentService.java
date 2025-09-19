package com.example.payment_service.service;

import com.example.events.dtos.ChargeMoney;
import com.example.events.dtos.ReleaseFunds;
import com.example.events.dtos.ReservePayment;
import com.google.common.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private Cache<String, ReservePayment> guavacache;

    @KafkaListener(topics = "payment-service")
    public void reservefunds(ReservePayment payments)
    {
        guavacache.asMap().put(payments.getCorrelationId(),  payments);
    }

    @KafkaListener(topics = "payment-service")
    public void releasefunds(ReleaseFunds payments)
    {
        if(guavacache.asMap().containsKey(payments.getCorrelationid()))
        {
            guavacache.invalidate(payments.getCorrelationid());
        }
    }

    @KafkaListener(topics = "payment-service")
    public void deductmoney(ChargeMoney payments)
    {
        guavacache.asMap().get(payments.getCorrelationid()).setStatus(1);
    }
}
