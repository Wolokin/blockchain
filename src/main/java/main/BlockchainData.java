package main;

import misc.Common;
import primitives.Block;
import primitives.Transaction;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class BlockchainData {
    private final LinkedList<Block> blocks;

    public BlockchainData() {
        blocks = new LinkedList<>();

        Block originBlock = new Block(new byte[Common.hashLength], new LinkedList<>());
        blocks.add(originBlock);
    }

    /**
     * Creates a block with previousBlockHash already set to the hash of the newest block in the chain.
     * @param transactionsList - list of transactions to be included in this block.
     * @return Newly created block.
     */
    public Block createBlock(LinkedList<Transaction> transactionsList) {
        return new Block(blocks.getLast().getBlockHash(), transactionsList);
    }

    /**
     * Adds block to the chain.
     * @param block - block to be added.
     */
    public void addBlock(Block block) {
        blocks.add(block);
    }

    /**
     * Creates and appends block to the chain.
     * @param transactionsList - transactions to be included in the newly created block.
     */
    public void createAndAddBlock(LinkedList<Transaction> transactionsList) {
        addBlock(createBlock(transactionsList));
    }

    /**
     * Returns an iterator which can be used to traverse the blocks from newest to oldest.
     * @return A descending iterator over blocks.
     */
    public Iterator<Block> getBlocksIterator() {
        return blocks.descendingIterator();
    }

    public long getBlockchainLength() {
        return blocks.size();
    }

    /**
     * Verifies chain integrity by checking if previousBlockHash is truly the hash of a previous block.
     * @return true if no errors were detected. false otherwise.
     */
    public boolean verifyBlockchain() {
        Iterator<Block> it = getBlocksIterator();
        byte[] expectedPreviousBlockHash = it.next().getBlockHeader().getPreviousBlockHash();
        while(it.hasNext()) {
            Block currentBlock = it.next();
            if(!Arrays.equals(currentBlock.calculateBlockHash(), expectedPreviousBlockHash)) {
                return false;
            }
            expectedPreviousBlockHash = currentBlock.getBlockHeader().getPreviousBlockHash();
        }
        return true;
    }

    @Override
    public String toString() {
        return "Blockchain{" +
                "\n\tblockchain: \"" + Common.increaseStringIndentation(blocks.toString()) +
                "\"\n}";
    }
}
