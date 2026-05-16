package com.example.alloservice.dto;


import jakarta.validation.constraints.*;
import lombok.*;
import java.util.Set;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class OffreRequest {
    @NotBlank private String titre;
    @NotNull  private Float price;
    private String description;
    private String details;
    private String adresse;
    private Set<Long> categoryIds;  // on envoie les IDs pour créer la relation
    // la réponse (OffreDTO) retourne les objets complets
}