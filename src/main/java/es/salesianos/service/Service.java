package es.salesianos.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import es.salesianos.model.Actor;
import es.salesianos.model.Director;
import es.salesianos.model.Pelicula;
import es.salesianos.model.PeliculaActor;
import es.salesianos.model.assembler.DirectorAssembler;
import es.salesianos.repository.Repository;

public class Service {

	private Repository repository = new Repository();

	public Director assembleDirectorFromRequest(HttpServletRequest req) {
		return DirectorAssembler.assembleDirectorFrom(req);
	}

	public List<Actor> filterAllActor(int beginDate, int endDate) {
		return repository.filterAllActor(beginDate, endDate);
	}

	public List<Actor> selectAllActor() {
		return repository.selectAllActor();
	}

	public List<Director> selectAllDirector() {
		return repository.selectAllDirector();
	}

	public List<Pelicula> selectAllPelicula() {
		return repository.selectAllPelicula();
	}

	public void insert(Actor actor) {
		repository.insert(actor);

	}

	public void insert(Pelicula pelicula) {
		repository.insert(pelicula);

	}

	public void insert(Director director) {
		repository.insert(director);
	}
	
	public void insert(PeliculaActor peliculaActor) {
		repository.insert(peliculaActor);;
	}

	public void delete(Actor actor) {
		repository.delete(actor);

	}

	public void delete(Pelicula pelicula) {
		repository.delete(pelicula);

	}

	public void delete(Director director) {
		repository.delete(director);
	}

}
