package com.weather;

public class WeatherApplication {

    public static void main(String[] args) {

        LocationRepositoryImpl locationRepositoryImpl = new LocationRepositoryImpl();
        LocationService locationService = new LocationService(locationRepositoryImpl);
        LocationController locationController = new LocationController(locationService);
        UserInterface userInterface = new UserInterface(locationController);
        userInterface.run();

        // https://api.openweathermap.org/data/2.5/onecall?lat=50&lon=20&exclude=minutely,hourly&appid=9042c4ef58824c7627c67bcf1007f755
    }
}
