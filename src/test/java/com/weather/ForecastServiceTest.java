package com.weather;

import org.junit.jupiter.api.Test;

public class ForecastServiceTest {

    @Test
    public void test() {
        LocationRepositoryMock locationRepositoryMock = new LocationRepositoryMock();
        locationRepositoryMock.save(new Location(null, "Cracow", 45.2, 12.1, "", "Poland"));
        ForecastService forecastService = new ForecastService(locationRepositoryMock);
        Forecast forecast = forecastService.getForecast(1L, 1);
        System.out.println(forecast);
    }
}
