package fr.insta.cinemax.repositories;

import fr.insta.cinemax.interfaces.ISessionRepository;
import fr.insta.cinemax.manager.ConnectionManager;
import fr.insta.cinemax.mappers.MovieMapper;
import fr.insta.cinemax.mappers.SessionMapper;
import fr.insta.cinemax.model.Movie;
import fr.insta.cinemax.model.Room;
import fr.insta.cinemax.model.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SessionRepository implements ISessionRepository {

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

}
