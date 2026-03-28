package com.sangabriel.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sangabriel.dev.models.Especialidad;
import com.sangabriel.dev.models.Medico;
import com.sangabriel.dev.repository.MedicoRepository;

import jakarta.transaction.Transactional;

@Service
public class MedicoService {
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	public Medico buscar(String nroColegiatura) {
		return medicoRepository.findByNroColegiatura(nroColegiatura)
				.orElse(null);
	}
	
	@Transactional
	public Medico registrar(String nroColegiatura, String nombre, 
			String apePat, String apeMat, String telefono, String correo, Especialidad especialidad) {
		
		// Validaciones
		if (nroColegiatura == null || nroColegiatura.isBlank()) {
			throw new IllegalArgumentException("El nro de colegiatura es obligatorio");
		}
		
		if (nombre == null || nombre.isBlank()) {
			throw new IllegalArgumentException("El nombre es obligatorio");
		}
		
		if (especialidad == null) {
			throw new IllegalArgumentException("La especialidad es obligatoria");
		}
		
		// Validar que no haya medico con el mismo nroColegiatura
		if (buscar(nroColegiatura) != null) {
			throw new RuntimeException("El medico se encuentra registrado");
		}
		
		// Creamos medico
		Medico medico = new Medico();
		
		medico.setNroColegiatura(nroColegiatura);
		medico.setNombre(nombre);
		medico.setApePat(apePat);
		medico.setApeMat(apeMat);
		medico.setTelefono(telefono);
		medico.setCorreo(correo);
		medico.setEspecialidad(especialidad);
		
		return medicoRepository.save(medico);
	}
	
}
