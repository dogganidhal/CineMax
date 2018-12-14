package fr.insta.cinemax.model;

public class Movie {

	private Integer id;
	private String title;
	private String version;
	private String description;
	/**
	 * Duration in minutes
	 */
	private Double duration;

	public Movie(Integer id, String title, String version, String description, Double duration) {
		this.id = id;
		this.title = title;
		this.version = version;
		this.description = description;
		this.duration = duration;
	}

	public Movie(String title, String version, String vision, Double duration) {
		this.title = title;
		this.version = version;
		this.description = vision;
		this.duration = duration;
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

	public String getDescription() {
		return description;
	}

	public Double getDuration() {
		return duration;
	}

}
