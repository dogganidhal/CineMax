package fr.insta.cinemax.repositories;

import fr.insta.cinemax.interfaces.IMovieRepository;
import fr.insta.cinemax.interfaces.IStatsRepository;
import fr.insta.cinemax.interfaces.IUserRepository;
import fr.insta.cinemax.manager.ConnectionManager;
import fr.insta.cinemax.mappers.UserMapper;
import fr.insta.cinemax.model.Movie;
import fr.insta.cinemax.model.StatsModel;
import fr.insta.cinemax.model.User;
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
		return new StatsModel(
			this.getSalesPerDayOfWeek(),
			this.getLastWeekProfit(),
			this.getTopThreeUsers(),
			this.getBestSellerMovies()
		);
	}

	private List<Pair<User, Integer>> getTopThreeUsers() {

		try {

			Connection connection = ConnectionManager.getInstance().getConnection();
			String selectStatement = "SELECT COUNT(user_id) AS tickets_per_user, user_id FROM Ticket GROUP BY user_id ORDER BY tickets_per_user DESC LIMIT 3;";
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);

			ResultSet resultSet = preparedStatement.executeQuery();
			List<Pair<User, Integer>> topUsers = new ArrayList<>();
			IUserRepository userRepository = RepositoryFactory.getInstance().createUserRepository();

			while (resultSet.next()) {

				User user = userRepository.getUserById(resultSet.getInt("user_id"));
				topUsers.add(new Pair<>(user, resultSet.getInt("tickets_per_user")));

			}

			return topUsers;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new ArrayList<>();

	}

	private List<Pair<Movie, Double>> getBestSellerMovies() {

		try {

			Connection connection = ConnectionManager.getInstance().getConnection();
			String selectStatement = "SELECT movie_id, SUM(revenue_per_session) AS revenue_per_movie FROM (\n" +
							"\tSELECT SUM(t.price) as revenue_per_session, t.session_id as session_id, s.movie_id as movie_id\n" +
							"\tFROM ticket t JOIN session s \n" +
							"\tON t.session_id = s.id\n" +
							"\tGROUP BY session_id\n" +
							") as s GROUP BY s.movie_id ORDER BY revenue_per_movie DESC;";
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);

			ResultSet resultSet = preparedStatement.executeQuery();
			List<Pair<Movie, Double>> bestSellerMovies = new ArrayList<>();
			IMovieRepository movieRepository = RepositoryFactory.getInstance().createMovieRepository();

			while (resultSet.next()) {

				Movie movie = movieRepository.getMovieById(resultSet.getInt("movie_id"));
				bestSellerMovies.add(new Pair<>(movie, resultSet.getDouble("revenue_per_movie")));

			}

			return bestSellerMovies;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new ArrayList<>();

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
