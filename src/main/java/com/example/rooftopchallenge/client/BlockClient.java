package com.example.rooftopchallenge.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
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
        BlocksBody blocksBody = restTemplate.getForObject(String.format(BLOCKS_URI, token), BlocksBody.class);
        if (blocksBody != null) {
            return blocksBody.data;
        } else {
            throw new ResourceAccessException("Error accessing blocks resource");
        }
    }


    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    protected static class BlocksBody {
        private String[] data;
    }
}
