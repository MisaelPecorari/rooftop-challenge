package com.example.rooftopchallenge.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class BlockServiceTest {

    private static final String TOKEN = "abc123";

    @Autowired
    private BlockService blockService;

    @Test
    public void givenUnsortedBlocks_whenCheck_thenReturnSortedBlocks() {
        String[] unsortedBlocks = new String[]{"f319", "3720", "4e3e", "46ec", "c7df", "c1c7", "80fd", "c4ea"};

        String[] sortedBlocks = blockService.check(unsortedBlocks, TOKEN);
        boolean isSorted = blockService.isSorted(sortedBlocks, TOKEN);

        assertEquals(unsortedBlocks.length, sortedBlocks.length);
        assertTrue(isSorted);
    }

    @Test
    public void givenSortedBlocks_whenCheck_thenReturnSameBlocks() {
        String[] unsortedBlocks = new String[]{"f319", "46ec", "c1c7", "3720", "c7df", "c4ea", "4e3e", "80fd"};

        String[] sortedBlocks = blockService.check(unsortedBlocks, TOKEN);

        boolean isSorted = blockService.isSorted(sortedBlocks, TOKEN);

        assertEquals(unsortedBlocks.length, sortedBlocks.length);
        assertTrue(isSorted);
    }

}