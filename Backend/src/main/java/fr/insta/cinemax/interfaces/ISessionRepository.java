package fr.insta.cinemax.interfaces;

import fr.insta.cinemax.exceptions.NotEnoughSpaceException;
import fr.insta.cinemax.model.Session;

import java.util.List;


public interface ISessionRepository {

	Session getSessionById(Integer id);
	List<Session> getSessionsForMovie(Integer movieId);
	Session incrementTicketCountOfSession(Session session) throws NotEnoughSpaceException;
	Double getPriceForUser(Integer userId);

}
