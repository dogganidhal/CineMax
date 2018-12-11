package fr.insta.cinemax.model;

import com.sun.istack.internal.Nullable;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "user")
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

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	public Integer getId() {
		return id;
	}

	@Column(name = "first_name")
	public String getFirstName() {
		return firstName;
	}

	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	@Column(name = "password")
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
