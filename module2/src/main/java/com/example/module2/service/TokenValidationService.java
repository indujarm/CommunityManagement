package com.example.module2.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TokenValidationService {
    private final RestTemplate restTemplate = new RestTemplate();

    public boolean validateToken(String token) {
        String url = "http://localhost:8080/auth/validate-token"; // Adjust Module 1 URL
        return restTemplate.postForObject(url, token, Boolean.class);
    }
}
