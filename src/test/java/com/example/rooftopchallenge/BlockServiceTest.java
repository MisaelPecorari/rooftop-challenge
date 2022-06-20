package com.example.rooftopchallenge;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BlockServiceTest {

    @InjectMocks
    private BlockService blockService;

    @Mock
    private CheckClient checkClient;

    @Test
    public void givenUnsortedBlocks_whenCheck_thenReturnSortedBlocks() {
        String[] unsortedBlocks = new String[]{"first", "third", "second"};
        String token = "abc123";

        when(checkClient.check(new String[]{"first", "third"}, token)).thenReturn(false);
        when(checkClient.check(new String[]{"first", "second"}, token)).thenReturn(true);

        String[] sortedBlocks = blockService.check(unsortedBlocks, token);

        assertEquals(unsortedBlocks.length, sortedBlocks.length);

        Mockito.verify(checkClient, atLeast(1)).check(any(String[].class), eq(token));
    }

}