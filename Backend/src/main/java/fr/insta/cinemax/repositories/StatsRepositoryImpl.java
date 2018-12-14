package fr.insta.cinemax.repositories;

import fr.insta.cinemax.interfaces.IStatsRepository;
import fr.insta.cinemax.manager.ConnectionManager;
import fr.insta.cinemax.model.StatsModel;
import fr.insta.cinemax.util.DateUtils;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class StatsRepositoryImpl implements IStatsRepository {

	@Override
	public StatsModel getStats() {
		return new StatsModel(this.getSalesPerDayOfWeek(), this.getLastWeekProfit());
	}

	private List<Pair<String, Integer>> getSalesPerDayOfWeek() {

		List<Pair<String, Integer>> salesPerDayOfWeek = new ArrayList<>();
		long currentTimestamp = new Date().getTime();

		for (int index = 0; index < 7; index ++) {

			Date date = new Date(currentTimestamp - index * 24 * 60 * 60 * 1000);
			salesPerDayOfWeek.add(this.getSalesPerDate(date));

		}

		return salesPerDayOfWeek;
	}

	private List<Pair<String, Double>> getLastWeekProfit() {

		List<Pair<String, Double>> lastWeekProfit = new ArrayList<>();
		long currentTimestamp = new Date().getTime();

		for (int index = 0; index < 7; index ++) {

			Date date = new Date(currentTimestamp - index * 24 * 60 * 60 * 1000);
			lastWeekProfit.add(this.getProfitPerDate(date));

		}

		return lastWeekProfit;

	}

	private Pair<String, Integer> getSalesPerDate(Date date) {

		try {

			Connection connection = ConnectionManager.getInstance().getConnection();
			String selectStatement = "SELECT COUNT(id) AS ticket_count FROM ticket WHERE DATE_FORMAT(created_date, \"%d-%m-%y\") = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);

			preparedStatement.setString(1, DateUtils.formatDate(date, DateUtils.DateFormat.SHORT_DASHED));

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
				return new Pair<>(DateUtils.getDayOfWeekString(date), resultSet.getInt(1));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	private Pair<String, Double> getProfitPerDate(Date date) {

		try {

			Connection connection = ConnectionManager.getInstance().getConnection();
			String selectStatement = "SELECT SUM(price) AS ticket_count FROM ticket WHERE DATE_FORMAT(created_date, \"%d-%m-%y\") = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);

			preparedStatement.setString(1, DateUtils.formatDate(date, DateUtils.DateFormat.SHORT_DASHED));

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
				return new Pair<>(DateUtils.getDayOfWeekString(date), resultSet.getDouble("ticket_count"));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

}
