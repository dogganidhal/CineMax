package fr.insta.cinemax.manager;

import fr.insta.cinemax.model.User;

import java.util.Calendar;
import java.util.Date;

public class PriceManager {

	private static PriceManager instance;

	public static PriceManager getInstance() {
		return PriceManager.instance;
	}

	public Double computePriceForUser(User user) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(user.getBirthDate());

		Integer birthYear = calendar.get(Calendar.YEAR);
		calendar.setTime(new Date());

		Integer currentYear = calendar.get(Calendar.YEAR);
		Integer userAge = currentYear - birthYear;

		// TODO: Find a more elegant way to do this

		if (userAge > 45) // Veteran
			return 7.99;
		else if (userAge > 25) // Adult and he/she knows it
			return 9.99;
		else if (userAge > 15) // Full of ambition
			return 4.99;

		return 2.99;

	}

}
