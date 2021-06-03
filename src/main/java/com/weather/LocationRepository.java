package com.weather;

import java.util.List;

public interface LocationRepository {
    Location save(Location location);
    List<Location> getAllLocations();
}
