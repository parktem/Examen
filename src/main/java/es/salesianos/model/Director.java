package es.salesianos.model;

import java.util.List;

public class Director extends BaseEntity{
	
	private String nombre;
	private List<Pelicula> peliculas;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Pelicula> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(List<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}

}
