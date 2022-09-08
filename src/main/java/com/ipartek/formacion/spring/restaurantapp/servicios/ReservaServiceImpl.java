package com.ipartek.formacion.spring.restaurantapp.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.spring.restaurantapp.entidades.Reserva;
import com.ipartek.formacion.spring.restaurantapp.repositorios.ReservaRepository;

import lombok.extern.java.Log;

@Log
@Service
public class ReservaServiceImpl implements ReservaService {

	@Autowired
	private ReservaRepository repo;
	
	@Override
	public Iterable<Reserva> listar() {
		log.info("Se ha pedido un listado de reservas");
		return repo.findAll();
	}

	@Override
	public Reserva crear(Reserva reserva) {
		log.info("Se va a crear una reserva: " + reserva);
		Reserva nueva = repo.save(reserva);
		log.info("Se ha creado la reserva: " + nueva);
		
		return nueva;
	}

	@Override
	public void borrar(Long id) {
		log.info("Se va a borrar la reserva con ID: " + id);
		repo.deleteById(id);
		log.info("Se ha borrado la reserva con ID: " + id);
	}

	@Override
	public Optional<Reserva> obtenerPorId(Long id) {
		log.info("Se ha pedido la reserva con ID: " + id);
		return repo.findById(id);
	}

}
