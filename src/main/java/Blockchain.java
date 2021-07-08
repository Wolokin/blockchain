import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class Blockchain {
    private final LinkedList<Block> blockchain;

    public Blockchain() {
        blockchain = new LinkedList<>();

        Block originBlock = new Block(new byte[Consts.hashLength], new LinkedList<>());
        blockchain.add(originBlock);
    }

    public Block createBlock(LinkedList<Transaction> transactionsList) {
        return new Block(blockchain.getLast().getBlockHash(), transactionsList);
    }

    public void addBlock(Block block) {
        blockchain.add(block);
    }

    public void createAndAddBlock(LinkedList<Transaction> transactionsList) {
        addBlock(createBlock(transactionsList));
    }

    public Block getMostRecentBlock() {
        return blockchain.getLast();
    }

    public Iterator<Block> getBlockchainIterator() {
        return blockchain.descendingIterator();
    }

    public long getBlockchainLength() {
        return blockchain.size();
    }

    public boolean verifyBlockchain() {
        Iterator<Block> it = getBlockchainIterator();
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
                "\n\tblockchain: \"" + Consts.increaseStringIndentation(blockchain.toString()) +
                "\"\n}";
    }
}
