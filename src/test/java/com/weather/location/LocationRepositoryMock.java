package com.weather.location;

import com.weather.location.Location;
import com.weather.location.LocationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LocationRepositoryMock implements LocationRepository {

    private List<Location> locations = new ArrayList<>();

    @Override
    public Location save(Location location) {
        location.setId(1L);
        locations.add(location);
        return location;
    }

    @Override
    public List<Location> getAllLocations() {
        return null;
    }

    @Override
    public Optional<Location> findById(Long id) {
        return locations.stream()
                .findFirst();
    }
}
