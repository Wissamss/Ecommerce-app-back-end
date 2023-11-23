package com.ecommerce.cartservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemResponse {
    private Long id;
    private Long productId;
    private Integer quantity;
    private BigDecimal totalPrice;
    @JsonIgnoreProperties("cartItems")
    private CartResponse cart;
}
