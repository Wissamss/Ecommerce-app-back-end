package com.ecommerce.orderservice.dto;

import com.ecommerce.orderservice.model.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Long id;
    private Long customerId;
    private BigDecimal totalAmount;
    private OrderStatus status;
    @JsonIgnoreProperties("order")
    private List<OrderItemResponse> orderItems;
    private LocalDateTime createdAt;
}