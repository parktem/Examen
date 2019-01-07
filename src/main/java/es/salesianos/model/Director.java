package es.salesianos.model;

import java.util.List;

public class Director extends BaseEntity{
	
	private String name;
	private List<Pelicula> peliculas;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Pelicula> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(List<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}

}
