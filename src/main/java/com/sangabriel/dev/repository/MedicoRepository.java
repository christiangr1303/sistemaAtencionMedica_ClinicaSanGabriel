package com.sangabriel.dev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sangabriel.dev.models.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long>{
	
	Optional<Medico> findByNroColegiatura(String nroColegiatura);
	
}
