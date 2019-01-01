package es.salesianos.model;

import java.util.ArrayList;
import java.util.List;

public class Actor extends Director{

	
	private Integer year;
	private List<PeliculaActor> peliculaActor = new ArrayList<PeliculaActor>();

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public List<PeliculaActor> getPeliculaActor() {
		return peliculaActor;
	}

	public void setPeliculaActor(List<PeliculaActor> peliculaActor) {
		this.peliculaActor = peliculaActor;
	}


}
