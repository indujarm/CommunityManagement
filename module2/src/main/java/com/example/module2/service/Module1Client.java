package com.example.module2.service;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class Module1Client {

    @Value("${module1.base.url}")
    private String module1BaseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public User fetchUserByEmail(String email) {
        String url = UriComponentsBuilder.fromHttpUrl(module1BaseUrl)
                .path("/user/" + email)
                .toUriString();
        return restTemplate.getForObject(url, User.class);
    }
}
