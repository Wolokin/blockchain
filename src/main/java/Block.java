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

    // Czy podczas weryfikacji nie powinnismy wyliczac hasha zamiast brac gotowego? Poki co tak zrobilem
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
                "\", \n\tblockHeader: \"" + Consts.increaseStringIndentation(blockHeader.toString()) +
                "\", \n\ttransactionList: \"" + Consts.increaseStringIndentation(transactionList.toString()) +
                "\"\n}";
    }
}
