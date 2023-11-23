package com.ecommerce.productservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String designation;
    private BigDecimal price;
    private String category;
    //private String description;
    //private String image;
    private Integer quantity;

}

