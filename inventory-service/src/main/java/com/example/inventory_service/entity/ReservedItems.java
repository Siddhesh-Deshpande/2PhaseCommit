package com.example.inventory_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="reserved_items")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservedItems {
    @Id
    @Column(name="item_id")
    private Integer item_id;
    @Column(name="reserved_quantity")
    private Integer reserved_quantity;


}
