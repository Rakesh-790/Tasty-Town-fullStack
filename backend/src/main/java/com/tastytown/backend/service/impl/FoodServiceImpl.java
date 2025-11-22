package com.tastytown.backend.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tastytown.backend.Helper.FoodServiceHelper;
import com.tastytown.backend.dto.FoodRequestDTO;
import com.tastytown.backend.dto.FoodResponseDTO;
import com.tastytown.backend.entity.Food;
import com.tastytown.backend.mapper.FoodMapper;
import com.tastytown.backend.repository.FoodRepository;
import com.tastytown.backend.service.ICategoryService;
import com.tastytown.backend.service.IFoodService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements IFoodService {
    private final ICategoryService categoryService;
    private final FoodRepository foodRepository;
    private final FoodServiceHelper foodServiceHelper;
    @Override
    public FoodResponseDTO createFood(FoodRequestDTO foodRequestDTO, MultipartFile foodImage) throws IOException {
        var existingCategory = categoryService.getCategoryById(foodRequestDTO.categoryId());
        // save the image in the folder
        var fileName = foodServiceHelper.uploadFile(foodImage);

        // save the food in the database
        var food = FoodMapper.convertToEntity(foodRequestDTO, existingCategory, fileName);
        var savedFood = foodRepository.save(food);

        return FoodMapper.convertToDTO(savedFood);
    }

    @Override
    public List<FoodResponseDTO> getAllFoods() {
        var food = foodRepository.findAll();
        // return food.stream().map(foodItem ->
        // FoodMapper.convertToDTO(foodItem)).toList();
        return food.stream().map(FoodMapper::convertToDTO).toList();
    }

    @Override
    public FoodResponseDTO getFoodById(String foodId) {
        var food = foodRepository.findById(foodId)
                .orElseThrow(() -> new NoSuchElementException("Food not found with id: " + foodId));
        return FoodMapper.convertToDTO(food);
    }

    @Override
    public FoodResponseDTO updateFood(String foodId,
            FoodRequestDTO dto, MultipartFile foodImage) throws IOException {
        var existingFood = foodRepository.findById(foodId)
                .orElseThrow(() -> new NoSuchElementException("Food not found with id: " + foodId));
        // var category = categoryService.getCategoryById(dto.categroyId());
        existingFood.setFoodName(dto.foodName());
        existingFood.setFoodPrice(dto.foodPrice());
        existingFood.setFoodDescription(dto.foodDescription());

        if (foodImage != null && !foodImage.isEmpty()) {
            foodServiceHelper.deleteFoodImage(existingFood.getFoodImage());
            var newFoodImageName = foodServiceHelper.uploadFile(foodImage);
            existingFood.setFoodImage(newFoodImageName);
            if (dto.categoryId() != null && !dto.categoryId().isEmpty()) {
                var categories = categoryService.getCategoryById(dto.categoryId());
                existingFood.setCategory(categories);
            }
        }
        var savedFood = foodRepository.save(existingFood);
        return FoodMapper.convertToDTO(savedFood);
    }

    @Override
    public FoodResponseDTO deleteFood(String foodId) throws IOException {
        var food = foodRepository.findById(foodId)
                .orElseThrow(() -> new NoSuchElementException("Food not found with id: " + foodId));
        foodServiceHelper.deleteFoodImage(food.getFoodImage());
        foodRepository.deleteById(foodId);
        return FoodMapper.convertToDTO(food);

    }

    @SuppressWarnings("null")
    @Override
    public Page<FoodResponseDTO> getPaginatedFoods(int pageNumber, int pageSize, String categoryId, String search) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        // filternation
        Page<Food> foodPage;
        if (categoryId != null && !categoryId.equals("all") && !search.equals("all")) {
            foodPage = foodRepository.findByCategory_CategoryIdAndFoodNameContainingIgnoreCase(categoryId, search,
                    pageable);
        } else if (!categoryId.equals("all")) {
            foodPage = foodRepository.findByCategory_CategoryId(categoryId, pageable);
        } else if (!search.equals("all")) {
            foodPage = foodRepository.findByFoodNameContainingIgnoreCase(search, pageable);
        } else {
            foodPage = foodRepository.findAll(pageable);
        }
        return foodPage.map(FoodMapper::convertToDTO);
    }
}
