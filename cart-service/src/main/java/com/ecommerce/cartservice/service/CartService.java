package com.ecommerce.cartservice.service;

import com.ecommerce.cartservice.FeignClient.OrderClient;
import com.ecommerce.cartservice.FeignClient.ProductClient;
import com.ecommerce.cartservice.dto.CartResponse;
import com.ecommerce.cartservice.dto.ProductResponse;
import com.ecommerce.cartservice.model.Cart;
import com.ecommerce.cartservice.model.CartItem;
import com.ecommerce.cartservice.repository.CartItemRepository;
import com.ecommerce.cartservice.repository.CartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderClient orderClient;
    private final ProductClient productClient;
    private final ModelMapper modelMapper;

    public Cart create(Cart cart) {
        Cart existingCart = getByCustomerId(cart.getCustomerId());
        if(existingCart != null) {
            throw new RuntimeException("Cart for customer already exist");
        }
        return cartRepository.save(cart);
    }

    private BigDecimal calculatePrice(BigDecimal price, int quantity) {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    @Transactional
    public Cart addItem(Long cartId, CartItem cartItem) {
        Cart cart = getById(cartId);
        ProductResponse productResponse = productClient.getProductById(cartItem.getProductId()).getBody();
        cartItem.setCart(cart);
        cartItem.setTotalPrice(calculatePrice(productResponse.getPrice(), cartItem.getQuantity()));
        cart.addItem(cartItem);
        cartItemRepository.save(cartItem);
        return cartRepository.save(cart);
    }

    public Cart updateItem(Long cartId, Long itemId, CartItem cartItem){
        Cart cart = getById(cartId);
        ProductResponse productResponse = productClient.getProductById(cartItem.getProductId()).getBody();
        Optional<CartItem> existingItem = cart.getCartItems().stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst();

        existingItem.ifPresent(item -> {
            item.setQuantity(cartItem.getQuantity());
            item.setTotalPrice(calculatePrice(productResponse.getPrice(), item.getQuantity()));
            cartItemRepository.save(item);
        });
       return cartRepository.save(cart);
    }



    public Cart removeItem(Long cartId, Long itemId) {
        Cart cart = getById(cartId);
        cart.removeItem(itemId);
        cartItemRepository.deleteById(itemId);
        return cartRepository.save(cart);
    }


    public Cart getById(Long id) {
        return cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found"));
    }


    @Transactional
    public Cart completeCart(Long id) {
        Cart cart = getById(id);
        orderClient.create(modelMapper.map(cart, CartResponse.class));
        cartItemRepository.deleteAllByCartId(id);
        cart.setCartItems(new ArrayList<>());
        return cart;
    }


    public Cart getByCustomerId(Long id) {
        return cartRepository.findByCustomerId(id);
    }

   public void deleteByCustomerId(Long id) {
        cartRepository.deleteByCustomerId(id);
    }
}
