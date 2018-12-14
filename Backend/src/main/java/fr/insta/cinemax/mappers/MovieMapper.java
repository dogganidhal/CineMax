package fr.insta.cinemax.mappers;

import fr.insta.cinemax.model.Movie;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieMapper implements IEntityMapper<Movie> {

	@Override
	public Movie map(ResultSet resultSet) throws SQLException {
		return new Movie(
			resultSet.getInt("id"),
			resultSet.getString("title"),
			resultSet.getString("version"),
			resultSet.getString("description"),
			resultSet.getDouble("duration")
		);
	}

}
