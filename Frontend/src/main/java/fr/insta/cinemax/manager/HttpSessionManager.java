package fr.insta.cinemax.manager;

import com.sun.istack.internal.Nullable;
import fr.insta.cinemax.model.Cart;
import fr.insta.cinemax.model.Ticket;
import fr.insta.cinemax.model.User;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class HttpSessionManager {

	private static String CURRENT_USER_ATTRIBUTE_NAME = "CurrentUser";
	private static String CURRENT_CART_ATTRIBUTE_NAME = "CurrentCart";

	@Nullable
	public static User getUserFromSession(HttpSession session) {

		try {
			return (User)session.getAttribute(HttpSessionManager.CURRENT_USER_ATTRIBUTE_NAME);
		} catch (ClassCastException ignored) { }

		return null;

	}

	public static void setUserForSession(HttpSession session, User user) {
		session.setAttribute(HttpSessionManager.CURRENT_USER_ATTRIBUTE_NAME, user);
	}

	public static Cart getCartFromSession(HttpSession session) {

		Object cart = session.getAttribute(HttpSessionManager.CURRENT_CART_ATTRIBUTE_NAME);

		if (cart == null)
			return new Cart(new ArrayList<>(), 0.0);

		try {
			return (Cart) cart;
		} catch (ClassCastException ignored) {}

		return new Cart(new ArrayList<>(), 0.0);

	}

	public static void setCartForSession(HttpSession session, Cart cart) {
		session.setAttribute(HttpSessionManager.CURRENT_CART_ATTRIBUTE_NAME, cart);
	}

	public static void deleteCart(HttpSession session) {
		session.removeAttribute(HttpSessionManager.CURRENT_CART_ATTRIBUTE_NAME);
	}

}
