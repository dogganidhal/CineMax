package fr.insta.cinemax.servlet;

import fr.insta.cinemax.interfaces.IStatsRepository;
import fr.insta.cinemax.repositories.RepositoryFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StatsServlet", urlPatterns = {"/stats"})
public class StatsServlet extends HttpServlet {

	private IStatsRepository statsRepository = RepositoryFactory.getInstance().createStatsRepository();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("stats", this.statsRepository.getStats());

		RequestDispatcher dispatcher = request.getRequestDispatcher("stats.jsp");
		dispatcher.forward(request,  response);

	}

}
