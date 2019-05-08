package com.slash.videotutorial.spring.rest.domain;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude="equipos")
public class Liga {

	private Short id;
	private String nombre;
	private Set<Equipo> equipos = new HashSet<>();
	
	public Liga() { }
	
	public Liga(String nombre) {
		this.nombre = nombre;
	}
	
	public Liga(Short id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	public void addEquipo(Equipo equipo) {
		equipos.add(equipo);
	}
	
	public void eliminarEquipo(Equipo equipo) {
		equipos.remove(equipo);
	}

	@Override
	public String toString() {
		return "Liga [id=" + id + ", nombre=" + nombre + "]";
	}
}
