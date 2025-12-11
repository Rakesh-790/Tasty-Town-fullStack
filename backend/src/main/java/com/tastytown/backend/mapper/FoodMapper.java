package com.tastytown.backend.mapper;

import com.tastytown.backend.dto.FoodRequestDTO;
import com.tastytown.backend.dto.FoodResponseDTO;
import com.tastytown.backend.entity.Category;
import com.tastytown.backend.entity.Food;

public class FoodMapper {
    private FoodMapper() {

    }

    public static Food convertToEntity(FoodRequestDTO foodRequestDTO, Category existingCategory, String foodImage) {
        return Food.builder()
                .foodName(foodRequestDTO.foodName())
                .foodDescription(foodRequestDTO.foodDescription())
                .foodPrice(foodRequestDTO.foodPrice())
                .foodImage(foodImage)
                .category(existingCategory)
                .build();
    }

    public static FoodResponseDTO convertToDTO(Food svaedFood) {
        return new FoodResponseDTO(
                svaedFood.getFoodId(),
                svaedFood.getFoodName(),
                svaedFood.getFoodDescription(),
                svaedFood.getFoodPrice(),
                svaedFood.getFoodImage(),
                svaedFood.getCategory().getCategoryName(),
                svaedFood.getCategory().getCategoryId());
    }
}
