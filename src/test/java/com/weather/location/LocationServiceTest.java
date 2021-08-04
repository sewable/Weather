package com.weather.location;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

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
        Location result = locationService.createNewLocation("city", 45.0, 45.0, "region", "country");

        // then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getCityName()).isEqualTo("city");
        assertThat(result.getLatitude()).isEqualTo(45.0);
        assertThat(result.getLongitude()).isEqualTo(45.0);
        assertThat(result.getRegion()).isEqualTo("region");
        assertThat(result.getCountry()).isEqualTo("country");
    }

    @Test
    public void whenCreateNewLocation_givenEmptyCityName_thenThrowsAnException() {
        // when
        Throwable result = catchThrowable(() -> locationService.createNewLocation("", 45.0, 45.0, "region", "country"));

        // then
        assertThat(result).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    public void whenCreateNewLocation_givenNullCityName_thenThrowsAnException() {
        // when
        Throwable result = catchThrowable(() -> locationService.createNewLocation(null, 45.0, 45.0, "region", "country"));

        // then
        assertThat(result).isInstanceOf(RuntimeException.class);
        assertThat(result).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void whenCreateNewLocation_givenBlankCityName_thenThrowsAnException() {
        // when
        Throwable result = catchThrowable(() -> locationService.createNewLocation("       ", 45.0, 45.0, "region", "country"));

        // then
        assertThat(result).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    public void whenCreateNewLocation_givenEmptyCountry_thenThrowsAnException() {
        // when
        Throwable result = catchThrowable(() -> locationService.createNewLocation("city", 45.0, 45.0, "region", ""));

        // then
        assertThat(result).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    public void whenCreateNewLocation_givenNullCountry_thenThrowsAnException() {
        // when
        Throwable result = catchThrowable(() -> locationService.createNewLocation("city", 45.0, 45.0, "region", null));

        // then
        assertThat(result).isInstanceOf(RuntimeException.class);
        assertThat(result).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void whenCreateNewLocation_givenBlankCountry_thenThrowsAnException() {
        // when
        Throwable result = catchThrowable(() -> locationService.createNewLocation("city", 45.0, 45.0, "region", "        "));

        // then
        assertThat(result).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    public void whenCreateNewLocation_givenTooLowLatitude_thenThrowsAnException() {
        // when
        Throwable result = catchThrowable(() -> locationService.createNewLocation("city", -1145.0, 45.0, "region", "country"));

        // then
        assertThat(result).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    public void whenCreateNewLocation_givenTooHighLatitude_thenThrowsAnException() {
        // when
        Throwable result = catchThrowable(() -> locationService.createNewLocation("city", 1145.0, 45.0, "region", "country"));

        // then
        assertThat(result).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    public void whenCreateNewLocation_givenTooLowLongitude_thenThrowsAnException() {
        // when
        Throwable result = catchThrowable(() -> locationService.createNewLocation("city", 45.0, -145.0, "region", "country"));

        // then
        assertThat(result).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    public void whenCreateNewLocation_givenTooHighLongitude_thenThrowsAnException() {
        // when
        Throwable result = catchThrowable(() -> locationService.createNewLocation("city", 45.0, 1145.0, "region", "country"));

        // then
        assertThat(result).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    public void whenCreateNewLocation_givenNullRegion_thenCreateNullRegion() {
        // when
        Location result = locationService.createNewLocation("city", 180.0, 90.0, null, "country");

        // then
        assertThat(result.getRegion()).isEqualTo(null);
    }

    @Test
    public void whenCreateNewLocation_givenBlankRegion_thenCreateNullRegion() {
        // when
        Location result = locationService.createNewLocation("city", 180.0, 90.0, "    ", "country");

        // then
        assertThat(result.getRegion()).isEqualTo(null);
    }

    @Test
    public void whenShowingAllLocations_givenLocationList_thenShowAllLocations() {
        // when
        Location location = locationService.createNewLocation("Kielce", 12.5, 45.7, null, "Poland");
        List<Location> result = new ArrayList<>();
        result.add(location);

        // then
        assertThat(!result.isEmpty());
    }

    @Test
    public void whenShowingAllLocations_givenEmptyLocationList_thenThrowsAnException() {
        // when
        Throwable result = catchThrowable(() -> locationService.showAllLocations());

        // then
        assertThat(result).isInstanceOf(RuntimeException.class);
        assertThat(result).isInstanceOf(NullPointerException.class);
    }
}
