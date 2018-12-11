package fr.insta.cinemax.repositories;

import fr.insta.cinemax.manager.ConnectionManager;
import fr.insta.cinemax.model.Movie;
import fr.insta.cinemax.model.Room;
import fr.insta.cinemax.model.Session;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SessionRepositoryTest {

	private SessionRepository repository = new SessionRepository();
	private Movie testMovie;

	private void createTestSessions() throws SQLException, ParseException {

		RoomRepository roomRepository = new RoomRepository();
		MovieRepository movieRepository = new MovieRepository();

		Room testRoom = roomRepository.create(new Room("Salle 100"));
		this.testMovie = movieRepository.create(new Movie("Dragon Ball", "VOSTFR", "3d", 123.0));

		Connection connection = ConnectionManager.getInstance().getConnection();
		String selectStatement = "INSERT INTO SESSION (start_date, movie_id, room_id) VALUES (?,?,?);";
		PreparedStatement preparedStatement = connection.prepareStatement(selectStatement, PreparedStatement.RETURN_GENERATED_KEYS);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date sessionStartDate = dateFormat.parse("2019-01-10 22:00");

		preparedStatement.setDate(1, new java.sql.Date(sessionStartDate.getTime()));
		preparedStatement.setInt(2, this.testMovie.getId());
		preparedStatement.setInt(3, testRoom.getId());

		preparedStatement.executeUpdate();

	}

	@Test
	void getSessionsForMovie() throws SQLException, ParseException {

		createTestSessions();

		List<Session> sessionsForMovie = this.repository.getSessionsForMovie(this.testMovie.getId());

		assertEquals(sessionsForMovie.size(), 1);

		assertEquals(sessionsForMovie.get(0).getMovie().getId(), this.testMovie.getId());
		assertEquals(sessionsForMovie.get(0).getMovie().getTitle(), this.testMovie.getTitle());
		assertEquals(sessionsForMovie.get(0).getMovie().getVersion(), this.testMovie.getVersion());
		assertEquals(sessionsForMovie.get(0).getMovie().getVision(), this.testMovie.getVision());
		assertEquals(sessionsForMovie.get(0).getMovie().getDuration(), this.testMovie.getDuration());

	}

}