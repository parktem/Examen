package es.salesianos.service;

import java.util.List;

import es.salesianos.model.Actor;
import es.salesianos.model.Pelicula;
import es.salesianos.model.PeliculaActor;
import es.salesianos.repository.ActorRepository;
import es.salesianos.repository.PeliculaRepository;

public class PeliculaService {

	PeliculaRepository repository = new PeliculaRepository();
	
	public List<Pelicula> selectAllPelicula() {
		return repository.selectAllPelicula();
	}
	
	public void insert(Pelicula pelicula) {
		repository.insert(pelicula);
	}
	
	public void delete(Pelicula pelicula) {
		repository.delete(pelicula);
	}
	
}
