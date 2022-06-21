package com.example.rooftopchallenge.client;

import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
public class TokenClient extends BaseClient {

    protected static final String TOKEN_URI = BASE_URI + "/token?email=%s";

    public TokenClient(RestTemplate restTemplate) {
        super(restTemplate);
    }

    public String getToken(String email) {
        try {
            TokenBody tokenBody = Objects.requireNonNull(restTemplate.getForObject(String.format(TOKEN_URI, email), TokenBody.class));
            return tokenBody.token;
        } catch (RestClientException e) {
            throw new ResourceAccessException("Error getting token: " + e);
        }
    }


    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    protected static class TokenBody {
        private String token;
    }
}
