package com.ecommerce.orderservice.dto;

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
