package fr.insta.cinemax.exceptions;

public class WrongPasswordException extends Exception {

	public WrongPasswordException() {
		super("Le mot de passe que vous avez introduit est erroné");
	}

}
