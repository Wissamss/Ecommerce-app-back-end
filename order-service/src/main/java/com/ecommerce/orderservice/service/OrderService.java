package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.model.Order;
import com.ecommerce.orderservice.model.OrderItem;
import com.ecommerce.orderservice.repository.OrderItemRepository;
import com.ecommerce.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id){
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"+id));
    }

    public Order addOrder(Order order){
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id ,Order order){
        Order existingOrder = getOrderById(id);
        return orderRepository.save(existingOrder);
    }

    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }

    public List<Order> getOrdersByCustomer(Integer customerId){
        List<Order> orders = orderRepository.findByCustomerId(customerId);
        return orders;
    }


    public List<OrderItem> getOrderItemsByOrder(Long id) {
        return orderItemRepository.findByOrder_Id(id);
    }
}
