package fr.insta.cinemax.model;

import java.util.Date;

public class Movie {

	private Integer id;
	private String title;
	private String version;
	private String vision;
	private Double price;
	private Room room;
	/**
	 * Duration in minutes
	 */
	private Double duration;
	private Date startDate;

	public Movie(Integer id, String title, String version, String vision, Double price, Room room, Double duration, Date startDate) {
		this.id = id;
		this.title = title;
		this.version = version;
		this.vision = vision;
		this.price = price;
		this.room = room;
		this.duration = duration;
		this.startDate = startDate;
	}

	public Movie(String title, String version, String vision, Double price, Room room, Double duration, Date startDate) {
		this.title = title;
		this.version = version;
		this.vision = vision;
		this.price = price;
		this.room = room;
		this.duration = duration;
		this.startDate = startDate;
	}

	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getVersion() {
		return version;
	}

	public String getVision() {
		return vision;
	}

	public Double getPrice() {
		return price;
	}

	public Room getRoom() {
		return room;
	}

	public Double getDuration() {
		return duration;
	}

	public Date getStartDate() {
		return startDate;
	}

}
