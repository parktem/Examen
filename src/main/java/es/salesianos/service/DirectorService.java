package es.salesianos.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import es.salesianos.model.Director;
import es.salesianos.model.assembler.DirectorAssembler;
import es.salesianos.repository.DirectorRepository;

public class DirectorService {
	
	DirectorRepository repository = new DirectorRepository();

	public Director assembleDirectorFromRequest(HttpServletRequest req) {
		return DirectorAssembler.assembleDirectorFrom(req);
	}
	
	public List<Director> selectAllDirector() {
		return repository.selectAllDirector();
	}
	
	public void insert(Director director) {
		repository.insert(director);
	}
	
	public void delete(Director director) {
		repository.delete(director);
	}
	
}
