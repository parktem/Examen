package es.salesianos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import es.salesianos.service.FilmService;

@Controller
public class FilmController {

	@Autowired
	FilmService service;

	@GetMapping
	public ModelAndView getFilms() {
		ModelAndView model = new ModelAndView("film");
		model.addObject("listAllFilms", service.selectAllFilm());
		return model;
	}

}
