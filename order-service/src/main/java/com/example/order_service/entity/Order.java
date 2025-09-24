package com.example.order_service.entity;

import com.example.events.dtos.ORDER_STATUS;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "orders")  // avoid reserved word "order"
@NoArgsConstructor
@Data
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

    @Column(name="status")
    private String status;

    public Order(Integer clientId, Integer[] itemsids,Integer[] quantity,Integer amount) {
        this.clientId = clientId;
        this.itemsids = itemsids;
        this.amount = amount;
        this.quantity = quantity;
        this.status = ORDER_STATUS.ORDER_CREATED.toString();

    }

}
