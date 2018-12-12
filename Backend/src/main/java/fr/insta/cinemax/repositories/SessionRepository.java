package fr.insta.cinemax.repositories;

import fr.insta.cinemax.exceptions.NotEnoughSpaceException;
import fr.insta.cinemax.interfaces.ISessionRepository;
import fr.insta.cinemax.manager.ConnectionManager;
import fr.insta.cinemax.manager.PriceManager;
import fr.insta.cinemax.mappers.SessionMapper;
import fr.insta.cinemax.mappers.UserMapper;
import fr.insta.cinemax.model.Movie;
import fr.insta.cinemax.model.Room;
import fr.insta.cinemax.model.Session;
import fr.insta.cinemax.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SessionRepository implements ISessionRepository {

	@Override
	public Session getSessionById(Integer id) {

		try {

			Connection connection = ConnectionManager.getInstance().getConnection();
			String selectStatement = "SELECT * FROM session WHERE id = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement, PreparedStatement.RETURN_GENERATED_KEYS);

			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				MovieRepository movieRepository = new MovieRepository();
				RoomRepository roomRepository = new RoomRepository();
				SessionMapper mapper = new SessionMapper();

				Movie movie = movieRepository.getMovieById(resultSet.getInt("movie_id"));
				Room room = roomRepository.getRoomById(resultSet.getInt("room_id"));
				Session session = mapper.map(resultSet);

				return new Session(
					session.getId(),
					session.getStartDate(),
					room,
					movie,
					session.getTicketCount()
				);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public List<Session> getSessionsForMovie(Integer movieId) {

		try {

			Connection connection = ConnectionManager.getInstance().getConnection();
			String selectStatement = "SELECT * FROM session WHERE movie_id = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);

			preparedStatement.setInt(1, movieId);

			ResultSet resultSet = preparedStatement.executeQuery();
			SessionMapper mapper = new SessionMapper();
			List<Session> sessions = new ArrayList<>();

			RoomRepository roomRepository = new RoomRepository();
			MovieRepository movieRepository = new MovieRepository();

			while (resultSet.next()) {

				Session session = mapper.map(resultSet);
				Integer roomId = resultSet.getInt("room_id");

				Room room = roomRepository.getRoomById(roomId);

				sessions.add(new Session(
					session.getId(),
					session.getStartDate(),
					room,
					movieRepository.getMovieById(movieId),
					session.getTicketCount()
				));

			}

			return sessions;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Session incrementTicketCountOfSession(Session session, Integer tickets) throws NotEnoughSpaceException {

		try {

			Connection connection = ConnectionManager.getInstance().getConnection();
			String updateStatement = "UPDATE session SET ticket_count = ? WHERE id = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(updateStatement);

			preparedStatement.setInt(1, session.getTicketCount() + tickets);
			preparedStatement.setInt(2, session.getId());

			if (preparedStatement.execute()) {

				return new Session(
					session.getId(),
					session.getStartDate(),
					session.getRoom(),
					session.getMovie(),
					session.getTicketCount() + tickets
				);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return session;

	}

	@Override
	public Double getPriceForUser(Integer userId) {

		try {

			Connection connection = ConnectionManager.getInstance().getConnection();
			String selectStatement = "SELECT * FROM user WHERE id = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);

			preparedStatement.setInt(1, userId);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				UserMapper mapper = new UserMapper();
				User user = mapper.map(resultSet);

				return PriceManager.getInstance().computePriceForUser(user);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

}
