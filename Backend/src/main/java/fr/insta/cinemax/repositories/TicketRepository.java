package fr.insta.cinemax.repositories;

import fr.insta.cinemax.exceptions.NotEnoughSpaceException;
import fr.insta.cinemax.interfaces.ITicketRepository;
import fr.insta.cinemax.manager.ConnectionManager;
import fr.insta.cinemax.mappers.TicketMapper;
import fr.insta.cinemax.model.Movie;
import fr.insta.cinemax.model.Session;
import fr.insta.cinemax.model.Ticket;
import fr.insta.cinemax.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class TicketRepository implements ITicketRepository {

	@Override
	public Ticket buyTicket(Integer userId, Integer sessionId) {

		try {

			Connection connection = ConnectionManager.getInstance().getConnection();
			String insertStatement = "INSERT INTO ticket (session_id, user_id, price, created) VALUES (?,?,?,?);";
			PreparedStatement preparedStatement = connection.prepareStatement(insertStatement);

			Double price = 9.99;
			Date creationDate = Date.from(Instant.now());

			preparedStatement.setInt(1, sessionId);
			preparedStatement.setInt(2, userId);
			preparedStatement.setDouble(3, price);
			preparedStatement.setDate(4, new java.sql.Date(creationDate.getTime()));

			preparedStatement.executeUpdate();

			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			UserRepository userRepository = new UserRepository();
			SessionRepository sessionRepository = new SessionRepository();

			if (resultSet.next()) {

				User user = userRepository.getUserById(userId);
				Session session = sessionRepository.getSessionById(sessionId);

				return new Ticket(
					resultSet.getInt("id"),
					session,
					user,
					price,
					creationDate
				);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public List<Ticket> buyTickets(Integer userId, Integer sessionId, Integer count) throws NotEnoughSpaceException {

		try {

			Connection connection = ConnectionManager.getInstance().getConnection();
			String insertStatement = "INSERT INTO ticket (session_id, user_id, price, created) VALUES (?,?,?,?);";
			PreparedStatement preparedStatement = connection.prepareStatement(insertStatement);

			Double price = 9.99;
			Date creationDate = Date.from(Instant.now());

			preparedStatement.setInt(1, sessionId);
			preparedStatement.setInt(2, userId);
			preparedStatement.setDouble(3, price);
			preparedStatement.setDate(4, new java.sql.Date(creationDate.getTime()));

			preparedStatement.executeUpdate();

			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			UserRepository userRepository = new UserRepository();
			SessionRepository sessionRepository = new SessionRepository();

			User user = userRepository.getUserById(userId);
			Session session = sessionRepository.getSessionById(sessionId);

			List<Ticket> tickets = new ArrayList<>();

			session = sessionRepository.incrementTicketCountOfSession(session, count);

			while (resultSet.next()) {

				tickets.add(new Ticket(
					resultSet.getInt("id"),
					session,
					user,
					price,
					creationDate
				));

			}

			return tickets;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public List<Ticket> ticketsForMovie(Integer movieId) {

		try {

			Connection connection = ConnectionManager.getInstance().getConnection();
			String selectStatement = "SELECT id FROM session WHERE movie_id = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);

			preparedStatement.setInt(1, movieId);

			ResultSet resultSet = preparedStatement.executeQuery();
			List<Integer> movieSessionIds = new ArrayList<>();

			while (resultSet.next())
				movieSessionIds.add(resultSet.getInt("id"));

			selectStatement = "SELECT * FROM ticket WHERE session_id in (?);";
			preparedStatement = connection.prepareStatement(selectStatement);
			preparedStatement.setArray(1, connection.createArrayOf("INTEGER", movieSessionIds.toArray()));
			resultSet = preparedStatement.executeQuery();

			TicketMapper mapper = new TicketMapper();
			SessionRepository sessionRepository = new SessionRepository();
			UserRepository userRepository = new UserRepository();

			List<Ticket> tickets = new ArrayList<>();

			while (resultSet.next()) {

				Ticket ticket = mapper.map(resultSet);
				Session session = sessionRepository.getSessionById(resultSet.getInt("session_id"));
				User user = userRepository.getUserById(resultSet.getInt("user_id"));

				tickets.add(new Ticket(
					ticket.getId(),
					session,
					user,
					ticket.getPrice(),
					ticket.getCreatedDate()
				));

			}

			return tickets;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public List<Ticket> ticketsForUser(Integer userId) {

		try {

			Connection connection = ConnectionManager.getInstance().getConnection();
			String selectStatement = "SELECT * FROM ticket WHERE user_id = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);

			preparedStatement.setInt(1, userId);

			ResultSet resultSet = preparedStatement.executeQuery();
			UserRepository userRepository = new UserRepository();
			SessionRepository sessionRepository = new SessionRepository();
			List<Ticket> tickets = new ArrayList<>();
			TicketMapper mapper = new TicketMapper();

			if (resultSet.next()) {

				Ticket ticket = mapper.map(resultSet);
				Integer sessionId = resultSet.getInt("session_id");
				User user = userRepository.getUserById(userId);
				Session session = sessionRepository.getSessionById(sessionId);

				tickets.add(new Ticket(
					ticket.getId(),
					session,
					user,
					ticket.getPrice(),
					ticket.getCreatedDate()
				));

			}

			return tickets;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

}