package com.weather.location;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class LocationService {

    private final LocationRepository locationRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public Location createNewLocation(String cityName, Double latitude, Double longitude, String region, String country) {
        if(longitude < -90 || longitude > 90) {
            throw new RuntimeException("Width should be between -90 and 90");
        }
        if(latitude < -180 || latitude > 180) {
            throw new RuntimeException("Length should be between -180 and 180");
        }
        if(cityName.isBlank() || cityName == null) {
            throw new RuntimeException("City's name cannot be empty");
        }
        if(country.isBlank() || cityName == null) {
            throw new RuntimeException("Country's name cannot be empty");
        }
        if(region != null && region.isBlank()) {
            region = null;
        }

        Location location = new Location(null, cityName, latitude, longitude, region, country);

        return locationRepository.save(location);
    }

    public List<Location> showAllLocations() {
        if(locationRepository.getAllLocations().isEmpty()) {
            throw new RuntimeException("List is empty");
        }
        return locationRepository.getAllLocations();
    }
}
