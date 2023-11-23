package com.ecommerce.cartservice.FeignClient;

import com.ecommerce.cartservice.dto.CartResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="order-service")
public interface OrderClient {
    @PostMapping("/api/orders")
    ResponseEntity<Object> create(@Valid @RequestBody CartResponse cart);
}

