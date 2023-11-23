package com.ecommerce.cartservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponse {
    private Long id;
    private String designation;
    private BigDecimal price;
    private Integer quantity;
    private String category;
}
