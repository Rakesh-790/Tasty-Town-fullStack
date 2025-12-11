package com.tastytown.backend.Helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.tastytown.backend.entity.Food;
import com.tastytown.backend.repository.FoodRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FoodServiceHelper {
    private final FoodRepository foodRepository;

    public Food getFoodById(String foodId) {
        return foodRepository.findById(foodId)
                .orElseThrow(() -> new NoSuchElementException("food not found by id" + foodId));
    }
}
