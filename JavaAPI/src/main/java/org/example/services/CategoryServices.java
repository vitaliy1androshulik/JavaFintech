package org.example.services;

import org.example.dto.category.CategoryCreateDto;
import org.example.entities.CategoryEntity;
import org.example.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class CategoryServices {
    @Autowired
    private ICategoryRepository repository;

    public CategoryEntity createCategory(CategoryCreateDto category) {
        CategoryEntity catDB = new CategoryEntity();
        catDB.setName(category.getName());
        catDB.setImage(category.getImage());
        catDB.setDescription(category.getDescription());
        catDB.setCreationTime(LocalDateTime.now());
        return repository.save(catDB);
    }

    public List<CategoryEntity> fetchCategoryList() {
        return repository.findAll();
    }

    public CategoryEntity updateCategory(CategoryCreateDto category, Integer Id) {
        CategoryEntity catDB = repository.findById(Id).get();
        catDB.setName(category.getName());
        catDB.setImage(category.getImage());
        catDB.setDescription(category.getDescription());
        return repository.save(catDB);
    }

    public void deleteCategoryById(Integer Id) {
        repository.deleteById(Id);
    }
}

