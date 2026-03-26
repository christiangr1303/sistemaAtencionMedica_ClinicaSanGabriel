package com.sangabriel.dev.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class HorarioAtencion {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="medico_id")
	private Medico medico;
	
	private LocalDateTime horarioInicioAtencion;
	private LocalDateTime horarioFinalAtencion;
	
	private Boolean activo;
	private LocalDateTime fechaCreacion = LocalDateTime.now();
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public LocalDateTime getHorarioInicioAtencion() {
		return horarioInicioAtencion;
	}
	public void setHorarioInicioAtencion(LocalDateTime horarioInicioAtencion) {
		this.horarioInicioAtencion = horarioInicioAtencion;
	}
	public LocalDateTime getHorarioFinalAtencion() {
		return horarioFinalAtencion;
	}
	public void setHorarioFinalAtencion(LocalDateTime horarioFinalAtencion) {
		this.horarioFinalAtencion = horarioFinalAtencion;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
}
