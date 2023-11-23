package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.model.OrderItem;
import com.ecommerce.orderservice.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public OrderItem create(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
