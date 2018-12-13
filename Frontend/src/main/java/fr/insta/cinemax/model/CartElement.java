package fr.insta.cinemax.model;


public class CartElement {

	private Session session;
	private Integer count;

	public CartElement(Session session, Integer count) {
		this.session = session;
		this.count = count;
	}

	public Session getSession() {
		return session;
	}

	public Integer getCount() {
		return count;
	}

}
