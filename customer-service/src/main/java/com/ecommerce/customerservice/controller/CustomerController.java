package com.ecommerce.customerservice.controller;

import com.ecommerce.customerservice.model.Customer;
import com.ecommerce.customerservice.model.CustomerRequest;
import com.ecommerce.customerservice.model.CustomerResponse;
import com.ecommerce.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getUserById(@PathVariable Integer id) {
        CustomerResponse user = customerService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/profile")
    public ResponseEntity<CustomerResponse> getProfile(@RequestHeader("UserID") Integer id) {
        CustomerResponse user = customerService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateUser(@PathVariable Integer id, @RequestBody CustomerRequest customerRequest) {
        CustomerResponse updatedUser = customerService.updateUser(id, customerRequest);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/profile")
    public ResponseEntity<CustomerResponse> updateProfile(@RequestHeader("UserID") Integer id, @RequestBody CustomerRequest customerRequest) {
        CustomerResponse updatedUser = customerService.updateUser(id, customerRequest);
        return ResponseEntity.ok(updatedUser);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}