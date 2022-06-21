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
public class TokenClientTest {

    private static final String TOKEN = "abc123";

    @InjectMocks
    private TokenClient tokenClient;

    @Mock
    private RestTemplate restTemplate;


    @Test
    public void whenGetToken_thenSuccess() {
        String email = "example@gmail.com";
        Mockito.when(restTemplate.getForObject(String.format(TokenClient.TOKEN_URI, email), TokenClient.TokenBody.class))
                .thenReturn(new TokenClient.TokenBody(TOKEN));

        String actualToken = tokenClient.getToken(email);
        assertEquals(TOKEN, actualToken);
    }

    @Test
    public void givenRestClientException_whenGetToken_thenResourceAccessException() {
        String email = "example@gmail.com";
        Mockito.when(restTemplate.getForObject(String.format(TokenClient.TOKEN_URI, email), TokenClient.TokenBody.class))
                .thenThrow(RestClientException.class);

        assertThrows(ResourceAccessException.class, () -> tokenClient.getToken(email));
    }

}