package com.ipartek.formacion.spring.restaurantapp.servicios;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ipartek.formacion.spring.restaurantapp.entidades.Reserva;

public class ReservaServiceTest {
	@Autowired
	private ReservaService servicio;
	
	@Autowired
	private JdbcTemplate jdbc;
	
	private List<Object[]> registros;
	
	@BeforeEach
	void antesDeCadaMetodo() {
		registros = new ArrayList<>();
		
		registros.add(new Object[] {1L, "Javi", null, "123456789", LocalDateTime.now(), 2, null});
		registros.add(new Object[] {2L, "Pepe", null, "987654321", LocalDateTime.now(), 3, null});
		registros.add(new Object[] {3L, "Juan", null, "147384729", LocalDateTime.now(), 5, null});
		registros.add(new Object[] {4L, "Pedro", null, "184637463", LocalDateTime.now(), 2, null});
		
		jdbc.execute("TRUNCATE reservas");
		jdbc.batchUpdate("INSERT INTO reservas (id, nombre, email, telefono, fecha_hora, numero_personas, comentarios) VALUES (?,?,?,?,?,?,?)", registros);
	}
	
	@Test
	void listar() {
		Iterable<Reserva> reservas = servicio.listar();
		
		Iterator<Reserva> iterator = reservas.iterator();
		int contador = 0;
		
		while(iterator.hasNext()) {
			iterator.next();
			contador++;
			}
		
		assertNotNull(reservas);
		assertEquals(4, contador);
	}
	
	@Test
	void crear() {
		Reserva reserva = new Reserva(null, "Javi", null, "123456789", LocalDateTime.now(), 2, null);
		Reserva recibida = servicio.crear(reserva);
		
		assertNotNull(recibida);
		assertNotNull(recibida.getId());
		assertEquals(reserva, recibida);
	}
	

}
