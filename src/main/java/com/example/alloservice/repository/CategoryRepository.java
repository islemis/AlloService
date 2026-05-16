package com.example.alloservice.repository;

import com.example.alloservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	boolean existsByName(String name);
}