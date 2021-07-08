package primitives;

import merkle_tree.DummyTreeNode;
import misc.Common;

import java.util.LinkedList;

public class Block {
    private static long blockCounter = 0;

    private final long blockId;
    private byte[] blockHash;
    private final BlockHeader blockHeader;
    private final LinkedList<Transaction> transactionList;

    public Block(byte[] previousBlockHash, LinkedList<Transaction> transactionList) {
        blockId = blockCounter++;
        this.transactionList = transactionList;
        blockHeader = new BlockHeader(previousBlockHash, new DummyTreeNode(transactionList));
        calculateBlockHash();
    }

    public BlockHeader getBlockHeader() {
        return blockHeader;
    }

    public byte[] calculateBlockHash() {
        return blockHash = blockHeader.getHash();
    }

    public byte[] getBlockHash() {
        return blockHash;
    }

    @Override
    public String toString() {
        return "Block{" +
                "\n\tblockId: \"" + blockId +
                "\", \n\tblockHash: \"" + BlockHeader.bytesToHexString(blockHash) +
                "\", \n\tblockHeader: \"" + Common.increaseStringIndentation(blockHeader.toString()) +
                "\", \n\ttransactionList: \"" + Common.increaseStringIndentation(transactionList.toString()) +
                "\"\n}";
    }
}
