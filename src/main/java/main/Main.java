package main;

import misc.Common;
import primitives.Block;
import primitives.BlockHeader;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Block block = new Block(new byte[Common.hashLength], new LinkedList<>());
        BlockHeader blockHeader = block.getBlockHeader();
        System.out.println(BlockHeader.bytesToHexString(blockHeader.getHash()));
    }
}
