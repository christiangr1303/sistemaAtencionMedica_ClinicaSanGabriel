package com.sangabriel.dev.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sangabriel.dev.models.Cita;
import com.sangabriel.dev.models.Especialidad;
import com.sangabriel.dev.models.Medico;
import com.sangabriel.dev.models.Paciente;
import com.sangabriel.dev.repository.CitaRepository;
import com.sangabriel.dev.repository.EspecialidadRepository;
import com.sangabriel.dev.repository.MedicoRepository;
import com.sangabriel.dev.repository.PacienteRepository;

import jakarta.transaction.Transactional;

@Service
public class CitaService {

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private EspecialidadRepository especialidadRepository;

	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private CitaRepository citaRepository;

	@Transactional
	public Cita registrar(String dni, Long especialidad_id, Long medico_id, LocalDateTime horaInicio,
			LocalDateTime horaFinal) {

		// Validar horario
		// Que no la hora final no sea menor a la hora inicial ni viceversa
		if (horaInicio.isAfter(horaFinal)) {
			throw new IllegalArgumentException("Horario invalido");
		}
		// Que no se agenden citas pasadas a la hora actual
		if (horaInicio.isBefore(LocalDateTime.now())) {
			throw new IllegalArgumentException("No se pueden registrar citas en el pasado");
		}

		Medico medico = medicoRepository.findById(medico_id)
				.orElseThrow(() -> new RuntimeException("Medico no identificado"));

		// Validar que el medico E especialidad
		if (!medico.getEspecialidad().getId().equals(especialidad_id)) {
			throw new RuntimeException("El medico no pertenece a la especialidad seleccionada");
		}

		// Que no se solapen las citas con un mismo medico
		boolean ocupado = citaRepository.existsByMedicoAndHoraInicioLessThanAndHoraFinalGreaterThan(medico, horaFinal,
				horaInicio);
		if (ocupado) {
			throw new IllegalStateException("El medico ya tiene una cita en ese horario");
		}

		Paciente paciente = pacienteRepository.findByDni(dni)
				.orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

		Especialidad especialidad = especialidadRepository.findById(especialidad_id)
				.orElseThrow(() -> new RuntimeException("Especialidad no reconocida"));

		Cita cita = new Cita();
		cita.setPaciente(paciente);
		cita.setEspecialidad(especialidad);
		cita.setMedico(medico);
		cita.setHoraInicio(horaInicio);
		cita.setHoraFinal(horaFinal);

		return citaRepository.save(cita);
	}

}
