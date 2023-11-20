package com.ecommerce.productservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue
    private Integer productId;
    private String designation;
    private int price;
    private String category;
    //private String description;
    //private String image;
    private int quantity;

}

