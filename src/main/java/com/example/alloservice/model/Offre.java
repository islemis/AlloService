package com.example.alloservice.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor @Setter @Getter
@Entity
@Table(name = "offres")
public class Offre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String titre;
	private Float price;
	private String description;
	private String details;
	private String adresse;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToMany
	@JoinTable(
			name = "offre_category",
			joinColumns = @JoinColumn(name = "offre_id"),
			inverseJoinColumns = @JoinColumn(name = "category_id")
	)
	private Set<Category> categories = new HashSet<>();

	@OneToMany(mappedBy = "offre", cascade = CascadeType.ALL)
	private Set<ImageData> images = new HashSet<>();
}