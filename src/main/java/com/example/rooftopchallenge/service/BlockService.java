package com.example.rooftopchallenge.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.example.rooftopchallenge.client.CheckApi;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BlockService {

    private final CheckApi checkClient;
    private int count;

    public BlockService(CheckApi checkClient) {
        this.checkClient = checkClient;
        this.count = 0;
    }

    public String[] check(String[] blocks, String token) {
        count = 0;
        for (int i = 0; i < blocks.length - 2; i++) {
            int nextBlock = getNextConsecutive(blocks, i, token);
            moveBlocks(blocks, i + 1, nextBlock);
        }
        log.info("API called [{}] times", count);
        return blocks;
    }

    private int getNextConsecutive(String[] blocks, int currentBlockIndex, String token) {
        int blockIndexToCompareWith = currentBlockIndex + 1;
        boolean isConsecutive = false;
        while (!isConsecutive) {
            isConsecutive = checkClient.check(new String[]{blocks[currentBlockIndex], blocks[blockIndexToCompareWith]}, token);
            count++;
            if (!isConsecutive) {
                blockIndexToCompareWith++;
            }
        }
        return blockIndexToCompareWith;
    }

    private void moveBlocks(String[] blocks, int oldIndex, int newIndex) {
        String block1 = blocks[oldIndex];
        String block2 = blocks[newIndex];
        blocks[oldIndex] = block2;
        blocks[newIndex] = block1;
    }

    public boolean isSorted(String[] blocks, String token) {
        StringBuilder builder = new StringBuilder();
        Arrays.stream(blocks).forEach(builder::append);
        String content = builder.toString();
        return checkClient.check(content, token);
    }

}
