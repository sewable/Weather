package com.weather.forecast;

import com.weather.location.Location;
import com.weather.location.LocationRepository;
import com.weather.location.LocationRepositoryMock;
import com.weather.location.LocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ForecastServiceTest {

    LocationService locationService;
    ForecastService forecastService;

    @BeforeEach
    public void setUp() {
        LocationRepository locationRepository = new LocationRepositoryMock();
        ForecastRepository forecastRepository = new ForecastRepositoryMock();
        forecastService = new ForecastService(locationRepository, forecastRepository);
    }

    // todo complete tests
    @Test
    public void whenGettingForecast_givenCorrectValues_thenShowForecast() {
        // when
        Location location = locationService.createNewLocation("Kielce", 79.1, 15.4, null, "Poland");
        Forecast result = forecastService.getForecast(1L, 1);

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
    public void test() {
        LocationRepositoryMock locationRepositoryMock = new LocationRepositoryMock();
        Location location = new Location(null, "Cracow", 45.2, 12.1, "", "Poland");
        locationRepositoryMock.save(location);

        ForecastRepositoryMock forecastRepositoryMock = new ForecastRepositoryMock();
        forecastRepositoryMock.save(new Forecast(null, 18.4, 1013.1, 50.1, 2.3, 23.6, location));

        ForecastService forecastService = new ForecastService(locationRepositoryMock, forecastRepositoryMock);
        Forecast forecast = forecastService.getForecast(1L, 1);
        System.out.println(forecast);
    }
}
