package fr.insta.cinemax.servlet;

import fr.insta.cinemax.manager.HttpSessionManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("cart", HttpSessionManager.getCartFromSession(request.getSession()));

		RequestDispatcher disp = request.getRequestDispatcher("cart.jsp");
		disp.forward(request,  response);
	}

}
