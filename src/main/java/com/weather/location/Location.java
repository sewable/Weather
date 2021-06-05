package com.weather.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "locations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "region")
    private String region;

    @Column(name = "country")
    private String country;

    public Location(Long id, String cityName, Double latitude, Double longitude, String country) {
        this.id = id;
        this.cityName = cityName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
    }
}
