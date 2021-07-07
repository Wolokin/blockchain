import java.util.LinkedList;

public class Block {
    private final BlockHeader blockHeader;
    private LinkedList<Transaction> transactionList;

    private static final int difficulty = 2;

    public Block(String previousBlockHash, Hashable merkleRoot, LinkedList<Transaction> transactionList) {
        this.transactionList = transactionList;
        blockHeader = new BlockHeader(previousBlockHash, merkleRoot);
    }

    public void mineBlock() {
        String desiredHashPrefix = "0".repeat(difficulty);
        int nonce = 0;
        do {
            blockHeader.setNonce(nonce++);
        } while(!blockHeader.getHash().startsWith(desiredHashPrefix));
    }

    public BlockHeader getBlockHeader() {
        return blockHeader;
    }
}
