package fr.insta.cinemax.repositories;

import fr.insta.cinemax.interfaces.ITicketRepository;
import fr.insta.cinemax.manager.ConnectionManager;
import fr.insta.cinemax.mappers.TicketMapper;
import fr.insta.cinemax.model.Movie;
import fr.insta.cinemax.model.Ticket;
import fr.insta.cinemax.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TicketRepository implements ITicketRepository {

	@Override
	public Ticket buyTicket(Integer userId, Integer movieId) {

		try {

			Connection connection = ConnectionManager.getInstance().getConnection();
			String insertStatement = "INSERT INTO ticket (movie_id, user_id, price, created) VALUES (?,?,?,?);";
			PreparedStatement preparedStatement = connection.prepareStatement(insertStatement);

			Double price = 9.99;
			Date creationDate = Date.from(Instant.now());

			preparedStatement.setInt(1, movieId);
			preparedStatement.setInt(2, userId);
			preparedStatement.setDouble(3, price);
			preparedStatement.setDate(4, new java.sql.Date(creationDate.getTime()));

			preparedStatement.executeUpdate();

			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			UserRepository userRepository = new UserRepository();
			MovieRepository movieRepository = new MovieRepository();

			if (resultSet.next()) {

				User user = userRepository.getUserById(userId);
				Movie movie = movieRepository.getMovieById(movieId);

				return new Ticket(
					resultSet.getInt("id"),
					movie,
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
	public List<Ticket> ticketsForMovie(Integer movieId) {

		try {

			Connection connection = ConnectionManager.getInstance().getConnection();
			String selectStatement = "SELECT * FROM ticket WHERE movie_id = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);

			preparedStatement.setInt(1, movieId);

			ResultSet resultSet = preparedStatement.executeQuery();
			UserRepository userRepository = new UserRepository();
			MovieRepository movieRepository = new MovieRepository();
			List<Ticket> tickets = new ArrayList<>();
			TicketMapper mapper = new TicketMapper();

			if (resultSet.next()) {

				Ticket ticket = mapper.map(resultSet);
				Integer userId = resultSet.getInt("user_id");
				User user = userRepository.getUserById(userId);
				Movie movie = movieRepository.getMovieById(movieId);

				tickets.add(new Ticket(
					ticket.getId(),
					movie,
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
			MovieRepository movieRepository = new MovieRepository();
			List<Ticket> tickets = new ArrayList<>();
			TicketMapper mapper = new TicketMapper();

			if (resultSet.next()) {

				Ticket ticket = mapper.map(resultSet);
				Integer movieId = resultSet.getInt("movie_id");
				User user = userRepository.getUserById(userId);
				Movie movie = movieRepository.getMovieById(movieId);

				tickets.add(new Ticket(
					ticket.getId(),
					movie,
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
