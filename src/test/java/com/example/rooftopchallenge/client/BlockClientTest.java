package com.example.rooftopchallenge.client;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
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
        String[] expectedBlocks = new String[]{"first", "second", "third"};
        Mockito.when(restTemplate.getForObject(String.format(BlockClient.BLOCKS_URI, TOKEN), BlockClient.BlocksBody.class))
                .thenReturn(new BlockClient.BlocksBody(expectedBlocks));

        String[] actualBlocks = blockClient.getBlocks(TOKEN);
        assertArrayEquals(expectedBlocks, actualBlocks);
    }

    @Test
    public void givenRestClientException_whenGetBlocks_thenResourceAccessException() {
        Mockito.when(restTemplate.getForObject(String.format(BlockClient.BLOCKS_URI, TOKEN), BlockClient.BlocksBody.class))
                .thenThrow(RestClientException.class);

        assertThrows(ResourceAccessException.class, () -> blockClient.getBlocks(TOKEN));
    }

}