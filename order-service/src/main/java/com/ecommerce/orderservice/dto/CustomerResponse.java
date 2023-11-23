package com.ecommerce.orderservice.dto;

import lombok.Data;

@Data
public class CustomerResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phoneNumber;
}