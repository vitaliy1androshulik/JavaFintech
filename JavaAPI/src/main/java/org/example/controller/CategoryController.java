package org.example.controller;

import org.example.dto.category.CategoryCreateDto;
import org.example.dto.category.CategoryItemDto;
import org.example.entities.CategoryEntity;
import org.example.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryServices categoryService;

    @GetMapping
    public List<CategoryItemDto> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryItemDto getCategoryById(@PathVariable Integer id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping(consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CategoryEntity> createCategory(@ModelAttribute CategoryCreateDto category) {
        CategoryEntity createdCategory = categoryService.createCategory(category);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateCategory(@PathVariable Integer id, @ModelAttribute CategoryCreateDto category) {
        return categoryService.updateCategory(id, category) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        return categoryService.deleteCategory(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}