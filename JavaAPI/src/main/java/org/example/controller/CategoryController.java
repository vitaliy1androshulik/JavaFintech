package org.example.controller;

import org.example.dto.category.CategoryCreateDto;
import org.example.dto.category.CategoryEditDto;
import org.example.entities.CategoryEntity;

import org.example.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryServices categoryService;

    //CREATE
    @PostMapping
    public CategoryEntity createCategory(@RequestBody CategoryCreateDto category) {
        return categoryService.createCategory(category);
    }

    // READ
    @GetMapping
    public List<CategoryEntity> fetchCategoryList() {
        return categoryService.fetchCategoryList();
    }


    // UPDATE
    @PutMapping
    public CategoryEntity updateCategory(@RequestBody CategoryEditDto category) {
        return categoryService.updateCategory(category);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CategoryEntity> getCategoryById(@PathVariable Integer id) {
        Optional<CategoryEntity> category = categoryService.getCategoryById(id);
        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    // DELETE
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable int id) {
        categoryService.deleteCategoryById(id);
    }

}