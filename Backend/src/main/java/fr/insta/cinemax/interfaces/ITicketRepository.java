package fr.insta.cinemax.interfaces;

import fr.insta.cinemax.exceptions.NotEnoughSpaceException;
import fr.insta.cinemax.model.Session;
import fr.insta.cinemax.model.Ticket;
import fr.insta.cinemax.model.User;

import java.util.List;

public interface ITicketRepository {

	Ticket buyTicket(User user, Session session) throws NotEnoughSpaceException;
	List<Ticket> ticketsForMovie(Integer movieId);
	List<Ticket> ticketsForUser(Integer userId);

}