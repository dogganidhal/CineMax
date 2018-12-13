package fr.insta.cinemax.model;

import java.util.Date;

public class Ticket {

	private Integer id;
	private Session session;
	private User user;
	private Double price;
	private Date createdDate;

	public Ticket(Session session, User user, Double price) {
		this.session = session;
		this.user = user;
		this.price = price;
	}

	public Ticket(Session session, User user, Double price, Date createdDate) {
		this.session = session;
		this.user = user;
		this.price = price;
		this.createdDate = createdDate;
	}

	public Ticket(Integer id, Session session, User user, Double price, Date createdDate) {
		this.id = id;
		this.session = session;
		this.user = user;
		this.price = price;
		this.createdDate = createdDate;
	}

	public Integer getId() {
		return id;
	}

	public Session getSession() {
		return session;
	}

	public User getUser() {
		return user;
	}

	public Double getPrice() {
		return price;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

}
