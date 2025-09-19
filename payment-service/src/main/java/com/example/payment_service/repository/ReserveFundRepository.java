package com.example.payment_service.repository;

import com.example.payment_service.entity.ReserveFund;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReserveFundRepository extends JpaRepository<ReserveFund, Integer> {
}
