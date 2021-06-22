package com.weather;

import com.weather.forecast.ForecastController;
import com.weather.location.LocationController;

import java.time.LocalDate;
import java.util.Scanner;

public class UserInterface {

    private final LocationController locationController;
    private final ForecastController forecastController;

    public UserInterface(LocationController locationController, ForecastController forecastController) {
        this.locationController = locationController;
        this.forecastController = forecastController;
    }

    public void run() {
        System.out.println("Application running");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome in weather application, what do you want to do?");
            System.out.println("1. Add new location");
            System.out.println("2. Show all locations");
            System.out.println("3. Show forecast for location");
            System.out.println("4. Show forecast for location using city's name");
            System.out.println("5. Exit application");

            int response = scanner.nextInt();

            switch (response) {
                case 1:
                    addNewLocation();
                    break;
                case 2:
                    showAllLocations();
                    break;
                case 3:
                    getForecast();
                    break;
                case 4:
                    getForecastByCityName();
                    break;
                case 5:
                    return;
            }
        }
    }

    public void addNewLocation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pass city's name: ");
        String cityName = scanner.nextLine();
        System.out.println("Pass latitude: ");
        Double latitude = scanner.nextDouble();
        System.out.println("Pass longitude: ");
        Double longitude = scanner.nextDouble();
        System.out.println("Pass region: ");
        String region = scanner.nextLine();
        scanner.nextLine();
        System.out.println("Pass country's name: ");
        String country = scanner.nextLine();

        String httpResponseBody = locationController.createNewLocation(cityName, latitude, longitude, region, country);
        System.out.println("Response from the server: " + httpResponseBody);
        System.out.println();
    }

    public void showAllLocations() {
        String httpResponseBody = locationController.showAllLocations();
        System.out.println("Response from the server: " + httpResponseBody);
        System.out.println();
    }

    public void getForecast() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pass city's id: ");
        Long locationId = scanner.nextLong();
        System.out.println("Pass day of month: ");
        int day = scanner.nextInt();
        System.out.println("Pass month(1 - 12): ");
        int month = scanner.nextInt();
        System.out.println("Pass year: ");
        int year = scanner.nextInt();

        LocalDate date = LocalDate.of(year, month, day);

        String httpResponseBody = forecastController.getForecast(locationId, date);
        System.out.println("Response from the server: " + httpResponseBody);
        System.out.println();
    }

    private void getForecastByCityName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pass city's name: ");
        String city = scanner.nextLine();
        System.out.println("Pass day of month: ");
        int day = scanner.nextInt();
        System.out.println("Pass month(1 - 12): ");
        int month = scanner.nextInt();
        System.out.println("Pass year: ");
        int year = scanner.nextInt();

        LocalDate date = LocalDate.of(year, month, day);

        String httpResponseBody = forecastController.getForecastByCityName(city, date);
        System.out.println("Response from the server: " + httpResponseBody);
        System.out.println();
    }
}
