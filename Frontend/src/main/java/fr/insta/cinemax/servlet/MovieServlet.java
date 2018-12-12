package fr.insta.cinemax.servlet;

import fr.insta.cinemax.interfaces.IMovieRepository;
import fr.insta.cinemax.interfaces.ISessionRepository;
import fr.insta.cinemax.manager.HttpSessionManager;
import fr.insta.cinemax.manager.PriceManager;
import fr.insta.cinemax.model.Movie;
import fr.insta.cinemax.model.Session;
import fr.insta.cinemax.model.User;
import fr.insta.cinemax.repositories.RepositoryFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Collectors;


@WebServlet(name = "MovieServlet", urlPatterns = {"/movie"})
public class MovieServlet extends HttpServlet {

	private IMovieRepository movieRepository = RepositoryFactory.getInstance().createMovieRepository();
	private ISessionRepository sessionRepository = RepositoryFactory.getInstan-ce().createSessionRepository();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String movieId = request.getParameter("id");
		Movie movie = this.movieRepository.getMovieById(Integer.parseInt(movieId));
		List<Session> sessions = sessionRepository.getSessionsForMovie(Integer.parseInt(movieId));

		sessions.sort(Comparator.comparing(Session::getStartDate));

		User currentUser = HttpSessionManager.getUserFromSession(request.getSession());

		Double unitPrice = currentUser != null ? this.sessionRepository.getPriceForUser(currentUser.getId()) : Double.MAX_VALUE;

		request.setAttribute("movie", movie);
		request.setAttribute("sessions", sessions);
		request.setAttribute("unitPrice", unitPrice);

		RequestDispatcher dispatcher = request.getRequestDispatcher("movie.jsp");
		dispatcher.forward(request,  response);

	}

}
