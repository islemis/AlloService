package com.example.alloservice.repository;

import com.example.alloservice.model.Offre;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OffreRepository extends JpaRepository<Offre, Long> {
    List<Offre> findByUserId(Long userId);
    List<Offre> findByCategoriesId(Long categoryId);
    List<Offre> findByTitreContainingIgnoreCase(String keyword);
}