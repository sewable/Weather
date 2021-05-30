package com.weather;

import org.junit.jupiter.api.Test;

public class ForecastServiceTest {

    @Test
    public void test() {
        ForecastService forecastService = new ForecastService();
        forecastService.getForecast(1, 1);
    }
}
