package fr.insta.cinemax.repositories;

import fr.insta.cinemax.manager.ConnectionManager;
import fr.insta.cinemax.model.Movie;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieRepositoryTest {

	private MovieRepository repository = new MovieRepository();
	private Integer testMovieId;
	private boolean didCreateTestMovie = false;

	void createTestMovies() throws SQLException {

		Connection connection = ConnectionManager.getInstance().getConnection();
		String insertStatement = "INSERT INTO movie (title, version, vision, duration) VALUES (?,?,?,?);";
		PreparedStatement preparedStatement = connection.prepareStatement(insertStatement, PreparedStatement.RETURN_GENERATED_KEYS);

		preparedStatement.setString(1, "SPIDERMAN");
		preparedStatement.setString(2, "VOSTFR");
		preparedStatement.setString(3, "2d");
		preparedStatement.setDouble(4, 120);

		preparedStatement.executeUpdate();

		ResultSet resultSet = preparedStatement.getGeneratedKeys();

		if (resultSet.next())
			this.testMovieId = resultSet.getInt(1);

		this.didCreateTestMovie = true;

	}

	@Test
	void getMovies() throws SQLException {

		if (!this.didCreateTestMovie)
			this.createTestMovies();

		List<Movie> movies = this.repository.getMovies();

		assertTrue(movies.size() >= 1);
		assertEquals(1, movies.stream().filter(movie -> movie.getId().equals(this.testMovieId)).count());

	}

	@Test
	void getMovieById() throws SQLException {

		if (!this.didCreateTestMovie)
			this.createTestMovies();

		Movie movieFromDatabase = this.repository.getMovieById(this.testMovieId);

		assertEquals(movieFromDatabase.getDuration(), Double.valueOf(120.0));
		assertEquals(movieFromDatabase.getTitle(), "SPIDERMAN");
		assertEquals(movieFromDatabase.getVision(), "2d");
		assertEquals(movieFromDatabase.getVersion(), "VOSTFR");

	}

}