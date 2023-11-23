package com.ecommerce.cartservice.controller;

import com.ecommerce.cartservice.dto.CartItemRequest;
import com.ecommerce.cartservice.dto.CartRequest;
import com.ecommerce.cartservice.dto.CartResponse;
import com.ecommerce.cartservice.model.Cart;
import com.ecommerce.cartservice.model.CartItem;
import com.ecommerce.cartservice.service.CartService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<CartResponse> createCart(@RequestBody CartRequest cartRequest) {
        Cart cart = modelMapper.map(cartRequest, Cart.class);
        Cart createdCart = cartService.create(cart);
        CartResponse cartResponse = modelMapper.map(createdCart, CartResponse.class);
        return ResponseEntity.ok(cartResponse);

    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartResponse> getCartById(@PathVariable("cartId") Long cartId){
        Cart cart = cartService.getById(cartId);
        return ResponseEntity.ok(modelMapper.map(cart, CartResponse.class));
    }

    //show cart by each user
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<CartResponse> getCartByCustomerId(@PathVariable("customerId") Long customerId) {
        Cart cart = cartService.getByCustomerId(customerId);
        return ResponseEntity.ok(modelMapper.map(cart, CartResponse.class));
    }

    //add item
    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartResponse> createCartItem(@PathVariable("cartId") Long cartId, @Valid @RequestBody CartItemRequest cartItemRequest) {
        Cart existingCart = cartService.getById(cartId);
        if (existingCart == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Cart not found
        }
        Cart cart = cartService.addItem(cartId, modelMapper.map(cartItemRequest, CartItem.class));
        return ResponseEntity.ok(modelMapper.map(cart, CartResponse.class));
    }
    @PutMapping("/{cartId}/items/{itemId}")
    public ResponseEntity<CartResponse> updateCartItem(@PathVariable("cartId") Long cartId, @PathVariable("itemId") Long itemId, @Valid @RequestBody CartItemRequest cartItemRequest) {
        Cart existingCart = cartService.getById(cartId);
        if (existingCart == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Cart not found
        }
        Cart cart = cartService.updateItem(cartId, itemId, modelMapper.map(cartItemRequest, CartItem.class));
        return ResponseEntity.ok(modelMapper.map(cart, CartResponse.class));
    }
    @PostMapping("/{cartId}/complete")
    public ResponseEntity<CartResponse> completeCart(@PathVariable("cartId") Long cartId) {
        Cart cart = cartService.completeCart(cartId);
        return ResponseEntity.ok(modelMapper.map(cart, CartResponse.class));
    }

    //delete item
    @DeleteMapping("/{cartId}/items/{itemId}")
    public ResponseEntity<CartResponse> deleteCartItem(@PathVariable("cartId") Long cartId, @PathVariable("itemId") Long itemId) {
        Cart cart = cartService.removeItem(cartId, itemId);
        return ResponseEntity.ok(modelMapper.map(cart, CartResponse.class));
    }
}
