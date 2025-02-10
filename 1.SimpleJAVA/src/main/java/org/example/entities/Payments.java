package org.example.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tbl_payments")
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private Orders order;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(length = 50)
    private String paymentMethod;

    @Column(length = 50, columnDefinition = "VARCHAR(50) DEFAULT 'pending'")
    private String status;
}
