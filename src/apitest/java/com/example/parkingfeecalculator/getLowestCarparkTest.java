package com.example.parkingfeecalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.parkingfeecalculator.model.CarParkFee;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class getLowestCarparkTest {
    private final String baseUrl = "http://localhost:8080/api/feecalculator/getLowestCarpark?vehicleType=%s&startTime=%s&endTime=%s"; 
    private final String startTime = "2024-03-20T10:00:00";
    private final String endTime = "2024-03-22T12:00:00";
    private final String vehicleType = "CAR";
    @Test
    public void shouldReturn200WithLowestCarpark() throws Exception {
        HttpClient  client = HttpClient.newHttpClient();
        String url = String.format(baseUrl, vehicleType, startTime, endTime);
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode());
        CarParkFee carParkFee = new ObjectMapper().readValue(response.body(), CarParkFee.class);
        assertEquals("Plaza Singapura Car Park", carParkFee.getName());
        assertEquals(89.35, carParkFee.getFee());
    }

    @Test
    public void shouldReturn400IfVehicleTypeIsInvalid() throws Exception {
        String invalidVehicleType = "CAR1";
        HttpClient  client = HttpClient.newHttpClient();
        String url = String.format(baseUrl, 
        invalidVehicleType, startTime, endTime);
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(400, response.statusCode());
        assertEquals("Invalid vehicle type", response.body());
    }
}