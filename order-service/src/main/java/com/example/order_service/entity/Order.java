package com.example.order_service.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "orders")  // avoid reserved word "order"
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderid")
    private Integer orderid;

    @Column(name = "clientid")
    private Integer clientId;

    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(name = "itemsids", columnDefinition = "integer[]")
    private Integer[] itemsids;

    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(name = "quantity", columnDefinition = "integer[]")
    private Integer[] quantity;

    @Column(name = "amount")
    private Integer amount;

    public Order(Integer clientId, Integer[] itemsids, Integer[] quantity, Integer amount) {
        this.clientId = clientId;
        this.itemsids = itemsids;
        this.quantity = quantity;
        this.amount = amount;
    }

    public Order() {
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer[] getItemsids() {
        return itemsids;
    }

    public void setItemsids(Integer[] itemsids) {
        this.itemsids = itemsids;
    }

    public Integer[] getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer[] quantity) {
        this.quantity = quantity;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
