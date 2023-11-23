package com.ecommerce.customerservice.service;

import com.ecommerce.customerservice.dto.CartRequest;
import com.ecommerce.customerservice.dto.CustomerRequest;
import com.ecommerce.customerservice.feignClient.CartClient;
import com.ecommerce.customerservice.model.Customer;
import com.ecommerce.customerservice.model.Role;
import com.ecommerce.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CartClient cartClient;

    public Customer registration(Customer customer) {
        String password = customer.getPassword();
        customer.setPassword(new BCryptPasswordEncoder().encode(password));
        customer.setRole(Role.CUSTOMER);
        if(customerRepository.findByEmail(customer.getEmail()) != null) {
            throw new RuntimeException("Username is already taken.");
        }
        Customer c = customerRepository.save(customer);
        cartClient.createCart(new CartRequest(c.getId()));
        return c;
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID : " + id));
    }

    public Customer getByEmail(String email){
        return customerRepository.findByEmail(email);
    }

    public Customer updateUser(Long id, CustomerRequest customerRequest) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + id));

        existingCustomer.setFirstName(customerRequest.getFirstName());
        existingCustomer.setLastName(customerRequest.getLastName());
        existingCustomer.setAddress(customerRequest.getAddress());
        existingCustomer.setPhoneNumber(customerRequest.getPhoneNumber());
        existingCustomer.setEmail(customerRequest.getEmail());

        return customerRepository.save(existingCustomer);

    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

}
