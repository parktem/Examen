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
import es.salesianos.service.ActorService;

public class ActorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ActorService service = new ActorService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Actor actor = ActorAssembler.assembleActorFrom(req);
		service.insert(actor);
		doAction(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String codString = req.getParameter("cod");
		if (codString != null) {
			Actor actor = new Actor();
			int cod = Integer.parseInt(codString);
			actor.setCod(cod);
			service.delete(actor);
		}
		doAction(req, resp);
	}

	private void doAction(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String parameter = req.getParameter("beginDate");
		if (parameter != null) {
			List<Actor> listAllActors = service.filterAllActor(req);
			req.setAttribute("listAllActores", listAllActors);
		}
		List<Actor> listAllActors = service.selectAllActor();
		req.setAttribute("listAllActors", listAllActors);
		redirect(req, resp);
	}

	protected void redirect(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/actor.jsp");
		dispatcher.forward(req, resp);
	}
}
