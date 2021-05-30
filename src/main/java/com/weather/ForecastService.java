package com.weather;

import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ForecastService {

    public void getForecast(Integer locationId, Integer date) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                // todo
                .uri(URI.create("https://api.openweathermap.org/data/2.5/onecall?lat=50&lon=20&exclude=minutely,hourly&appid=9042c4ef58824c7627c67bcf1007f755"))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            System.out.println(responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
