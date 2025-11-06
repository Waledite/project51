package edu.westga.cs6241.weather_app_uwg.api;

import java.util.List;

import edu.westga.cs6241.weather_app_uwg.model.DailySummary;
import edu.westga.cs6241.weather_app_uwg.strategy.SelectionStrategy;

/**
 * Interface for querying weather data using selection strategies.
 */
public interface WeatherAPI {
	/**
     * Queries weather data using the provided selection strategy.
     * 
     * @param strategy The strategy to filter/select daily summaries
     * @return A list of DailySummary objects matching the strategy
     */
	List<DailySummary> queryBy(SelectionStrategy strategy);
}
