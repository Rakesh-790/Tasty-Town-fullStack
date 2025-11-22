package com.tastytown.backend.dto;

public record FoodResponseDTO(String foodId, String foodName, String description, double price, String foodImage,
        String categoryName, String categoryId) {
            
}
