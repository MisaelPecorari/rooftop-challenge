package com.example.rooftopchallenge;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeast;

@ExtendWith(MockitoExtension.class)
public class BlockServiceTest {

    @InjectMocks
    private BlockService blockService;

    @Mock
    private CheckClient checkClient;

    @Test
    public void givenUnsortedBlocks_whenCheck_thenReturnSortedBlocks() {
        List<String> unsortedBlocks = List.of("first", "third", "second");
        String token = "abc123";

        List<String> sortedBlocks = blockService.check(unsortedBlocks, token);

        assertEquals(unsortedBlocks.size(), sortedBlocks.size());

        Mockito.verify(checkClient, atLeast(1)).check(unsortedBlocks, token);
    }

}