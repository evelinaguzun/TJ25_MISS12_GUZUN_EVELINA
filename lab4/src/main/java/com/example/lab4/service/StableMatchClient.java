package com.example.lab4.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import java.util.Map;

@Service
public class StableMatchClient {

    private final RestTemplate restTemplate;
    private final String stableMatchUrl = "http://localhost:8082/api/matching";

    public StableMatchClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String, Object> solveMatching(Map<String, Object> matchingProblem) {
        // Setează headers cu Content-Type JSON
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Creează request entity cu body și headers
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(matchingProblem, headers);

        // Trimite request-ul
        ResponseEntity<Map> response = restTemplate.postForEntity(
                stableMatchUrl + "/solve",
                request,
                Map.class
        );

        return response.getBody();
    }
}
