package fr.insta.cinemax.interfaces;

import fr.insta.cinemax.model.Ticket;

public interface ITicketRepository {

	Integer computeMoviePrice(Integer userId);
	Ticket buyTicket(Integer userId, Integer movieId);

}