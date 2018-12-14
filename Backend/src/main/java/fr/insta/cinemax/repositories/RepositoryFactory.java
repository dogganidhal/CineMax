package fr.insta.cinemax.repositories;

import fr.insta.cinemax.interfaces.*;

public class RepositoryFactory {

	private static RepositoryFactory instance = new RepositoryFactory();

	public static RepositoryFactory getInstance() {
		return RepositoryFactory.instance;
	}

	public IUserRepository createUserRepository() {
		return new UserRepositoryImpl();
	}

	public IMovieRepository createMovieRepository() {
		return new MovieRepositoryImpl();
	}

	public ISessionRepository createSessionRepository() {
		return new SessionRepositoryImpl();
	}

	public ITicketRepository createTicketRepository() {
		return new TicketRepositoryImpl();
	}

	public IRoomRepository createRoomRepository() {
		return new RoomRepositoryImpl();
	}

	public IStatsRepository createStatsRepository() {
		return new StatsRepositoryImpl();
	}

}
