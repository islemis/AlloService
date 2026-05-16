package com.example.alloservice.service;

import com.example.alloservice.dto.*;
import com.example.alloservice.model.*;
import com.example.alloservice.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OffreService {

    private final OffreRepository offreRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    public OffreDto create(OffreRequest req, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Offre offre = new Offre();
        offre.setTitre(req.getTitre());
        offre.setPrice(req.getPrice());
        offre.setDescription(req.getDescription());
        offre.setDetails(req.getDetails());
        offre.setAdresse(req.getAdresse());
        offre.setDate(new Date());
        offre.setUser(user);

        if (req.getCategoryIds() != null && !req.getCategoryIds().isEmpty()) {
            Set<Category> cats = new HashSet<>(categoryRepository.findAllById(req.getCategoryIds()));
            offre.setCategories(cats);
        }

        return toDto(offreRepository.save(offre));
    }

    public List<OffreDto> getAll() {
        return offreRepository.findAll().stream().map(this::toDto).toList();
    }

    public OffreDto getById(Long id) {
        return toDto(offreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offre not found")));
    }

    public List<OffreDto> getByUser(Long userId) {
        return offreRepository.findByUserId(userId).stream().map(this::toDto).toList();
    }

    public List<OffreDto> getByCategory(Long categoryId) {
        return offreRepository.findByCategoriesId(categoryId).stream().map(this::toDto).toList();
    }

    public List<OffreDto> search(String keyword) {
        return offreRepository.findByTitreContainingIgnoreCase(keyword)
                .stream().map(this::toDto).toList();
    }

    public OffreDto update(Long id, OffreRequest req) {
        Offre offre = offreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offre not found"));
        offre.setTitre(req.getTitre());
        offre.setPrice(req.getPrice());
        offre.setDescription(req.getDescription());
        offre.setDetails(req.getDetails());
        offre.setAdresse(req.getAdresse());
        if (req.getCategoryIds() != null) {
            Set<Category> cats = new HashSet<>(categoryRepository.findAllById(req.getCategoryIds()));
            offre.setCategories(cats);
        }
        return toDto(offreRepository.save(offre));
    }

    public void delete(Long id) {
        offreRepository.deleteById(id);
    }

    private OffreDto toDto(Offre o) {
        OffreDto Dto = new OffreDto();
        Dto.setId(o.getId());
        Dto.setTitre(o.getTitre());
        Dto.setPrice(o.getPrice());
        Dto.setDescription(o.getDescription());
        Dto.setDetails(o.getDetails());
        Dto.setAdresse(o.getAdresse());
        Dto.setDate(o.getDate());
        Dto.setFournisseurUsername(o.getUser() != null ? o.getUser().getUsername() : null);
        Dto.setFournisseurPhone(o.getUser() != null ? o.getUser().getPhone() : null);
        Dto.setCategories(o.getCategories().stream()
                .map(categoryService::toDTO)
                .collect(Collectors.toSet()));
        Dto.setImageUrls(o.getImages().stream()
                .map(img -> "/api/images/" + img.getId())
                .collect(Collectors.toSet()));
        return Dto;
    }
}