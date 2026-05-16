package com.example.alloservice.dto;

import lombok.*;
import java.util.Date;
import java.util.Set;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class OffreDto {
	private Long id;
	private String titre;
	private Float price;
	private String description;
	private String details;
	private String adresse;
	private Date date;
	private String fournisseurUsername;
	private String fournisseurPhone;       // client appelle ce numéro
	private Set<CategoryDto> categories;   // ← objets Category complets, pas juste les IDs
	private Set<String> imageUrls;
}