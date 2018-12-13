package fr.insta.cinemax.repositories;

import fr.insta.cinemax.exceptions.AccountNotFoundException;
import fr.insta.cinemax.exceptions.WrongPasswordException;
import fr.insta.cinemax.model.User;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


class UserRepositoryImplTest {

	private UserRepositoryImpl repository = new UserRepositoryImpl();

	@Test
	void getUserById() throws ParseException {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date birthDate = dateFormat.parse("1990-11-11");
		User testUser = this.repository.create(new User(
			this.generateRandomString(10),
			this.generateRandomString(10),
			this.generateRandomString(10) + "@mail.com",
			this.generateRandomString(10),
			birthDate
		));
		User userFromDatabase = this.repository.getUserById(testUser.getId());

		assertNotNull(userFromDatabase);
		assertEquals(testUser.getId(), userFromDatabase.getId());
		assertEquals(testUser.getFirstName(), userFromDatabase.getFirstName());
		assertEquals(testUser.getLastName(), userFromDatabase.getLastName());
		assertEquals(testUser.getPassword(), userFromDatabase.getPassword());
		assertEquals(testUser.getEmail(), userFromDatabase.getEmail());
		assertEquals(testUser.getBirthDate(), userFromDatabase.getBirthDate());

	}

	@Test
	void create() throws ParseException {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date birthDate = dateFormat.parse("1990-11-11");
		User testUser = this.repository.create(new User(
			this.generateRandomString(10),
			this.generateRandomString(10),
			this.generateRandomString(10) + "@mail.com",
			this.generateRandomString(10),
			birthDate
		));

		assertNotNull(this.repository.getUserById(testUser.getId()));

	}

	@Test
	void login_ReturnsUserWhenValidPasswordAndEmailAreGiven() throws ParseException {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date birthDate = dateFormat.parse("1990-11-11");
		String email = this.generateRandomString(10) + "@mail.com";
		String password = "qwerty2018";

		User testUser = this.repository.create(new User(
			this.generateRandomString(10),
			this.generateRandomString(10),
			email,
			password,
			birthDate
		));

		try {

			User userFromDatabase = this.repository.login(email, password);

			assertNotNull(userFromDatabase);
			assertEquals(testUser.getId(), userFromDatabase.getId());
			assertEquals(testUser.getFirstName(), userFromDatabase.getFirstName());
			assertEquals(testUser.getLastName(), userFromDatabase.getLastName());
			assertEquals(testUser.getPassword(), userFromDatabase.getPassword());
			assertEquals(testUser.getEmail(), userFromDatabase.getEmail());
			assertEquals(testUser.getBirthDate(), userFromDatabase.getBirthDate());

		} catch (Exception e) {
			fail();
		}

	}

	@Test
	void login_ThrowsWrongPasswordWhenDoesNotMatchRecords() throws ParseException {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date birthDate = dateFormat.parse("1990-11-11");
		final String email = this.generateRandomString(10) + "@mail.com";
		String password = "qwerty2018";

		this.repository.create(new User(
			this.generateRandomString(10),
			this.generateRandomString(10),
			email,
			password,
			birthDate
		));

		assertThrows(WrongPasswordException.class, () -> repository.login(email, "WRONG_PASSWORD"));

	}

	@Test
	void login_ThrowsAccountNotFoundWhenWrongEmailIsGiven() throws ParseException {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date birthDate = dateFormat.parse("1990-11-11");
		final String password = "qwerty2018";

		this.repository.create(new User(
			this.generateRandomString(10),
			this.generateRandomString(10),
			this.generateRandomString(10) + "@mail.com",
			password,
			birthDate
		));

		assertThrows(AccountNotFoundException.class, () -> repository.login("email-that-does-not-exist@f**k.com", ""));

	}

	String generateRandomString(int length) {

		byte[] array = new byte[length];
		new Random().nextBytes(array);
		return new String(array, Charset.defaultCharset());

	}

}