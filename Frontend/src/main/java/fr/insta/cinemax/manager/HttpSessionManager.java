package fr.insta.cinemax.manager;

import com.sun.istack.internal.Nullable;
import fr.insta.cinemax.model.CartElement;
import fr.insta.cinemax.model.User;

import javax.servlet.http.HttpSession;
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

	public static List<CartElement> getCartFromSession(HttpSession session) {

		try {
			return (List<CartElement>)session.getAttribute(HttpSessionManager.CURRENT_CART_ATTRIBUTE_NAME);
		} catch (ClassCastException ignored) {}

		return null;

	}

	public static void setCartForSession(HttpSession session, List<CartElement> cart) {
		session.setAttribute(HttpSessionManager.CURRENT_CART_ATTRIBUTE_NAME, cart);
	}

}
