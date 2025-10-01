package com.example.payment_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reserve_payments")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReserveFund {
    @Id
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "amount")
    private Integer reserveAmount;

}