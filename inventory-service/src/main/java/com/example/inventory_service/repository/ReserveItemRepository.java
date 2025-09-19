package com.example.inventory_service.repository;

import com.example.inventory_service.entity.ReservedItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReserveItemRepository extends JpaRepository<ReservedItems, Integer> {
}
