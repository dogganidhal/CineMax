package fr.insta.cinemax.servlet;

import fr.insta.cinemax.exceptions.AccountNotFoundException;
import fr.insta.cinemax.exceptions.WrongPasswordException;
import fr.insta.cinemax.interfaces.IUserRepository;
import fr.insta.cinemax.manager.HttpSessionManager;
import fr.insta.cinemax.model.User;
import fr.insta.cinemax.repositories.RepositoryFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;


@WebServlet(name = "AuthServlet", urlPatterns = {"/auth"})
public class AuthServlet extends HttpServlet {

	private IUserRepository userRepository = RepositoryFactory.getInstance().createUserRepository();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String authType = request.getParameter("auth-type");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		if (authType.equals("login")) {

			try {

				User user = this.userRepository.login(email, password);
				HttpSessionManager.setUserForSession(request.getSession(), user);

				response.sendRedirect("/");

			} catch (AccountNotFoundException | WrongPasswordException e) {

				request.setAttribute("errorMessage", e.getMessage());

				RequestDispatcher disp = request.getRequestDispatcher("auth.jsp");
				disp.forward(request,  response);

			}

		} else if (authType.equals("sign-up")) {

			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String birthDate = request.getParameter("birthDate");

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			try {

				User user = this.userRepository.create(new User(
					firstName,
					lastName,
					email,
					password,
					dateFormat.parse(birthDate))
				);

				HttpSessionManager.setUserForSession(request.getSession(), user);

				response.sendRedirect("/");

			} catch (ParseException e) {
				e.printStackTrace();
			}

		} else if (authType.equals("sign-out")) {

			request.getSession().invalidate();
			response.sendRedirect("/");

		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		request.setAttribute("errorMessage", "");

		RequestDispatcher disp = request.getRequestDispatcher("auth.jsp");
		disp.forward(request,  response);

	}

}
