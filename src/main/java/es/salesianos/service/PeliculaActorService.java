package es.salesianos.service;


import es.salesianos.model.PeliculaActor;
import es.salesianos.repository.PeliculaActorRepository;

public class PeliculaActorService {

	PeliculaActorRepository repository = new PeliculaActorRepository();
	
	public PeliculaActor filterAllPeliculaActor(String role) {
		return repository.filterAllPeliculaActor(role);
	}
	
	public void insert(PeliculaActor peliculaActor) {
		repository.insert(peliculaActor);;
	}
	
}
