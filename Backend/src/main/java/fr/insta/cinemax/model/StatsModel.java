package fr.insta.cinemax.model;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StatsModel {

	/**
	 * Number of ticket sales during the last 7 days of week
	 * #key: Day of week (String)
	 * #value: Number of tickets sold on that day
	 */
	private List<Pair<String, Integer>> salesPerDayOfWeek;
	/**
	 * Evolution of the Profits during the last 7 days
	 * #key: Day of week (String)
	 * #value: Total profit from tickets sold on that day
	 */
	private List<Pair<String, Double>> lastWeekProfit;
	private List<Pair<User, Integer>> topUsers;
	private List<Pair<Movie, Double>> bestSellerMovies;

	public StatsModel(List<Pair<String, Integer>> salesPerDayOfWeek, List<Pair<String, Double>> lastWeekProfit, List<Pair<User, Integer>> topUsers, List<Pair<Movie, Double>> bestSellerMovies) {
		this.salesPerDayOfWeek = salesPerDayOfWeek;
		this.lastWeekProfit = lastWeekProfit;
		this.topUsers = topUsers;
		this.bestSellerMovies = bestSellerMovies;
	}

	public List<Integer> getSalesPerDayOfWeek() {
		List<Integer> profits = new ArrayList<>();
		for (Pair<String, Integer> entry: this.salesPerDayOfWeek)
			profits.add(entry.getValue());
		return profits;
	}

	public List<Double> getLastWeekProfit() {
		List<Double> profits = new ArrayList<>();
		for (Pair<String, Double> entry: this.lastWeekProfit)
			profits.add(entry.getValue());
		return profits;
	}

	public List<Pair<User, Integer>> getTopUsers() {
		return topUsers;
	}

	public List<Pair<Movie, Double>> getBestSellerMovies() {
		return bestSellerMovies;
	}

	public String getDaysOfWeek() {
		StringBuilder daysOfWeek = new StringBuilder();
		int index = 0;
		daysOfWeek.append("[");
		List<Pair<String, Double>> entries = this.lastWeekProfit;
		Collections.reverse(entries);
		for (Pair<String, Double> entry : entries) {
			daysOfWeek
				.append("\"")
				.append(entry.getKey())
				.append("\"");
			if (index < this.lastWeekProfit.size() - 1)
				daysOfWeek.append(",");
			index++;
		}
		daysOfWeek.append("]");
		return daysOfWeek.toString();
	}

}
