package com.weather.forecast;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.location.Location;
import com.weather.location.LocationRepository;
import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

@RequiredArgsConstructor
public class ForecastService {

    private final LocationRepository locationRepository;
    private final ForecastRepository forecastRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

    public Forecast getForecast(Long locationId, Integer date) {
        Location location = locationRepository.findById(locationId).orElseThrow(() -> new RuntimeException("There is no location with " + locationId + " id"));

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://api.openweathermap.org/data/2.5/onecall?lat=" + location.getLatitude() + "&lon=" + location.getLongitude() + "&exclude=minutely,hourly&appid=9042c4ef58824c7627c67bcf1007f755"))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            ForecastResponseDTO forecastResponseDTO = objectMapper.readValue(responseBody, ForecastResponseDTO.class);

            LocalDate forecastDay = LocalDate.now().plusDays(date);

            Forecast forecast = forecastResponseDTO.getDaily().stream()
                    .filter(s -> s.getDate().equals(forecastDay))
                    .findFirst()
                    .map(s -> Forecast.builder().temperature(s.getTemperature().getCelsius())
                            .pressure(s.getPressure())
                            .windSpeed(s.getWindSpeed())
                            .windDeg(s.getWindDeg())
                            .humidity(s.getHumidity())
                            .location(location)
                            .build())
                    .orElseThrow(() -> new RuntimeException("There is no forecast for passed date"));

            return forecastRepository.save(forecast);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
