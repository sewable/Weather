package com.weather;

import java.util.Scanner;

public class UserInterface {

    private LocationController locationController = new LocationController();

    public void run() {
        System.out.println("Application running");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome in weather application, what do you want to do?");
            System.out.println("1. Add new location");
            System.out.println("2. Exit application");

            int response = scanner.nextInt();

            switch (response) {
                case 1:
                    addNewLocation();
                    break;
                case 2:
                    return;
            }
        }
    }

    public void addNewLocation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pass city's name: ");
        String cityName = scanner.nextLine();
        System.out.println("Pass geographic length: ");
        Double length = scanner.nextDouble();
        System.out.println("Pass geographic width: ");
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
}
