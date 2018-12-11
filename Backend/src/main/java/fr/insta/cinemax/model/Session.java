package fr.insta.cinemax.model;

import java.util.Date;

public class Session {

	private Integer id;
	private Date startDate;
	private Room room;
	private Movie movie;

	public Session(Integer id, Date startDate, Room room, Movie movie) {
		this.id = id;
		this.startDate = startDate;
		this.room = room;
		this.movie = movie;
	}

	public Session(Date startDate, Room room, Movie movie) {
		this.startDate = startDate;
		this.room = room;
		this.movie = movie;
	}

	public Integer getId() {
		return id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Room getRoom() {
		return room;
	}

	public Movie getMovie() {
		return movie;
	}

}
