package com.example.rooftopchallenge;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@Service
@AllArgsConstructor
public class CheckClient {


    private final RestTemplate restTemplate;
    private static final String BASE_URI = "https://rooftop-career-switch.herokuapp.com";
    protected static final String CHECK_URI = BASE_URI + "/check?token=%s";


    public boolean check(List<String> blocks, String token) {
        MessageBody messageBody = restTemplate.postForObject(String.format(CHECK_URI, token), new BlockBody(blocks), MessageBody.class);
        if (messageBody != null) {
            return messageBody.message;
        } else {
            throw new ResourceAccessException("Error checking blocks");
        }
    }

    public boolean check(String content, String token) {
        MessageBody messageBody = restTemplate.postForObject(String.format(CHECK_URI, token), new EncodedBody(content), MessageBody.class);
        if (messageBody != null) {
            return messageBody.message;
        } else {
            throw new ResourceAccessException("Error checking blocks");
        }
    }

    @AllArgsConstructor
    @EqualsAndHashCode
    protected static class BlockBody {
        private List<String> blocks;
    }

    @AllArgsConstructor
    @EqualsAndHashCode
    protected static class EncodedBody {
        private String encoded;
    }

    @AllArgsConstructor
    protected static class MessageBody {
        private boolean message;
    }
}
