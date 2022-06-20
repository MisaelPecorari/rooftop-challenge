package com.example.rooftopchallenge.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@ConditionalOnProperty(prefix = "api.check", name = "use", havingValue = "mock")
@Slf4j
public class CheckClientMock implements CheckApi {

    private final String[] sortedBlocks;

    public CheckClientMock() {
        log.info("Initializing CheckClient Mock");
        sortedBlocks = new String[]{"f319", "46ec", "c1c7", "3720", "c7df", "c4ea", "4e3e", "80fd"};
    }

    @Override
    public boolean check(String[] blocks, String token) {
        for (int i = 0; i < sortedBlocks.length - blocks.length; i++) {
            List<String> subset = Arrays.asList(sortedBlocks).subList(i, i + blocks.length);
            if (subset.equals(Arrays.asList(blocks))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean check(String content, String token) {
        StringBuilder builder = new StringBuilder();
        Arrays.stream(sortedBlocks).forEach(builder::append);
        String validChain = builder.toString();
        return validChain.equals(content);
    }
}
