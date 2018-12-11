package fr.insta.cinemax.model;


import java.util.Date;

public class User {

	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Date birthDate;

	public User(Integer id, String firstName, String lastName, String email, String password, Date birthDate) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.birthDate = birthDate;
	}

	public User(String firstName, String lastName, String email, String password, Date birthDate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.birthDate = birthDate;
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

	public Date getBirthDate() {
		return birthDate;
	}

	@Override
	public String toString() {
		return "[User: " + this.getFirstName() + " " + this.getLastName() + "]";
	}

}
