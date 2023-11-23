package com.ecommerce.orderservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderStatusUpdate {
    @NotNull
    private String status;
}
