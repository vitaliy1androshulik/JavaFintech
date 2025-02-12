package org.example.controller;

import org.example.dto.category.CategoryCreateDto;
import org.example.entities.CategoryEntity;

import org.example.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryServices categoryService;

    //CREATE
    @PostMapping
    public CategoryEntity createCategory(CategoryCreateDto category) {
        return categoryService.createCategory(category);
    }

    // READ
    @GetMapping
    public List<CategoryEntity> fetchCategoryList() {
        return categoryService.fetchCategoryList();
    }


    // UPDATE
    @PutMapping("/{id}")
    public CategoryEntity updateCategory(Integer id, CategoryCreateDto category) {
        return categoryService.updateCategory(category, id);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteCategory(Integer id) {
        categoryService.deleteCategoryById(id);
        return "Category deleted!";
    }

}