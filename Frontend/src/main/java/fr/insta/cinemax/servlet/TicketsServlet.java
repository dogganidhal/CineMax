package fr.insta.cinemax.servlet;

import fr.insta.cinemax.interfaces.ITicketRepository;
import fr.insta.cinemax.manager.HttpSessionManager;
import fr.insta.cinemax.model.Ticket;
import fr.insta.cinemax.model.User;
import fr.insta.cinemax.repositories.RepositoryFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TicketsServlet", urlPatterns = {"/tickets"})
public class TicketsServlet extends HttpServlet {

	private ITicketRepository ticketRepository = RepositoryFactory.getInstance().createTicketRepository();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User user = HttpSessionManager.getUserFromSession(request.getSession());

		if (user == null) {
			response.sendRedirect("/");
			return;
		}

		List<Ticket> tickets = this.ticketRepository.ticketsForUser(user.getId());

		request.setAttribute("tickets", tickets);

		RequestDispatcher disp = request.getRequestDispatcher("tickets.jsp");
		disp.forward(request,  response);

	}

}
