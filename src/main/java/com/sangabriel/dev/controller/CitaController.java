package com.sangabriel.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sangabriel.dev.dto.CitaRequestDTO;
import com.sangabriel.dev.models.Cita;
import com.sangabriel.dev.service.CitaService;

@Controller
@RequestMapping("/citas")
public class CitaController {

	@Autowired
	private CitaService citaService;
	
	@GetMapping("/nueva")
	public String mostrarFormulario(Model model) {
		Cita cita = new Cita();
		model.addAttribute("cita", cita);
		return "citas/GenerarCita";
	}
	
	@PostMapping
	public String guardar(@ModelAttribute CitaRequestDTO citaDTO, RedirectAttributes redirect) {
		try {
			citaService.registrar(
					citaDTO.getDni(),
					citaDTO.getEspecialidadId(), 
					citaDTO.getMedicoId(), 
					citaDTO.getHoraInicio(),
					citaDTO.getHoraFinal());
			redirect.addFlashAttribute("success", "Cita registrada correctamente");
		} catch (Exception e){
			redirect.addFlashAttribute("error", e.getMessage());
		}
		return "redirect:/cita/nueva";
	}
	
}
