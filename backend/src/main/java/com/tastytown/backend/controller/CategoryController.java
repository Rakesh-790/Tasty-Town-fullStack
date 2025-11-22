package com.tastytown.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tastytown.backend.dto.CategoryRequestDTO;
import com.tastytown.backend.entity.Category;
import com.tastytown.backend.service.ICategoryService;
// import com.tastytown.backend.service.CategoryServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
@Tag(name = "Category Api", description = "This Controller Manages CRUD operations for Category Entity")
public class CategoryController {
    private final ICategoryService categoryService;

    @PostMapping("/add")
    @Operation(summary = "Create a new category")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryRequestDTO requestDTO) {
        return new ResponseEntity<>(categoryService.saveCategory(requestDTO), HttpStatus.CREATED);
    }

    @GetMapping
    @ApiResponse(description = "List of all categories")
    @Operation(summary = "Get all categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{categoryId}")
    @ApiResponse(description = "Category retrieved successfully by ID")
    @Operation(summary = "Get a category by ID")
    public ResponseEntity<Category> getCategoryById(@PathVariable String categoryId) {
        return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
    }

    @PutMapping("/{categoryId}")
    @ApiResponse(description = "Category updated successfully")
    @Operation(summary = "Update a category by ID")
    public ResponseEntity<Category> updateCategory(@PathVariable String categoryId,
            @RequestBody CategoryRequestDTO requestDTO) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryId, requestDTO));
    }

    @DeleteMapping("/{categoryId}")
    // @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponse(description = "Category deleted successfully")
    @Operation(summary = "Delete a category by ID")
    public ResponseEntity<Void> deleteCategory(@PathVariable String categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }
}
