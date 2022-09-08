package com.ipartek.formacion.spring.restaurantapp.servicios;

import java.util.Optional;

import com.ipartek.formacion.spring.restaurantapp.entidades.Reserva;

public interface ReservaService {
	Iterable<Reserva> listar();
	Optional<Reserva> obtenerPorId(Long id);
	Reserva crear(Reserva reserva);
	void borrar(Long id);
}
