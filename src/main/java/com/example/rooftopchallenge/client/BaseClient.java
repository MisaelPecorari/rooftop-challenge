package com.example.rooftopchallenge.client;

import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class BaseClient {

    protected static final String BASE_URI = "https://rooftop-career-switch.herokuapp.com";
    protected final RestTemplate restTemplate;
}
