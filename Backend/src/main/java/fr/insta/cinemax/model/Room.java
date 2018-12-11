package fr.insta.cinemax.model;


public class Room {

	private Integer id;
	private String name;
	private Integer capacity;

	public Room(Integer id, String name, Integer capacity) {
		this.id = id;
		this.name = name;
		this.capacity = capacity;
	}

	public Room(String name, Integer capacity) {
		this.name = name;
		this.capacity = capacity;
	}

	public Room(Integer id, String name) {
		this.id = id;
		this.name = name;
		this.capacity = 100;
	}

	public Room(String name) {
		this.name = name;
		this.capacity = 100;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getCapacity() {
		return capacity;
	}

}
