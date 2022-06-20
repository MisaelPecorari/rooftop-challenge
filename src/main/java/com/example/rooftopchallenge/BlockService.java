package com.example.rooftopchallenge;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class BlockService {

    private final CheckClient checkClient;

    public String[] check(String[] blocks, String token) {
        for (int i = 0; i < blocks.length - 2; i++) {
            int nextBlock = getNextConsecutive(blocks, i, token);
            moveBlocks(blocks, i + 1, nextBlock);
        }
        return blocks;
    }

    private int getNextConsecutive(String[] blocks, int currentBlockIndex, String token) {
        int blockIndexToCompareWith = currentBlockIndex + 1;
        boolean isConsecutive = false;
        while (!isConsecutive) {
            isConsecutive = checkClient.check(new String[]{blocks[currentBlockIndex], blocks[blockIndexToCompareWith]}, token);
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

}
