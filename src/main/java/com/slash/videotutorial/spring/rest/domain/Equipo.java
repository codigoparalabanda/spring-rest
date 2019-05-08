package com.slash.videotutorial.spring.rest.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class Equipo {

	private Short id;
	private String nombre;
	
	@JsonIgnore
	public Liga liga;
	
	public Equipo() { }

	public Equipo(Short id, String nombre, Liga liga) {
		this.id = id;
		this.nombre = nombre;
		this.liga = liga;
	}

	@Override
	public String toString() {
		return "Equipo [id=" + id + ", nombre=" + nombre + ", liga=" + liga + "]";
	}
}
