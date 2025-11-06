package edu.westga.cs6241.weather_app_uwg.model;

import java.io.FileReader;
import java.io.Reader;  
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import edu.westga.cs6241.weather_app_uwg.api.WeatherAPI;
import edu.westga.cs6241.weather_app_uwg.strategy.SelectionStrategy;

public class WeatherDatabase implements WeatherAPI {
	private final List<DailySummary> summaries;

    // Private constructor
    private WeatherDatabase(List<DailySummary> summaries) {
        this.summaries = summaries;
    }

    /**
     * Loads DailySummary data from a CSV file.
     * CSV columns should include: stationName, date, precip, hiTemp, loTemp
     */
    public static WeatherDatabase from(String filename) throws IOException {
        List<DailySummary> summaries = new ArrayList<>();

        try (Reader in = new FileReader(filename)) {
        	Iterable<CSVRecord> records = CSVFormat.DEFAULT
        	        .builder()
        	        .setHeader()                // Automatically use the first record as header
        	        .setSkipHeaderRecord(true)  // Skip the header row when reading
        	        .build()
        	        .parse(in);


            for (CSVRecord record : records) {
                String stationName = record.get("stationName");
                LocalDate date = LocalDate.parse(record.get("date"));
                double precip = Double.parseDouble(record.get("precip"));
                int hiTemp = Integer.parseInt(record.get("hiTemp"));
                int loTemp = Integer.parseInt(record.get("loTemp"));

                summaries.add(new DailySummary(stationName, date, precip, hiTemp, loTemp));
            }
        }

        return new WeatherDatabase(summaries);
    }

    public List<DailySummary> queryBy(SelectionStrategy strategy) {
        return summaries.stream()
                .filter(strategy::matches)
                .collect(Collectors.toList());
    }

    public List<DailySummary> getSummaries() {
        return summaries;
    }

}
