package com.example.rooftopchallenge.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.rooftopchallenge.client.BlockClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BlockServiceIntegrationTest {

    @Autowired
    private BlockClient blockClient;
    @Autowired
    private BlockService blockService;

    @Test
    public void givenUnsortedBlocks_whenCheck_thenReturnSortedBlocks() {
        String token = blockClient.getToken("example@gmail.com");
        String[] unsortedBlocks = blockClient.getBlocks(token);

        String[] sortedBlocks = blockService.check(unsortedBlocks, token);
        boolean isSorted = blockService.isSorted(sortedBlocks, token);

        assertEquals(unsortedBlocks.length, sortedBlocks.length);
        assertTrue(isSorted);
    }

}
