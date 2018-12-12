package fr.insta.cinemax.repositories;

import fr.insta.cinemax.interfaces.*;

public class RepositoryFactory {

	private static RepositoryFactory instance = new RepositoryFactory();

	public static RepositoryFactory getInstance() {
		return RepositoryFactory.instance;
	}

	public IUserRepository createUserRepository() {
		return new UserRepository();
	}

	public IMovieRepository createMovieRepository() {
		return new MovieRepository();
	}

	public ISessionRepository createSessionRepository() {
		return new SessionRepository();
	}

	public ITicketRepository createTicketRepository() {
		return new TicketRepository();
	}

	public IRoomRepository createRoomeRepository() {
		return new RoomRepository();
	}

}
