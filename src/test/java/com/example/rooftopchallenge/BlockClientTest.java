package com.example.rooftopchallenge;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BlockClientTest {

    private static final String TOKEN = "abc123";

    @InjectMocks
    private BlockClient blockClient;

    @Mock
    private RestTemplate restTemplate;


    @Test
    public void whenGetBlocks_thenSuccess() {
        List<String> expectedBlocks = List.of("first", "second", "third");
        Mockito.when(restTemplate.getForObject(String.format(BlockClient.BLOCKS_URI, TOKEN), BlockClient.BlocksBody.class))
                .thenReturn(new BlockClient.BlocksBody(expectedBlocks));

        List<String> actualBlocks = blockClient.getBlocks(TOKEN);
        assertIterableEquals(expectedBlocks, actualBlocks);
    }

    @Test
    public void givenNullBody_whenGetBlocks_thenResourceAccessException() {
        Mockito.when(restTemplate.getForObject(String.format(BlockClient.BLOCKS_URI, TOKEN), BlockClient.BlocksBody.class))
                .thenReturn(null);

        assertThrows(ResourceAccessException.class, () -> blockClient.getBlocks(TOKEN));
    }

    @Test
    public void whenGetToken_thenSuccess() {
        String email = "example@gmail.com";
        Mockito.when(restTemplate.getForObject(String.format(BlockClient.TOKEN_URI, email), BlockClient.TokenBody.class))
                .thenReturn(new BlockClient.TokenBody(TOKEN));

        String actualToken = blockClient.getToken(email);
        assertEquals(TOKEN, actualToken);
    }

    @Test
    public void givenNullBody_whenGetToken_thenResourceAccessException() {
        String email = "example@gmail.com";
        Mockito.when(restTemplate.getForObject(String.format(BlockClient.TOKEN_URI, email), BlockClient.TokenBody.class))
                .thenReturn(null);

        assertThrows(ResourceAccessException.class, () -> blockClient.getToken(email));
    }

}