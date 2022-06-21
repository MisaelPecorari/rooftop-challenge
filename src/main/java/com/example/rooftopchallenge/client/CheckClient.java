package com.example.rooftopchallenge.client;

import java.util.Objects;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Configuration
@ConditionalOnProperty(prefix = "api.check", name = "use", havingValue = "server")
public class CheckClient extends BaseClient implements CheckApi {

    protected static final String CHECK_URI = BASE_URI + "/check?token=%s";

    public CheckClient(RestTemplate restTemplate) {
        super(restTemplate);
    }


    public boolean check(String[] blocks, String token) {
        try {
            MessageBody messageBody = Objects.requireNonNull(restTemplate.postForObject(String.format(CHECK_URI, token), new BlockBody(blocks), MessageBody.class));
            return messageBody.message;
        } catch (RestClientException e) {
            throw new ResourceAccessException("Error checking blocks: " + e);
        }
    }

    public boolean check(String content, String token) {
        try {
            MessageBody messageBody = Objects.requireNonNull(restTemplate.postForObject(String.format(CHECK_URI, token), new EncodedBody(content), MessageBody.class));
            return messageBody.message;
        } catch (RestClientException e) {
            throw new ResourceAccessException("Error checking content: " + e);
        }
    }

    @NoArgsConstructor
    @Data
    @AllArgsConstructor
    @EqualsAndHashCode
    protected static class BlockBody {
        private String[] blocks;
    }

    @NoArgsConstructor
    @Data
    @AllArgsConstructor
    @EqualsAndHashCode
    protected static class EncodedBody {
        private String encoded;
    }

    @NoArgsConstructor
    @Data
    @AllArgsConstructor
    protected static class MessageBody {
        private boolean message;
    }
}
