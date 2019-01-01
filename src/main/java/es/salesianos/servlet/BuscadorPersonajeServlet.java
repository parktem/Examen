package es.salesianos.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.salesianos.model.Actor;
import es.salesianos.model.Pelicula;
import es.salesianos.model.PeliculaActor;
import es.salesianos.model.assembler.PeliculaActorAssembler;
import es.salesianos.model.assembler.PeliculaAssembler;
import es.salesianos.service.Service;
import es.salesianos.service.Service;

public class BuscadorPersonajeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Service service = new Service();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doAction(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doAction(req, resp);
	}

	private void doAction(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String role = req.getParameter("role");
		if (role != null) {
			PeliculaActor selectPeliculaActor = service.filterAllPeliculaActor(role);
			req.setAttribute("selectPeliculaActor", selectPeliculaActor);
		}
		redirect(req, resp);
	}

	protected void redirect(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/buscador.jsp");
		dispatcher.forward(req, resp);
	}
}