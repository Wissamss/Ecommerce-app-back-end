package com.ecommerce.customerservice.feignClient;

import com.ecommerce.customerservice.dto.CartRequest;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "cart-service")
public interface CartClient {
    @PostMapping("/api/cart")
    ResponseEntity<Object> createCart(@Valid @RequestBody CartRequest cartRequest);
}
