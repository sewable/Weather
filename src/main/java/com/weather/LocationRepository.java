package com.weather;

import java.util.List;
import java.util.Optional;

public interface LocationRepository {
    Location save(Location location);
    List<Location> getAllLocations();
    Optional<Location> findById(Long id);
}
