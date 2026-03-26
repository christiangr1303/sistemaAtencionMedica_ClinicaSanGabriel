package com.sangabriel.dev.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sangabriel.dev.models.Cita;
import com.sangabriel.dev.models.Medico;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long>{
	
	boolean existsByMedicoAndHoraInicioLessThanAndHoraFinalGreaterThan(
			Medico medico, LocalDateTime horaFinal, LocalDateTime horaInicio);
	
}
