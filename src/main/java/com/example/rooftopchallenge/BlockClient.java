package com.example.rooftopchallenge;

import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BlockClient {
    private final RestTemplate restTemplate;
    private static final String BASE_URI = "https://rooftop-career-switch.herokuapp.com";
    protected static final String TOKEN_URI = BASE_URI + "/token?email=%s";
    protected static final String BLOCKS_URI = BASE_URI + "/blocks?token=%s";


    public String getToken(String email) {
        TokenBody tokenBody = restTemplate.getForObject(String.format(TOKEN_URI, email), TokenBody.class);
        if (tokenBody != null) {
            return tokenBody.token;
        } else {
            throw new ResourceAccessException("Error getting token");
        }
    }

    public String[] getBlocks(String token) {
        BlocksBody blocksBody = restTemplate.getForObject(String.format(BLOCKS_URI, token), BlocksBody.class);
        if (blocksBody != null) {
            return blocksBody.data;
        } else {
            throw new ResourceAccessException("Error accessing blocks resource");
        }
    }


    @AllArgsConstructor
    protected static class BlocksBody {
        private String[] data;
    }

    @AllArgsConstructor
    protected static class TokenBody {
        private String token;
    }
}
