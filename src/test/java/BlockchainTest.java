import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class BlockchainTest {
    Blockchain blockchain;
    Random random = new Random(42);

    public static Blockchain blockchainInit() {
        Blockchain newBlockchain = new Blockchain();
        newBlockchain.createAndAddBlock(TransactionTest.transactionListInit());
        LinkedList<Transaction> reversedTransactionList = TransactionTest.transactionListInit();
        Collections.reverse(reversedTransactionList);
        newBlockchain.createAndAddBlock(reversedTransactionList);
        return newBlockchain;
    }

    @Test
    void defaultConstructorTest() {
        blockchain = new Blockchain();
        Assertions.assertEquals(1, blockchain.getBlockchainLength());
    }

    @Test
    void createAndAddBlockTest() {
        blockchain = new Blockchain();
        blockchain.createAndAddBlock(TransactionTest.transactionListInit());
        Assertions.assertEquals(2, blockchain.getBlockchainLength());
    }

    @Test
    void verifyCorrectBlockchainTest() {
        blockchain = blockchainInit();
        Assertions.assertTrue(blockchain.verifyBlockchain());
    }

    @Test
    void verifyIncorrectBlockchainWrongLastBlockTest() {
        blockchain = blockchainInit();
        byte[] someRandomHash = new byte[Consts.hashLength];
        random.nextBytes(someRandomHash);
        Block someRandomBlock = new Block(someRandomHash, TransactionTest.transactionListInit());
        blockchain.addBlock(someRandomBlock);
        Assertions.assertFalse(blockchain.verifyBlockchain());
    }

    @Test
    void verifyIncorrectBlockchainWrongSecondBlockTest() {
        blockchain = new Blockchain();

        byte[] someRandomHash = new byte[Consts.hashLength];
        random.nextBytes(someRandomHash);
        Block someRandomBlock = new Block(someRandomHash, TransactionTest.transactionListInit());
        blockchain.addBlock(someRandomBlock);

        blockchain.createAndAddBlock(TransactionTest.transactionListInit());

        LinkedList<Transaction> reversedTransactionList = TransactionTest.transactionListInit();
        Collections.reverse(reversedTransactionList);
        blockchain.createAndAddBlock(reversedTransactionList);

        Assertions.assertFalse(blockchain.verifyBlockchain());
    }

    @Test
    void toStringTest() {
        blockchain = blockchainInit();
        System.out.println(blockchain);
    }
}
