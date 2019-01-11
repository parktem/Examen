package es.salesianos.service;

import java.util.List;

import es.salesianos.model.Film;
import es.salesianos.repository.FilmRepository;

public class FilmService {

	FilmRepository repository = new FilmRepository();
	
	public List<Film> selectAllFilm() {
		return repository.selectAllFilm();
	}
	
	public void insert(Film film) {
		repository.insert(film);
	}
	
	public void delete(Film film) {
		repository.delete(film);
	}
	
}
