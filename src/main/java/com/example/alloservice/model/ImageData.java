package com.example.alloservice.model;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "images")
public class ImageData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String type;

	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] imageData;

	@ManyToOne
	@JoinColumn(name = "offre_id")
	private Offre offre;
}