package com.rayyan.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;


@Data
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    private BigDecimal amount;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
