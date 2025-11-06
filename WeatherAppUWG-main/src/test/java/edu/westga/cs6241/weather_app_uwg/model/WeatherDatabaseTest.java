package edu.westga.cs6241.weather_app_uwg.model;

import edu.westga.cs6241.weather_app_uwg.strategy.SelectionStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherDatabaseTest {

    @TempDir
    Path tempDir;  // JUnit creates a temporary directory automatically

    @Test
    void testFromLoadsCSVCorrectly() throws IOException {
        // Arrange
        Path csvFile = tempDir.resolve("weather.csv");
        String csvContent = """
                stationName,date,precip,hiTemp,loTemp
                StationA,2024-07-15,1.2,30,20
                StationB,2024-07-16,0.0,25,18
                """;
        Files.writeString(csvFile, csvContent);

        // Act
        WeatherDatabase db = WeatherDatabase.from(csvFile.toString());
        List<DailySummary> summaries = db.getSummaries();

        // Assert
        assertEquals(2, summaries.size());
        assertEquals("StationA", summaries.get(0).stationName);
        assertEquals(LocalDate.of(2024, 7, 15), summaries.get(0).date);
        assertEquals(30, summaries.get(0).hiTemp);
        assertEquals(20, summaries.get(0).loTemp);
    }

    @Test
    void testQueryByFiltersUsingStrategy() throws IOException {
        // Arrange
        Path csvFile = tempDir.resolve("weather.csv");
        Files.writeString(csvFile, """
                stationName,date,precip,hiTemp,loTemp
                StationA,2024-07-15,1.2,30,20
                StationB,2024-07-16,0.0,25,18
                StationC,2024-07-17,2.1,33,22
                """);

        WeatherDatabase db = WeatherDatabase.from(csvFile.toString());

        // Define a simple strategy: hot days (hiTemp > 28)
        SelectionStrategy hotDays = ds -> ds.hiTemp > 28;

        // Act
        List<DailySummary> results = db.queryBy(hotDays);

        // Assert
        assertEquals(2, results.size());
        assertTrue(results.stream().allMatch(ds -> ds.hiTemp > 28));
        assertEquals("StationA", results.get(0).stationName);
        assertEquals("StationC", results.get(1).stationName);
    }
}
