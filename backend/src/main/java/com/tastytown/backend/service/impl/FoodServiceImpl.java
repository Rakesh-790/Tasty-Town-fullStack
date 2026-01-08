package com.tastytown.backend.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import com.tastytown.backend.repository.CategoryRepository;
import jakarta.transaction.Transactional;
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
import com.tastytown.backend.service.IImageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements IFoodService {

    private final ICategoryService categoryService;
    private final FoodRepository foodRepository;
    private final FoodServiceHelper foodServiceHelper; // you can later remove image-related methods from this helper
    private final IImageService imageService;          // NEW: Cloudinary service
    private final CategoryRepository categoryRepository;

    @Override
    public FoodResponseDTO createFood(FoodRequestDTO foodRequestDTO, MultipartFile foodImage) throws IOException {
        var existingCategory = categoryService.getCategoryById(foodRequestDTO.categoryId());

        // Upload image to Cloudinary instead of local folder
        String imageUrl = imageService.uploadFoodImage(foodImage);

        // Save the food in the database with Cloudinary URL
        var food = FoodMapper.convertToEntity(foodRequestDTO, existingCategory, imageUrl);
        var savedFood = foodRepository.save(food);

        return FoodMapper.convertToDTO(savedFood);
    }

    @Override
    public List<FoodResponseDTO> getAllFoods() {
        var food = foodRepository.findAll();
        return food.stream().map(FoodMapper::convertToDTO).toList();
    }

    @Override
    public FoodResponseDTO getFoodById(String foodId) {
        var food = foodRepository.findById(foodId)
                .orElseThrow(() -> new NoSuchElementException("Food not found with id: " + foodId));
        return FoodMapper.convertToDTO(food);
    }

    @Override
    @Transactional
    public FoodResponseDTO updateFood(String foodId,
                                      FoodRequestDTO dto,
                                      MultipartFile foodImage) throws IOException {

        var existingFood = foodRepository.findById(foodId)
                .orElseThrow(() -> new NoSuchElementException("Food not found with id: " + foodId));

        existingFood.setFoodName(dto.foodName());
        existingFood.setFoodPrice(dto.foodPrice());
        existingFood.setFoodDescription(dto.foodDescription());

        // If new image is provided, upload to Cloudinary and replace URL
        if (foodImage != null && !foodImage.isEmpty()) {
            // OPTIONAL: you can delete old Cloudinary image if you store public_id
            // For now, just upload new and overwrite the URL
           try {
                String newImageUrl = imageService.uploadFoodImage(foodImage);
                existingFood.setFoodImage(newImageUrl);
            } catch (Exception e) {
                throw new RuntimeException("Image upload failed", e);
            }
        }

        if (dto.categoryId() != null && !dto.categoryId().isEmpty()) {
           categoryRepository.findById(dto.categoryId())
                    .ifPresent(existingFood::setCategory);
        }

        var savedFood = foodRepository.save(existingFood);
        return FoodMapper.convertToDTO(savedFood);
    }

    @Override
    public FoodResponseDTO deleteFood(String foodId) throws IOException {
        var food = foodRepository.findById(foodId)
                .orElseThrow(() -> new NoSuchElementException("Food not found with id: " + foodId));

        // IMPORTANT: no more local file delete here.
        // If you want to delete from Cloudinary, you'd need to store public_id (not only URL).

        foodRepository.deleteById(foodId);
        return FoodMapper.convertToDTO(food);
    }

    @Override
    public Page<FoodResponseDTO> getPaginatedFoods(int pageNumber,
                                                   int pageSize,
                                                   String categoryId,
                                                   String search) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Food> foodPage;
        if (categoryId != null && !categoryId.equals("all") && !search.equals("all")) {
            foodPage = foodRepository
                    .findByCategory_CategoryIdAndFoodNameContainingIgnoreCase(categoryId, search, pageable);
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