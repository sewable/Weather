package com.weather;

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
    private Integer id;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "length")
    private Double length;

    @Column(name = "width")
    private Double width;

    @Column(name = "region")
    private String region;

    @Column(name = "country")
    private String country;

    public Location(Integer id, String cityName, Double length, Double width, String country) {
        this.id = id;
        this.cityName = cityName;
        this.length = length;
        this.width = width;
        this.country = country;
    }
}
