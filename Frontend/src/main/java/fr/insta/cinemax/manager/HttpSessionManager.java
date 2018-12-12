package fr.insta.cinemax.manager;

import com.sun.istack.internal.Nullable;
import fr.insta.cinemax.model.User;

import javax.servlet.http.HttpSession;

public class HttpSessionManager {

	private static String CURRENT_USER_ATTRIBUTE_NAME = "CurrentUser";

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

}
