package com.ecommerce.authService.dto;

import com.ecommerce.authService.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    private Long id;
    private String email;
    private String password;
    private Role role;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;

}