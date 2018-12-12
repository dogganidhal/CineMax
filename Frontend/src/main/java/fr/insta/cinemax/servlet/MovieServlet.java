package fr.insta.cinemax.servlet;

import fr.insta.cinemax.interfaces.IMovieRepository;
import fr.insta.cinemax.interfaces.ISessionRepository;
import fr.insta.cinemax.model.Movie;
import fr.insta.cinemax.model.Session;
import fr.insta.cinemax.repositories.RepositoryFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "MovieServlet", urlPatterns = {"/movie"})
public class MovieServlet extends HttpServlet {

	private IMovieRepository movieRepository = RepositoryFactory.getInstance().createMovieRepository();
	private ISessionRepository sessionRepository = RepositoryFactory.getInstance().createSessionRepository();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String movieId = request.getParameter("id");
		Movie movie = this.movieRepository.getMovieById(Integer.parseInt(movieId));
		List<Session> sessions = sessionRepository.getSessionsForMovie(Integer.parseInt(movieId));

		request.setAttribute("movie", movie);
		request.setAttribute("sessions", sessions);

		RequestDispatcher dispatcher = request.getRequestDispatcher("movie.jsp");
		dispatcher.forward(request,  response);

	}

}
