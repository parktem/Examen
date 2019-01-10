package es.salesianos.service;


import es.salesianos.model.DtoActorFilm;
import es.salesianos.model.FilmActor;
import es.salesianos.repository.PeliculaActorRepository;

public class PeliculaActorService {

	PeliculaActorRepository repository = new PeliculaActorRepository();
	
	public DtoActorFilm filterAllPeliculaActor(String role) {
		return repository.filterAllPeliculaActor(role);
	}
	
	public void insert(FilmActor peliculaActor) {
		repository.insert(peliculaActor);;
	}
	
}
