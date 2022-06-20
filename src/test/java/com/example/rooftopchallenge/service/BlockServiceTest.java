package com.example.rooftopchallenge.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BlockServiceTest {

    @Autowired
    private BlockService blockService;

    @Test
    public void givenUnsortedBlocks_whenCheck_thenReturnSortedBlocks() {
        String[] unsortedBlocks = new String[]{"f319", "3720", "4e3e", "46ec", "c7df", "c1c7", "80fd", "c4ea"};
        String token = "abc123";

        String[] sortedBlocks = blockService.check(unsortedBlocks, token);
        boolean isSorted = blockService.isSorted(sortedBlocks, token);

        assertEquals(unsortedBlocks.length, sortedBlocks.length);
        assertTrue(isSorted);
    }

}