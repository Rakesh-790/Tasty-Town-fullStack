package com.tastytown.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tastytown.backend.dto.CartItemRequestDTO;
import com.tastytown.backend.dto.CartResponseDTO;
import com.tastytown.backend.service.ICartService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {

    private final ICartService cartService;

    @PostMapping("/items")
    public ResponseEntity<CartResponseDTO> addItemToCart(
            Principal principal,
            @RequestBody CartItemRequestDTO dto) {

        String userId = principal.getName(); // Extracted from JWT (sub/username)
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cartService.addItemToCart(userId, dto));
    }

    @GetMapping
    public ResponseEntity<CartResponseDTO> getCartByUserId(Principal principal) {
        String userId = principal.getName();
        return ResponseEntity.ok(cartService.getCartByUserId(userId));
    }

    @PutMapping("/updateQuantity")
    public ResponseEntity<CartResponseDTO> updateCartItemsQuantity(
            Principal principal,
            @RequestBody CartItemRequestDTO cartItemRequestDTO) {

        String userId = principal.getName();
        return ResponseEntity.ok(cartService.updateItemQuantity(userId, cartItemRequestDTO));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Void> clearCartItemsOfAnUser(Principal principal) {
        String userId = principal.getName();
        cartService.clearCartItem(userId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/item/{foodId}")
    public ResponseEntity<CartResponseDTO> removeItemFromCart(
            Principal principal,
            @PathVariable String foodId) {

        String userId = principal.getName();
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(cartService.removeItemFromCart(userId, foodId));
    }
}
