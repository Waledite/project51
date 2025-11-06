package edu.westga.cs6241.weather_app_uwg.strategy;

import edu.westga.cs6241.weather_app_uwg.model.DailySummary;

/**
 * Strategy interface for filtering daily weather summaries.
 */
public interface SelectionStrategy {
	/**
     * Determines if a given DailySummary matches this strategy's criteria.
     * 
     * @param ds The DailySummary to evaluate
     * @return true if the summary matches, false otherwise
     */
	boolean matches(DailySummary ds);
}
