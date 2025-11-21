package com.tastytown.backend.service;

import com.tastytown.backend.dto.CartItemRequestDTO;
import com.tastytown.backend.dto.CartResponseDTO;

public interface ICartService {
    CartResponseDTO addItemToCart(String email, CartItemRequestDTO cartItemRequestDTO);

    CartResponseDTO getCartByUserId(String email);

    CartResponseDTO updateItemQuantity(String email, CartItemRequestDTO cartItemRequestDTO);

    CartResponseDTO removeItemFromCart(String email, String foodId);

    void clearCartItem(String email);
}

