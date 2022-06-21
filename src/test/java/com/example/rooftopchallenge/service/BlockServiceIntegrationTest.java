package com.example.rooftopchallenge.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.rooftopchallenge.client.BlockClient;
import com.example.rooftopchallenge.client.TokenClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class BlockServiceIntegrationTest {

    @Autowired
    private TokenClient tokenClient;
    @Autowired
    private BlockClient blockClient;
    @Autowired
    private BlockService blockService;
    @Value("${api.email}")
    private String email;

    @Test
    public void givenUnsortedBlocks_whenCheck_thenReturnSortedBlocks() {
        log.info("email set {}", email);
        String token = tokenClient.getToken(email);
        String[] unsortedBlocks = blockClient.getBlocks(token);

        String[] sortedBlocks = blockService.check(unsortedBlocks, token);
        boolean isSorted = blockService.isSorted(sortedBlocks, token);

        assertEquals(unsortedBlocks.length, sortedBlocks.length);
        assertTrue(isSorted);
    }

}
