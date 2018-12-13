package fr.insta.cinemax.model;

import com.google.gson.annotations.SerializedName;

public class CartElement {

	@SerializedName("sessionId")
	public Integer sessionId;

	@SerializedName("movieId")
	public Integer movieId;

	@SerializedName("numberOfTickets")
	public Integer numberOfTickets;

}
