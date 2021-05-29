package com.weather;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LocationController {

    private LocationService locationService = new LocationService();
    private ObjectMapper objectMapper = new ObjectMapper();

    public String createNewLocation(String cityName, Double length, Double width, String region, String country) {
        try {
            Location location = locationService.createNewLocation(cityName, length, width, region, country);
            return objectMapper.writeValueAsString(location);
        } catch (Exception e) {
            return "Error message: " + e.getMessage();
        }
    }
}
