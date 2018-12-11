package fr.insta.cinemax.model;

import java.util.Date;

public class Ticket {

	private Integer id;
	private Movie movie;
	private User user;
	private Integer price;
	private Date createdDate;

	public Ticket(Movie movie, User user, Integer price, Date createdDate) {
		this.movie = movie;
		this.user = user;
		this.price = price;
		this.createdDate = createdDate;
	}

	public Ticket(Integer id, Movie movie, User user, Integer price, Date createdDate) {
		this.id = id;
		this.movie = movie;
		this.user = user;
		this.price = price;
		this.createdDate = createdDate;
	}

	public Integer getId() {
		return id;
	}

	public Movie getMovie() {
		return movie;
	}

	public User getUser() {
		return user;
	}

	public Integer getPrice() {
		return price;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

}
