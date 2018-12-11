package fr.insta.cinemax.mappers;

import fr.insta.cinemax.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements IEntityMapper<User> {

	@Override
	public User map(ResultSet resultSet) throws SQLException {
		return this.map(resultSet, "");
	}

	@Override
	public User map(ResultSet resultSet, String keyPrefix) throws SQLException {
		return new User(
			resultSet.getInt(keyPrefix + "id"),
			resultSet.getString(keyPrefix + "first_name"),
			resultSet.getString(keyPrefix + "last_name"),
			resultSet.getString(keyPrefix + "email"),
			resultSet.getString(keyPrefix + "password"),
			resultSet.getDate(keyPrefix + "birth_date")
		);
	}
}
