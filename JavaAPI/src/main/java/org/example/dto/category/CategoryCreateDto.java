package org.example.dto.category;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CategoryCreateDto{
    private String name;
    private MultipartFile imageFile;
    private String description;
}