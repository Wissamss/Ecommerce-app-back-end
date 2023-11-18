package com.ecommerce.customerservice.service;

import com.ecommerce.customerservice.model.Customer;
import com.ecommerce.customerservice.model.CustomerRequest;
import com.ecommerce.customerservice.model.CustomerResponse;
import com.ecommerce.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
   public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public CustomerResponse getUserById(Integer id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        return CustomerResponse.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .phoneNumber(customer.getPhoneNumber())
                .address(customer.getAddress())
                .email(customer.getEmail())
                .build();
    }

    public CustomerResponse updateUser(Integer id, CustomerRequest customerRequest) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

        existingCustomer.setFirstName(customerRequest.getFirstName());
        existingCustomer.setLastName(customerRequest.getLastName());
        existingCustomer.setAddress(customerRequest.getAddress());
        existingCustomer.setPhoneNumber(customerRequest.getPhoneNumber());
        existingCustomer.setEmail(customerRequest.getEmail());

        Customer updatedCustomer = customerRepository.save(existingCustomer);

       return CustomerResponse.builder()
               .firstName(updatedCustomer.getFirstName())
               .lastName(updatedCustomer.getLastName())
               .address(updatedCustomer.getAddress())
               .phoneNumber(updatedCustomer.getPhoneNumber())
               .email(updatedCustomer.getEmail())
               .build();
    }

    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }
}

