package es.salesianos.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.salesianos.model.PeliculaActor;
import es.salesianos.service.PeliculaActorService;

public class SearchRoleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private PeliculaActorService service = new PeliculaActorService();

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
