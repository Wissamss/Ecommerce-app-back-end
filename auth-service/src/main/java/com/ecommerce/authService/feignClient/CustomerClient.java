package com.ecommerce.authService.feignClient;

import com.ecommerce.authService.dto.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "customerr-service")
public interface CustomerClient {
    @GetMapping("/api/customers/email")
    ResponseEntity<CustomerResponse> getByEmail(@RequestHeader("email") String email);
}
