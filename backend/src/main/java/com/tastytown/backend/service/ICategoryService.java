package com.tastytown.backend.service;

import java.util.List;

import com.tastytown.backend.dto.CategoryRequestDTO;
import com.tastytown.backend.entity.Category;

public interface ICategoryService {
    /**
     * <h3>Saves a new Category Object.</h3> 
     */
    Category saveCategory(CategoryRequestDTO requestDTO);
    /**
     * <h3>Retrieves all categories.</h3>
     */
    List<Category> getAllCategories();
    /**
     * <h3>Retrieves a category by its ID.</h3>
     */
    Category getCategoryById(String categoryId);
    /**
     * <h3>Updates an existing category.</h3>
     */
    Category updateCategory(String categoryId, CategoryRequestDTO requestDTO);
    /**
     * <h3>Deletes a category by its ID.</h3>
     */
    void deleteCategory(String categoryId);
}
