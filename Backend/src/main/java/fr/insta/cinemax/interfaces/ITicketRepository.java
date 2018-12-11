package fr.insta.cinemax.interfaces;

import fr.insta.cinemax.exceptions.NotEnoughSpaceException;
import fr.insta.cinemax.model.Ticket;

import java.util.List;

public interface ITicketRepository {

	Ticket buyTicket(Integer userId, Integer sessionId) throws NotEnoughSpaceException;
	List<Ticket> buyTickets(Integer userId, Integer sessionId, Integer count) throws NotEnoughSpaceException;
	List<Ticket> ticketsForMovie(Integer movieId);
	List<Ticket> ticketsForUser(Integer userId);

}