package fr.insta.cinemax.repositories;

import fr.insta.cinemax.interfaces.IMovieRepository;
import fr.insta.cinemax.manager.ConnectionManager;
import fr.insta.cinemax.mappers.MovieMapper;
import fr.insta.cinemax.model.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MovieRepositoryImpl implements IMovieRepository {

	@Override
	public Movie getMovieById(Integer id) {

		try {

			Connection connection = ConnectionManager.getInstance().getConnection();
			String selectStatement = "SELECT * FROM movie WHERE id = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);

			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();
			MovieMapper mapper = new MovieMapper();

			if (resultSet.next())
				return mapper.map(resultSet);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public List<Movie> getMovies() {

		try {

			Connection connection = ConnectionManager.getInstance().getConnection();
			String selectStatement = "SELECT * FROM movie;";
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);

			ResultSet resultSet = preparedStatement.executeQuery();
			MovieMapper mapper = new MovieMapper();
			List<Movie> movies = new ArrayList<>();

			while (resultSet.next())
				movies.add(mapper.map(resultSet));

			return movies;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new ArrayList<>();
	}

	@Override
	public Movie create(Movie movie) {

		try {

			Connection connection = ConnectionManager.getInstance().getConnection();
			String selectStatement = "INSERT INTO movie (title, version, vision, duration) VALUES (?,?,?,?);";
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement, PreparedStatement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, movie.getTitle());
			preparedStatement.setString(2, movie.getVersion());
			preparedStatement.setString(3, movie.getVision());
			preparedStatement.setDouble(4, movie.getDuration());

			preparedStatement.executeUpdate();

			ResultSet resultSet = preparedStatement.getGeneratedKeys();

			if (resultSet.next())
				return new Movie(
					resultSet.getInt(1),
					movie.getTitle(),
					movie.getVersion(),
					movie.getVision(),
					movie.getDuration()
				);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

}
