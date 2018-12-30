package es.salesianos.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.salesianos.model.Actor;
import es.salesianos.model.assembler.ActorAssembler;
import es.salesianos.service.Service;
import es.salesianos.service.Service;

public class ActorServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;

	private Service service = new Service();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Actor actor = ActorAssembler.assembleActorFrom(req);
		service.insert(actor);
		doAction(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String codString = req.getParameter("cod");
		if(codString != null) {
			Actor actor = new Actor();
			int cod = Integer.parseInt(codString);
			actor.setCod(cod);
			service.delete(actor);
		}
		doAction(req, resp);
	}

	private void doAction(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		List<Actor> listAllActores = service.selectAllActor();
		req.setAttribute("listAllActores", listAllActores);
		redirect(req, resp);
	}

	protected void redirect(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/actor.jsp");
		dispatcher.forward(req, resp);
	}
}
