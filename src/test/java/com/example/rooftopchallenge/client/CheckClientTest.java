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
public class CheckClientTest {

    private static final String TOKEN = "abc123";

    @InjectMocks
    private CheckClient checkClient;

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void whenCheckBlocks_thenSuccess() {
        String[] blocks = new String[]{};
        Mockito.when(restTemplate.postForObject(String.format(CheckClient.CHECK_URI, TOKEN),
                new CheckClient.BlockBody(blocks), CheckClient.MessageBody.class))
                .thenReturn(new CheckClient.MessageBody(true));

        boolean isValid = checkClient.check(blocks, TOKEN);

        assertTrue(isValid);
    }

    @Test
    public void givenNullBody_whenCheckBlocks_thenResourceAccessException() {
        String[] blocks = new String[]{};
        Mockito.when(restTemplate.postForObject(String.format(CheckClient.CHECK_URI, TOKEN),
                        new CheckClient.BlockBody(blocks), CheckClient.MessageBody.class))
                .thenThrow(RestClientException.class);

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
                .thenThrow(RestClientException.class);

        assertThrows(ResourceAccessException.class, () -> checkClient.check(encoded, TOKEN));
    }

}