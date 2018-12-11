package fr.insta.cinemax.exceptions;

public class NotEnoughSpaceException extends Exception {

	public NotEnoughSpaceException() {
		super("La scéance que vous avez demandé est malheureusement pleine, veuillez réserver dans une autre scéance");
	}

}
