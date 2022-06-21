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
public class BlockClient extends BaseClient {
    protected static final String BLOCKS_URI = BASE_URI + "/blocks?token=%s";

    public BlockClient(RestTemplate restTemplate) {
        super(restTemplate);
    }

    public String[] getBlocks(String token) {
        try {
            BlocksBody blocksBody = Objects.requireNonNull(restTemplate.getForObject(String.format(BLOCKS_URI, token), BlocksBody.class));
            return blocksBody.data;
        } catch (RestClientException e) {
            throw new ResourceAccessException("Error getting blocks: " + e);
        }
    }


    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    protected static class BlocksBody {
        private String[] data;
    }
}
