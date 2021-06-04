package com.weather;

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
            System.out.println("4. Exit application");

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
                    return;
            }
        }
    }

    public void addNewLocation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pass city's name: ");
        String cityName = scanner.nextLine();
        System.out.println("Pass latitude: ");
        Double length = scanner.nextDouble();
        System.out.println("Pass longitude: ");
        Double width = scanner.nextDouble();
        System.out.println("Pass region: ");
        String region = scanner.nextLine();
        scanner.nextLine();
        System.out.println("Pass country's name: ");
        String country = scanner.nextLine();

        String httpResponseBody = locationController.createNewLocation(cityName, length, width, region, country);
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
        System.out.println("Pass day from today(1 - 8): ");
        Integer date = scanner.nextInt();

        String httpResponseBody = forecastController.getForecast(locationId, date);
        System.out.println("Response from the server: " + httpResponseBody);
        System.out.println();
    }
}
