package com.example.parkingfeecalculator.controller;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/featuretoggledemo")
public class FeatureToggleDemoController {
    // change toggle online: https://www.npoint.io/docs/0752974a4a6668c526af
    private final String toggleUrl = "https://api.npoint.io/0752974a4a6668c526af";
    // step 3: fill in your toggle url here, e.g. "https://api.npoint.io/0752974a4a6668c526af";
    private final String testToggleUrl = "";
    // step 3: fill in your toggle name here, e.g. "toggle";
    private final String testToggleName = "";
    @GetMapping("/hello")
    public ResponseEntity<?> hello() {
        try {
            if(isFeatureEnabled(toggleUrl, "toggle")) {
                return ResponseEntity.ok().body("Hello, World!");
            }

            return ResponseEntity.status(403).body("Feature is disabled");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error occurred while checking feature toggle");
        }
    }

    @GetMapping("/testToggle")
    public ResponseEntity<?> testToggle() {
        try {
            if(isFeatureEnabled(testToggleUrl, testToggleName)) {
                // step 4: when toggle on
                return ResponseEntity.ok().body("Hello, World!");
            }

            // step 4: when toggle off
            return ResponseEntity.status(403).body("Feature is disabled");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error occurred while checking feature toggle");
        }
    }

    private boolean isFeatureEnabled(String url, String toggleName) throws Exception {
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.body());

            return jsonNode.get(toggleName).asBoolean();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
