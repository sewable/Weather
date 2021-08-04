package com.weather.forecast;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;

public class ForecastController {

    private final ForecastService forecastService;
    private ObjectMapper objectMapper = new ObjectMapper();

    public ForecastController(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    public String getForecast(Long locationId, LocalDate date) {
        try {
            Forecast forecast = forecastService.getForecast(locationId, date);
            return objectMapper.writeValueAsString(forecast);
        } catch (Exception e) {
            return "Error message: " + e.getMessage();
        }
    }

    public String getForecastByCityName(String city, LocalDate date) {
        try {
            Forecast forecast = forecastService.getForecastByCityName(city, date);
            return objectMapper.writeValueAsString(forecast);
        } catch (Exception e) {
            return "Error message: " + e.getMessage();
        }
    }
}
