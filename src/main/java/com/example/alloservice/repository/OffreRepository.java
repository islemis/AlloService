package com.example.alloservice.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Offre;

@Repository

public interface OffreRepository  extends JpaRepository<Offre, Long>{

	
	

}
