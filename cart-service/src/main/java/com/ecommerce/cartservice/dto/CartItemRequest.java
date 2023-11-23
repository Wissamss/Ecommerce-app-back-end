package com.ecommerce.cartservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class CartItemRequest {
    @NotNull
    private Long productId;

    @Min(value = 0, message = "Quantity must be greater than or equal to 0")
    @NotNull
    private Integer quantity;

    @JsonIgnore
    private BigDecimal totalPrice;
}
