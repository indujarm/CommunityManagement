package com.example.module2.service;

import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Module1Client {
    private final RestTemplate restTemplate = new RestTemplate();

    public User fetchUserByEmail(String email) {
        String url = "http://localhost:8080/user/" + email; // Module 1 URL
        return restTemplate.getForObject(url, User.class);
    }
}
