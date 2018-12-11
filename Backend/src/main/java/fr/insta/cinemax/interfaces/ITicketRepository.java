package fr.insta.cinemax.interfaces;

import fr.insta.cinemax.model.Ticket;

import java.util.List;

public interface ITicketRepository {

	Ticket buyTicket(Integer userId, Integer movieId);
	List<Ticket> ticketsForMovie(Integer movieId);
	List<Ticket> ticketsForUser(Integer userId);

}