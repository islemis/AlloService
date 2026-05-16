package com.example.alloservice.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "offre")
public class Offre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_offre")
	private Long idOffre;

	@Column(name = "titre")
	private String titre;

	@Column(name = "price")
	private Float price;

	@Column(name = "description")
	private String description;

	@Column(name = "details")
	private String details;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date")
	private Date date;

	@Column(name = "adresse")
	private String adresse;

	@OneToMany(mappedBy = "offre", cascade = CascadeType.ALL)
	private Set<ImageData> images = new HashSet<>();

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

	@Override
	public String toString() {
		return "Offre [id=" + idOffre + ", titre=" + titre + ", price=" + price
				+ ", description=" + description + ", details=" + details
				+ ", date=" + date + ", adresse=" + adresse + "]";
	}
}