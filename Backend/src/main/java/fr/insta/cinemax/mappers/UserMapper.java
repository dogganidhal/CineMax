package fr.insta.cinemax.mappers;

import fr.insta.cinemax.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements IEntityMapper<User> {

	@Override
	public User map(ResultSet resultSet) throws SQLException {
		return new User(
			resultSet.getInt( "id"),
			resultSet.getString("first_name"),
			resultSet.getString( "last_name"),
			resultSet.getString("email"),
			resultSet.getString("password"),
			resultSet.getDate("birth_date")
		);
	}

}
