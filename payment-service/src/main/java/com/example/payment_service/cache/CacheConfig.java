package com.example.payment_service.cache;

import com.example.events.dtos.ReservePayment;
import com.example.payment_service.entity.ReserveFund;
import com.example.payment_service.repository.ReserveFundRepository;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.common.cache.Cache;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {

    @Autowired
    private ReserveFundRepository reserveFundRepository;
    @Bean
    public Cache<String, ReservePayment> guavaCache() {
        return CacheBuilder.newBuilder()
                .expireAfterWrite(3, TimeUnit.SECONDS)
                .removalListener((RemovalNotification<String, ReservePayment> notification) -> {
                    ReservePayment value = notification.getValue();
                    if(value.getStatus()==1) //if reserved then only
                    {
                        ReserveFund reserveFund = reserveFundRepository.findById(value.getClientid()).orElse(null);
                        reserveFund.setReserveAmount(reserveFund.getReserveAmount()-value.getAmount());
                        reserveFundRepository.save(reserveFund);
                    }
                })
                .build();
    }
}
