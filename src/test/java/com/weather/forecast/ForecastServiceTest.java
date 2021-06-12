package com.weather.forecast;

import com.weather.location.Location;
import com.weather.location.LocationRepository;
import com.weather.location.LocationRepositoryMock;
import com.weather.location.LocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class ForecastServiceTest {

    LocationService locationService;
    ForecastService forecastService;

    @BeforeEach
    public void setUp() {
        LocationRepository locationRepository = new LocationRepositoryMock();
        ForecastRepository forecastRepository = new ForecastRepositoryMock();
        locationService = new LocationService(locationRepository);
        forecastService = new ForecastService(locationRepository, forecastRepository);
    }

    @Test
    public void whenGettingForecast_givenCorrectValues_thenShowForecast() {
        // when
        Location location = locationService.createNewLocation("Kielce", 79.1, 15.4, null, "Poland");
        Forecast result = forecastService.getForecast(1L, LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()));

        // then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getTemperature()).isNotNull();
        assertThat(result.getPressure()).isNotNull();
        assertThat(result.getHumidity()).isNotNull();
        assertThat(result.getWindSpeed()).isNotNull();
        assertThat(result.getWindDeg()).isNotNull();
        assertThat(result.getLocation().getId()).isEqualTo(1L);
    }

    @Test
    public void whenGettingForecast_givenNonExistingLocationId_thenThrowsAnException() {
        // when
        Throwable result = catchThrowable(() -> forecastService.getForecast(5L, LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth())));

        // then
        assertThat(result).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    public void whenGettingForecast_givenTooFarDate_thenThrowsAnException() {
        // when
        Location location = locationService.createNewLocation("Kielce", 79.1, 15.4, null, "Poland");
        Throwable result = catchThrowable(() -> forecastService.getForecast(1L, LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth() + 9)));

        // then
        assertThat(result).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    public void whenGettingForecast_givenPastDate_thenThrowsAnException() {
        // when
        Location location = locationService.createNewLocation("Kielce", 79.1, 15.4, null, "Poland");
        Throwable result = catchThrowable(() -> forecastService.getForecast(1L, LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth() - 1)));

        // then
        assertThat(result).isExactlyInstanceOf(RuntimeException.class);
    }

    //todo test date
    @Test
    public void whenGettingForecast_givenTooForwardLookingMonth_thenThrowsAnException() {
        // when
        Location location = locationService.createNewLocation("Kielce", 79.1, 15.4, null, "Poland");
        Throwable result = catchThrowable(() -> forecastService.getForecast(1L, LocalDate.of(LocalDate.now().getYear(), 13, LocalDate.now().getDayOfMonth())));

        // then
        assertThat(result).isExactlyInstanceOf(DateTimeException.class);
    }

    @Test
    public void whenGettingForecast_givenTooSoonMonth_thenThrowsAnException() {
        // when
        Location location = locationService.createNewLocation("Kielce", 79.1, 15.4, null, "Poland");
        Throwable result = catchThrowable(() -> forecastService.getForecast(1L, LocalDate.of(LocalDate.now().getYear(), -1, LocalDate.now().getDayOfMonth())));

        // then
        assertThat(result).isExactlyInstanceOf(DateTimeException.class);
    }

    @Test
    public void whenGettingForecast_givenTooForwardLookingDay_thenThrowsAnException() {
        // when
        Location location = locationService.createNewLocation("Kielce", 79.1, 15.4, null, "Poland");
        Throwable result = catchThrowable(() -> forecastService.getForecast(1L, LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 32)));

        // then
        assertThat(result).isExactlyInstanceOf(DateTimeException.class);
    }

    @Test
    public void whenGettingForecast_givenTooSoonDay_thenThrowsAnException() {
        // when
        Location location = locationService.createNewLocation("Kielce", 79.1, 15.4, null, "Poland");
        Throwable result = catchThrowable(() -> forecastService.getForecast(1L, LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 0)));

        // then
        assertThat(result).isExactlyInstanceOf(DateTimeException.class);
    }

//    @Test
//    public void test() {
//        LocationRepositoryMock locationRepositoryMock = new LocationRepositoryMock();
//        Location location = new Location(null, "Cracow", 45.2, 12.1, "", "Poland");
//        locationRepositoryMock.save(location);
//
//        ForecastRepositoryMock forecastRepositoryMock = new ForecastRepositoryMock();
//        forecastRepositoryMock.save(new Forecast(null, 18.4, 1013.1, 50.1, 2.3, 23.6, location));
//
//        ForecastService forecastService = new ForecastService(locationRepositoryMock, forecastRepositoryMock);
//        Forecast forecast = forecastService.getForecast(1L, 1);
//        System.out.println(forecast);
//    }
}
