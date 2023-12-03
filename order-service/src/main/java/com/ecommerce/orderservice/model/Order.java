package com.ecommerce.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "VARCHAR(20) default 'PENDING'", nullable = false)
    private OrderStatus status = OrderStatus.PENDING;

    @Column(name = "total_amount", columnDefinition = "Decimal(10,2) default '0.00'", nullable = false)
    private BigDecimal totalAmount = new BigDecimal(0);

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "createdAt")
    private LocalDateTime createdAt;
    public void addItem(OrderItem orderItem) {
        orderItems.add(orderItem);
    }

    public void increaseTotalAmount(BigDecimal value) {
        setTotalAmount(this.totalAmount.add(value));
    }
    public void updateTotalAmount() {
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (OrderItem orderItem : orderItems) {
            totalAmount = totalAmount.add(orderItem.getTotalPrice());
        }

        this.totalAmount = totalAmount;
    }
    
}