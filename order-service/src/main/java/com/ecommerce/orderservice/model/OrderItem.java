package com.ecommerce.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "quantity", columnDefinition = "Integer default 0", nullable = false)
    private Integer quantity = 0;

    @Column(name = "total_price", columnDefinition = "Decimal(10,2) default '0.00'", nullable = false)
    private BigDecimal totalPrice = BigDecimal.valueOf(0);

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}