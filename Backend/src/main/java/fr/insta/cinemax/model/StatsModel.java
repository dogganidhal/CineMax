package fr.insta.cinemax.model;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


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

	public StatsModel(List<Pair<String, Integer>> salesPerDayOfWeek, List<Pair<String, Double>> lastWeekProfit) {
		this.salesPerDayOfWeek = salesPerDayOfWeek;
		this.lastWeekProfit = lastWeekProfit;
	}

	public List<Pair<String, Integer>> getSalesPerDayOfWeek() {
		return salesPerDayOfWeek;
	}

	public List<Double> getLastWeekProfit() {
		List<Double> profits = new ArrayList<>();
		for (Pair<String, Double> entry: this.lastWeekProfit)
			profits.add(entry.getValue());
		return profits;
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
