package com.weather.forecast;

import com.weather.location.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "forecast")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Forecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "temperature")
    private Double temperature;

    @Column(name = "pressure")
    private Double pressure;

    @Column(name = "humidity")
    private Double humidity;

    @Column(name = "wind_speed")
    private Double windSpeed;

    @Column(name = "wind_deg")
    private Double windDeg;

    @ManyToOne
    private Location location;
}
