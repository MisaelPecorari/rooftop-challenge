package com.example.rooftopchallenge;

import java.util.ArrayList;
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
public class CheckClientTest {

    private static final String TOKEN = "abc123";

    @InjectMocks
    private CheckClient checkClient;

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void whenCheckBlocks_thenSuccess() {
        List<String> blocks = new ArrayList<>();
        Mockito.when(restTemplate.postForObject(String.format(CheckClient.CHECK_URI, TOKEN),
                new CheckClient.BlockBody(blocks), CheckClient.MessageBody.class))
                .thenReturn(new CheckClient.MessageBody(true));

        boolean isValid = checkClient.check(blocks, TOKEN);

        assertTrue(isValid);
    }

    @Test
    public void givenNullBody_whenCheckBlocks_thenResourceAccessException() {
        List<String> blocks = new ArrayList<>();
        Mockito.when(restTemplate.postForObject(String.format(CheckClient.CHECK_URI, TOKEN),
                        new CheckClient.BlockBody(blocks), CheckClient.MessageBody.class))
                .thenReturn(null);

        assertThrows(ResourceAccessException.class, () -> checkClient.check(blocks, TOKEN));
    }

    @Test
    public void whenCheckEncoded_thenSuccess() {
        String encoded = "abcdfg";
        Mockito.when(restTemplate.postForObject(String.format(CheckClient.CHECK_URI, TOKEN),
                        new CheckClient.EncodedBody(encoded), CheckClient.MessageBody.class))
                .thenReturn(new CheckClient.MessageBody(true));

        boolean isValid = checkClient.check(encoded, TOKEN);

        assertTrue(isValid);
    }

    @Test
    public void givenNullBody_whenCheckEncoded_thenResourceAccessException() {
        String encoded = "abcdfg";
        Mockito.when(restTemplate.postForObject(String.format(CheckClient.CHECK_URI, TOKEN),
                        new CheckClient.EncodedBody(encoded), CheckClient.MessageBody.class))
                .thenReturn(null);

        assertThrows(ResourceAccessException.class, () -> checkClient.check(encoded, TOKEN));
    }



}