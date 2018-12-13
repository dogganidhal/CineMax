package fr.insta.cinemax.repositories;

import fr.insta.cinemax.exceptions.AccountNotFoundException;
import fr.insta.cinemax.exceptions.WrongPasswordException;
import fr.insta.cinemax.interfaces.IUserRepository;
import fr.insta.cinemax.manager.ConnectionManager;
import fr.insta.cinemax.mappers.UserMapper;
import fr.insta.cinemax.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRepositoryImpl implements IUserRepository {

	@Override
	public User getUserById(Integer id) {

		try {

			Connection connection = ConnectionManager.getInstance().getConnection();
			String selectStatement = "SELECT * FROM USER WHERE id = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement, PreparedStatement.RETURN_GENERATED_KEYS);

			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				UserMapper mapper = new UserMapper();
				return mapper.map(resultSet);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public User create(User user) {

		try {

			Connection connection = ConnectionManager.getInstance().getConnection();
			String selectStatement = "INSERT INTO USER (first_name, last_name, email, password, birth_date) VALUES (?,?,?,?,?);";
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement, PreparedStatement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.setDate(5, new java.sql.Date(user.getBirthDate().getTime()));

			preparedStatement.executeUpdate();

			ResultSet resultSet = preparedStatement.getGeneratedKeys();

			if (resultSet.next())
				return new User(
					resultSet.getInt(1),
					user.getFirstName(),
					user.getLastName(),
					user.getEmail(),
					user.getPassword(),
					user.getBirthDate()
				);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public User login(String email, String password) throws AccountNotFoundException, WrongPasswordException {

		try {

			Connection connection = ConnectionManager.getInstance().getConnection();
			String selectStatement = "SELECT * FROM user WHERE email = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);

			preparedStatement.setString(1, email);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				UserMapper mapper = new UserMapper();
				User user = mapper.map(resultSet);

				if (!user.getPassword().equals(password))
					throw new WrongPasswordException();

				return user;

			}

		} catch (SQLException ignored) {}

		throw new AccountNotFoundException(email);

	}

}
