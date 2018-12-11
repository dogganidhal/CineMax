package fr.insta.cinemax.repositories;

import fr.insta.cinemax.manager.ConnectionManager;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieRepositoryTest {

	private MovieRepository repository = new MovieRepository();

	void createTestMovies() throws SQLException {

		Connection connection = ConnectionManager.getInstance().getConnection();
		String selectStatement = "INSERT INTO movie (title, version, vision, duration) VALUES (?,?,?,?);";
		PreparedStatement preparedStatement = connection.prepareStatement(selectStatement, PreparedStatement.RETURN_GENERATED_KEYS);

		preparedStatement.setString(1, "SPIDERMAN");
		preparedStatement.setString(2, "VOSTFR");
		preparedStatement.setString(3, "2d");
		preparedStatement.setDouble(4, 120);

		preparedStatement.executeUpdate();

	}

	@Test
	void getMovies() throws SQLException {

		this.createTestMovies();

		List movies = this.repository.getMovies();

		assertTrue(movies.size() >= 1);

	}

}