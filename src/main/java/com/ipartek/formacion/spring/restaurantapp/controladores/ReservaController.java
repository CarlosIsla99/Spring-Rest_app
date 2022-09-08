package com.ipartek.formacion.spring.restaurantapp.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipartek.formacion.spring.restaurantapp.entidades.Reserva;
import com.ipartek.formacion.spring.restaurantapp.servicios.ReservaService;

@Controller
@RequestMapping("/reserva")
public class ReservaController {
	@Autowired
	private ReservaService servicio;
	
	@GetMapping("/listar")
	public String listar(Model modelo) {
		modelo.addAttribute("reservas", servicio.listar());	
		return "listado";
	}
	
	@GetMapping("/crear")
	public String crear(Reserva reserva) {
		return "reserva";
	}
	
	@PostMapping("/crear")
	public String crear(@Valid Reserva reserva, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "reserva";
		}
		servicio.crear(reserva);
		
		return "redirect:/reserva/listar";
	}
	
	@GetMapping("/borrar/{id}")
	public String borrar(@PathVariable Long id) {
		servicio.borrar(id);
		return "redirect:/reserva/listar";
	}
	
	@GetMapping("/formulario/{id}")
	public String mostrarFormularioConReserva(@PathVariable Long id, Model modelo) {
		modelo.addAttribute("reserva", servicio.obtenerPorId(id).get());
		return "reserva";
	}
	
	@PostMapping("/formulario/{id}")
	public String recibirFormulario(@Valid Reserva reserva, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "redirect:/reserva/formulario/{id}";
		}
		servicio.crear(reserva);

		return "redirect:/reserva/listar";
	}
}
