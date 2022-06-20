package com.example.rooftopchallenge;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class BlockService {

    private final CheckClient checkClient;

    public List<String> check(List<String> blocks, String token) {
        return blocks;
    }


}
