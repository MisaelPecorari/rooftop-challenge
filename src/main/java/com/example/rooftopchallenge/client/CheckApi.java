package com.example.rooftopchallenge.client;

public interface CheckApi {

    boolean check(String[] blocks, String token);

    boolean check(String content, String token);
}
