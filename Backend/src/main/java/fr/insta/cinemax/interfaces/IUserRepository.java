package fr.insta.cinemax.interfaces;

import fr.insta.cinemax.exceptions.AccountNotFoundException;
import fr.insta.cinemax.exceptions.WrongPasswordException;
import fr.insta.cinemax.model.User;


public interface IUserRepository {

	User getUserById(Integer id);
	User create(User user);
	User login(String email, String password) throws AccountNotFoundException, WrongPasswordException;

}
