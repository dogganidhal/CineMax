package fr.insta.cinemax.model;

public class Movie {

	private Integer id;
	private String title;
	private String version;
	private String vision;
	/**
	 * Duration in minutes
	 */
	private Double duration;

	public Movie(Integer id, String title, String version, String vision, Double duration) {
		this.id = id;
		this.title = title;
		this.version = version;
		this.vision = vision;
		this.duration = duration;
	}

	public Movie(String title, String version, String vision, Double duration) {
		this.title = title;
		this.version = version;
		this.vision = vision;
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

	public String getVision() {
		return vision;
	}

	public Double getDuration() {
		return duration;
	}

}
