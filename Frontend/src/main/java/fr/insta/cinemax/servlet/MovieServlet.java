package fr.insta.cinemax.servlet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fr.insta.cinemax.interfaces.IMovieRepository;
import fr.insta.cinemax.interfaces.ISessionRepository;
import fr.insta.cinemax.manager.HttpSessionManager;
import fr.insta.cinemax.model.*;
import fr.insta.cinemax.repositories.RepositoryFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@WebServlet(name = "MovieServlet", urlPatterns = {"/movie"})
public class MovieServlet extends HttpServlet {

	private IMovieRepository movieRepository = RepositoryFactory.getInstance().createMovieRepository();
	private ISessionRepository sessionRepository = RepositoryFactory.getInstance().createSessionRepository();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String movieId = request.getParameter("id");
		Movie movie = this.movieRepository.getMovieById(Integer.parseInt(movieId));
		List<Session> sessions = sessionRepository.getSessionsForMovie(Integer.parseInt(movieId));

		sessions.sort(Comparator.comparing(Session::getStartDate));

		User currentUser = HttpSessionManager.getUserFromSession(request.getSession());
		Double unitPrice = currentUser != null ? this.sessionRepository.getPriceForUser(currentUser.getId()) : this.sessionRepository.getPriceForUser(null);

		request.setAttribute("movie", movie);
		request.setAttribute("sessions", sessions);
		request.setAttribute("unitPrice", unitPrice);

		RequestDispatcher dispatcher = request.getRequestDispatcher("movie.jsp");
		dispatcher.forward(request,  response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String serializedCart = request.getParameter("cart");
		List<CartDTO> cartDTOs = new Gson().fromJson(serializedCart, new TypeToken<List<CartDTO>>(){}.getType());

		Cart cart = HttpSessionManager.getCartFromSession(request.getSession());
		User user = HttpSessionManager.getUserFromSession(request.getSession());

		if (user == null) {
			response.sendRedirect("/");
			return;
		}

		Double total = 0.0;
		List<CartElement> cartElements = new ArrayList<>();

		for (CartDTO cartDTO: cartDTOs) {
			cartElements.add(new CartElement(
							this.sessionRepository.getSessionById(cartDTO.getSessionId()),
							cartDTO.getNumberOfTickets())
			);
			assert user != null;
			total += cartDTO.getNumberOfTickets() * this.sessionRepository.getPriceForUser(user.getId());
		}

		cart = cart.merge(new Cart(cartElements, total));
		HttpSessionManager.setCartForSession(request.getSession(), cart);

		response.sendRedirect("/cart");

	}
}
