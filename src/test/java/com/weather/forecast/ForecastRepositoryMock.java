package com.weather.forecast;

import com.weather.forecast.Forecast;
import com.weather.forecast.ForecastRepository;

import java.util.ArrayList;
import java.util.List;

public class ForecastRepositoryMock implements ForecastRepository {

    private List<Forecast> forecasts = new ArrayList<>();

    @Override
    public Forecast save(Forecast forecast) {
        forecast.setId(1L);
        forecasts.add(forecast);
        return forecast;
    }
}
