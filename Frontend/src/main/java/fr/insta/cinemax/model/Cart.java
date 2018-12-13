package fr.insta.cinemax.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class Cart {

	private List<CartElement> cartElements;
	private Double totalPrice;

	public Cart(List<CartElement> cartElements, Double totalPrice) {
		this.cartElements = cartElements;
		this.totalPrice = totalPrice;
	}

	public List<CartElement> getCartElements() {
		return cartElements;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public Cart merge(Cart cart) {

		for (CartElement cartElement: cart.cartElements) {
			if (this.containsSession(cartElement.getSession().getId())) {

				CartElement elementForSession = this.elementForSession(cartElement.getSession().getId());
				if (elementForSession != null) {
					this.cartElements.remove(elementForSession);
					this.cartElements.add(new CartElement(
						elementForSession.getSession(),
						elementForSession.getCount() + cartElement.getCount()
					));
				}
			} else
				this.cartElements.add(cartElement);
		}
		this.totalPrice += cart.getTotalPrice();

		return this;
	}

	private CartElement elementForSession(Integer sessionId) {
		for (CartElement cartElement: this.cartElements) {
			if (cartElement.getSession().getId().equals(sessionId))
				return cartElement;
		}
		return null;
	}

	private boolean containsSession(Integer sessionId) {
		for (CartElement cartElement: this.cartElements) {
			if (cartElement.getSession().getId().equals(sessionId))
				return true;
		}
		return false;
	}

}
