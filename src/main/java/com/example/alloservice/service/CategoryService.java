package com.example.alloservice.service;

import com.example.alloservice.dto.CategoryDto;
import com.example.alloservice.model.Category;
import com.example.alloservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryDto create(String name) {
        if (categoryRepository.existsByName(name))
            throw new RuntimeException("Category already exists");
        Category cat = new Category();
        cat.setName(name);
        return toDTO(categoryRepository.save(cat));
    }

    public List<CategoryDto> getAll() {
        return categoryRepository.findAll().stream().map(this::toDTO).toList();
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    public CategoryDto toDTO(Category c) {
        return new CategoryDto(c.getId(), c.getName());
    }
}