package com.example.payment_service.scheduler;

import com.example.events.dtos.PaymentResponse;
import com.example.events.dtos.ReservePayment;
import com.example.payment_service.entity.ReserveFund;
import com.example.payment_service.entity.User;
import com.example.payment_service.repository.PaymentRepository;
import com.example.payment_service.repository.ReserveFundRepository;
import com.google.common.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class TaskScheduler {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ReserveFundRepository reserveFundRepository;

    @Autowired
    private Cache<String, ReservePayment> guavaCache;

    @Autowired
    private KafkaTemplate<String, PaymentResponse> kafkaTemplate;


    @Scheduled(fixedRate = 1000)//this needs to be fixed
    public void runJob()
    {
        HashSet<String> keys = new HashSet<>();
        for(String key: guavaCache.asMap().keySet())
        {
            ReservePayment reservePayment = guavaCache.getIfPresent(key);
            User user = paymentRepository.findById(reservePayment.getClientid()).orElse(null);
            ReserveFund fundReserve = reserveFundRepository.findById(reservePayment.getClientid()).orElse(null);
            int reserveAmount = (fundReserve == null) ? 0 : fundReserve.getReserveAmount();

            if(reservePayment.getStatus() == 0)//perform reservation
            {
                if(user != null)
                {
                    if(user.getBalance() - reserveAmount < reservePayment.getAmount())
                    {
                        keys.add(key);
                        kafkaTemplate.send("coor-service",new PaymentResponse(reservePayment.getCorrelationId(),false));
                    }
                    else
                    {
                        if(fundReserve != null)
                        {
                            fundReserve.setReserveAmount(fundReserve.getReserveAmount()+reserveAmount);
                            reserveFundRepository.save(fundReserve);
                        }
                        else
                        {
                            ReserveFund reserveFund = new ReserveFund(reservePayment.getClientid(),reservePayment.getAmount());
                            reserveFundRepository.save(reserveFund);
                        }
                        kafkaTemplate.send("coor-service",new PaymentResponse(reservePayment.getCorrelationId(),true));//send that reserve is done correctly
                    }
                }
            }
            else
            {
                if(user != null)
                {
                    keys.add(key);
                    user.setBalance(user.getBalance()-reserveAmount);
                    fundReserve.setReserveAmount(fundReserve.getReserveAmount()-reserveAmount);
                    kafkaTemplate.send("coor-service",new PaymentResponse(reservePayment.getCorrelationId(),true)); //reserve is done correctly
                }
            }
        }
        for(String key:keys)
        {
            guavaCache.invalidate(key);
        }
    }
}
