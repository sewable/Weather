package com.weather.forecast;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.*;
import java.util.List;

@Data
public class ForecastResponseDTO {

    @JsonProperty("lat")
    private Double latitude;

    @JsonProperty("lon")
    private Double longitude;

    private List<SingleDayForecast> daily;

    @Data
    static class SingleDayForecast {

        private Double pressure;

        @JsonProperty("dt")
        private Long timestamp;

        private Double humidity;

        @JsonProperty("wind_speed")
        private Double windSpeed;

        @JsonProperty("wind_deg")
        private Double windDeg;

        @JsonProperty("temp")
        private Temperature temperature;

        public LocalDate getDate() {
            Instant instant = Instant.ofEpochSecond(timestamp);
            return instant.atZone(ZoneOffset.UTC).toLocalDate();
        }

        @Data
        static class Temperature {

            private Double day;

            public Double getCelsius() {
                return day - 273.15;
            }
        }
    }
}
