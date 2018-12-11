package fr.insta.cinemax.repositories;

import fr.insta.cinemax.exceptions.AccountNotFoundException;
import fr.insta.cinemax.exceptions.WrongPasswordException;
import fr.insta.cinemax.interfaces.IUserRepository;
import fr.insta.cinemax.manager.ConnectionManager;
import fr.insta.cinemax.model.User;

import java.sql.Connection;
import java.sql.SQLException;


public class UserRepository implements IUserRepository {

	@Override
	public User create(User user) {
		return null;
	}

	@Override
	public User login(String email, String password) throws AccountNotFoundException, WrongPasswordException {

		try {

			Connection connection = ConnectionManager.getInstance().getConnection();


		} catch (SQLException e) {

			e.printStackTrace();

		}

		return null;

	}

}
