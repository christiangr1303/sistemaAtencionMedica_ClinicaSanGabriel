package com.sangabriel.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sangabriel.dev.models.Paciente;
import com.sangabriel.dev.repository.PacienteRepository;

@Service
public class PacienteService {
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	public Paciente buscar(String dni) {
		return pacienteRepository.findByDni(dni).orElse(null);
	}
	
	public void registrar(Paciente paciente) {
		
		if (buscar(paciente.getDni()) != null) {
			System.out.println("El paciente se encuentra registrado");
		} else {
			pacienteRepository.save(paciente);
		}
		
	}
	
}
