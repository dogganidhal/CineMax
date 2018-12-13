package fr.insta.cinemax.servlet;

import fr.insta.cinemax.exceptions.NotEnoughSpaceException;
import fr.insta.cinemax.interfaces.ITicketRepository;
import fr.insta.cinemax.manager.HttpSessionManager;
import fr.insta.cinemax.model.Cart;
import fr.insta.cinemax.model.CartElement;
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

@WebServlet(name = "CartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {

	private ITicketRepository ticketRepository = RepositoryFactory.getInstance().createTicketRepository();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		User user = HttpSessionManager.getUserFromSession(request.getSession());

		if (user == null) {
			response.sendRedirect("/");
			return;
		}

		Cart cart = HttpSessionManager.getCartFromSession(request.getSession());

		try {

			for (CartElement cartElement: cart.getCartElements()) {
				for (int index = 0; index < cartElement.getCount(); index++) {
					Ticket ticket = ticketRepository.buyTicket(user.getId(), cartElement.getSession().getId());
				}
			}

			HttpSessionManager.deleteCart(request.getSession());

		} catch (NotEnoughSpaceException e) {

			request.setAttribute("errorMessage", e.getMessage());

			RequestDispatcher disp = request.getRequestDispatcher("cart.jsp");
			disp.forward(request,  response);

			return;

		}

		response.sendRedirect("/tickets");

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("cart", HttpSessionManager.getCartFromSession(request.getSession()));
		request.setAttribute("errorMessage", "");

		RequestDispatcher disp = request.getRequestDispatcher("cart.jsp");
		disp.forward(request,  response);
	}

}
