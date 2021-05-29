package com.weather;

public class WeatherApplication {

    public static void main(String[] args) {

        LocationRepositoryImpl locationRepositoryImpl = new LocationRepositoryImpl();
        LocationService locationService = new LocationService(locationRepositoryImpl);
        LocationController locationController = new LocationController(locationService);
        UserInterface userInterface = new UserInterface(locationController);
        userInterface.run();
    }
}
