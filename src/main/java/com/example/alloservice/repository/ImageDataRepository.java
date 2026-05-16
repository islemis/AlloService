package com.example.alloservice.repository;

import com.example.alloservice.model.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ImageDataRepository extends JpaRepository<ImageData, Long> {
    List<ImageData> findByOffreId(Long offreId);
}