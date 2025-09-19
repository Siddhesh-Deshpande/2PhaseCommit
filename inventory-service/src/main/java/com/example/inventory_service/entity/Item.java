package com.example.inventory_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @Column(name="item_id")
    private Integer id;
    @Column(name="name")
    private String itemName;
    @Column(name="quantity")
    private Integer quantity;
    @Column(name="price")
    private Integer price;


}
