package fr.insta.cinemax.exceptions;

public class AccountNotFoundException extends Exception {

	public AccountNotFoundException(String email) {
		super("Aucun compte avec l'email " + email + " n'a été trouvé");
	}

}
