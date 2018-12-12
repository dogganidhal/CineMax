package fr.insta.cinemax.servlet;

import fr.insta.cinemax.interfaces.IMovieRepository;
import fr.insta.cinemax.manager.HttpSessionManager;
import fr.insta.cinemax.model.User;
import fr.insta.cinemax.repositories.RepositoryFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Home", urlPatterns = {"/"})
public class HomeServlet extends HttpServlet {

	private IMovieRepository movieRepository = RepositoryFactory.getInstance().createMovieRepository();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("movies", this.movieRepository.getMovies());

		RequestDispatcher disp = request.getRequestDispatcher("home.jsp");
		disp.forward(request,  response);

	}

}
