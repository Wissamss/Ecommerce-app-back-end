package com.ecommerce.orderservice.feignClient;

import com.ecommerce.orderservice.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping(value = "/api/products/{id}")
    ResponseEntity<ProductResponse> getProductById(@PathVariable Long id);

}