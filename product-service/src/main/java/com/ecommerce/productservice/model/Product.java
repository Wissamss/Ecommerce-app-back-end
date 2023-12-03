package com.ecommerce.productservice.model;

import jakarta.persistence.*;
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
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;
    private Integer quantity;

}

