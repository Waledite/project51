package edu.westga.cs6241.weather_app_uwg.model;

import java.time.LocalDate;

/**
 * Data class representing a daily weather summary for a station.
 */
public class DailySummary {
	final String stationName;
	final LocalDate date;
	final Double precip;
	final int hiTemp;
	final int loTemp;
	
	public DailySummary(String stationName, LocalDate date, double precip, int hiTemp, int loTemp) {
        this.stationName = stationName;
        this.date = date;
        this.precip = precip;
        this.hiTemp = hiTemp;
        this.loTemp = loTemp;
    }
	
	public String getStationName() {
		return stationName;
	}

	public LocalDate getDate() {
		return date;
	}

	public Double getPrecip() {
		return precip;
	}

	public int getHiTemp() {
		return hiTemp;
	}

	public int getLoTemp() {
		return loTemp;
	}

}
