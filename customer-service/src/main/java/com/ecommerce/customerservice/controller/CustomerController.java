package com.ecommerce.customerservice.controller;

import com.ecommerce.customerservice.dto.CustomerRequest;
import com.ecommerce.customerservice.dto.CustomerResponse;
import com.ecommerce.customerservice.model.Customer;
import com.ecommerce.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<CustomerResponse> registration(@RequestBody CustomerRequest customerRequest) {
        return ResponseEntity.ok(modelMapper.map(customerService.registration(modelMapper.map(customerRequest, Customer.class)), CustomerResponse.class));
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(modelMapper.map(customer, CustomerResponse.class));
    }

    @GetMapping("/email")
    public ResponseEntity<CustomerResponse> getByEmail(@RequestHeader("email") String email){
        Customer customer = customerService.getByEmail(email);
        return ResponseEntity.ok(modelMapper.map(customer, CustomerResponse.class));
    }

    @GetMapping("/profile")
        public ResponseEntity<CustomerResponse> getProfile(@RequestHeader("CustomerID") Long id) {
        Customer customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(modelMapper.map(customer, CustomerResponse.class));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable Long id, @RequestBody CustomerRequest customerRequest) {
        Customer updatedUser = customerService.updateUser(id, customerRequest);
        return ResponseEntity.ok(modelMapper.map(updatedUser, CustomerResponse.class));
    }

    @PutMapping("/profile")
    public ResponseEntity<CustomerResponse> updateProfile(@RequestHeader("CustomerID") Long id, @RequestBody CustomerRequest customerRequest) {
        Customer updatedUser = customerService.updateUser(id, customerRequest);
        return ResponseEntity.ok(modelMapper.map(updatedUser, CustomerResponse.class));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
