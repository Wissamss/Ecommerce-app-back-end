package com.ecommerce.orderservice.controller;

import com.ecommerce.orderservice.dto.*;
import com.ecommerce.orderservice.model.Order;
import com.ecommerce.orderservice.model.OrderItem;
import com.ecommerce.orderservice.model.OrderStatus;
import com.ecommerce.orderservice.service.OrderItemService;
import com.ecommerce.orderservice.service.OrderService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
@CrossOrigin
public class OrderController {
    private final OrderService orderService;
    private final ModelMapper modelMapper;
    private final OrderItemService orderItemService;

    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody OrderRequest orderRequest) {
        Order order = new Order();
        order.setCustomerId(orderRequest.getCustomerId());
        orderService.addOrder(order);
        for(CartItemResponse cartItem : orderRequest.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setProductId(cartItem.getProductId());
            orderItem.setTotalPrice(cartItem.getTotalPrice());
            orderItem.setOrder(order);
            order.addItem(orderItem);
            orderItemService.create(orderItem);
        }
        order.updateTotalAmount();
        orderService.updateOrder(order.getId(), order);
        return ResponseEntity.ok(modelMapper.map(order, OrderResponse.class));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAll() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders.stream().map(order -> modelMapper.map(order, OrderResponse.class)).toList());
    }


    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderResponse>> getOrdersByCustomer(@PathVariable("customerId") Integer id) {
        List<Order> orders = orderService.getOrdersByCustomer(id);
        return ResponseEntity.ok(orders.stream().map(order -> modelMapper.map(order, OrderResponse.class)).toList());
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<List<OrderItemResponse>> getOrderItemsByOrder(@PathVariable("id") Long id) {
        List<OrderItem> orderItems = orderService.getOrderItemsByOrder(id);
        return ResponseEntity.ok(orderItems.stream().map(orderItem -> modelMapper.map(orderItem, OrderItemResponse.class)).toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrderByStatus(@PathVariable("id") Long id, @RequestBody OrderStatusUpdate status) {
        Order order = orderService.getOrderById(id);
        String orderStatus = status.getStatus();
        order.setStatus(OrderStatus.valueOf(orderStatus.toUpperCase()));
        return ResponseEntity.ok(modelMapper.map(orderService.addOrder(order), OrderResponse.class));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
