package com.weather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LocationServiceTest {

    LocationService locationService;

    @BeforeEach
    public void setUp() {
        LocationRepository locationRepository = new LocationRepositoryMock();
        locationService = new LocationService(locationRepository);
    }

    @Test
    public void whenCreateNewLocation_givenCorrectValues_thenCreatesNewLocation() {
        // when
        locationService.createNewLocation("city", 45.0, 45.0, "region", "country");

        // then

    }
}
