package es.salesianos.model.assembler;

import javax.servlet.http.HttpServletRequest;

import es.salesianos.model.FilmActor;

public class PeliculaActorAssembler {

	public static FilmActor assemblePeliculaActorFrom(HttpServletRequest req) {
		FilmActor peliculaActor = new FilmActor();
		String codPelicula = req.getParameter("codPelicula");
		String codActor = req.getParameter("codActor");
		String cache = req.getParameter("cache");
		String role = req.getParameter("role");
		peliculaActor.setCodPelicula(Integer.parseInt(codPelicula));
		peliculaActor.setCodActor(Integer.parseInt(codActor));
		peliculaActor.setCache(Integer.parseInt(cache));
		peliculaActor.setRole(role);
		return peliculaActor;
	}
	
}
