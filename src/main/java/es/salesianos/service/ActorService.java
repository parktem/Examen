package es.salesianos.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import es.salesianos.model.Actor;
import es.salesianos.model.Director;
import es.salesianos.repository.ActorRepository;

public class ActorService {

	private ActorRepository repository = new ActorRepository();
	
	
	public List<Actor> filterAllActor(HttpServletRequest req) {
		int beginDate = Integer.parseInt(req.getParameter("beginDate"));
		int endDate = Integer.parseInt(req.getParameter("endDate"));
		return repository.filterAllActor(beginDate, endDate);
	}
	
	public List<Actor> selectAllActor() {
		return repository.selectAllActor();
	}
	
	public void insert(Actor actor) {
		repository.insert(actor);
	}
	
	public void delete(Actor actor) {
		repository.delete(actor);
	}
	
	public Director filterAllDirector(String name) {
		return repository.filterAllDirector(name);
	}
	
}
