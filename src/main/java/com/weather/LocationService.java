package com.weather;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LocationService {

    private LocationRepository locationRepository = new LocationRepository();
    private ObjectMapper objectMapper = new ObjectMapper();

    public LocationService() {
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public Location createNewLocation(String cityName, Double length, Double width, String region, String country) {
        if(width < -90 || width > 90) {
            throw new RuntimeException("Width should be between -90 and 90");
        }
        if(length < -180 || length > 180) {
            throw new RuntimeException("Length should be between -180 and 180");
        }
        if(cityName.isBlank() || cityName == null) {
            throw new RuntimeException("City's name cannot be empty");
        }
        if(country.isBlank() || cityName == null) {
            throw new RuntimeException("Country's name cannot be empty");
        }

        Location location = new Location(null, cityName, length, width, region, country);

        return locationRepository.save(location);
    }
}
