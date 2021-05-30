package com.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

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
    }
}
