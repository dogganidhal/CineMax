package fr.insta.cinemax.model;

import com.sun.istack.internal.Nullable;

public class User {

	@Nullable
	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;

	public User(Integer id, String firstName, String lastName, String email, String password) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public User(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	@Override
	public String toString() {
		return "[User: " + this.getFirstName() + " " + this.getLastName() + "]";
	}

}
