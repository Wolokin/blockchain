import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

public class BlockHeaderTest {
    long timestamp = 12345;
    LinkedList<Transaction> transactionList;

    @Test
    void hashDeterminismTest() {
        transactionList = TransactionTest.transactionListInit();

        BlockHeader blockHeader = new BlockHeader(
                new byte[Consts.hashLength],
                timestamp,
                new DummyTreeNode(transactionList)
        );
        byte[] expectedHash = blockHeader.getHash();

        for (int i = 0; i < 50; ++i) {
            transactionList = TransactionTest.transactionListInit();
            blockHeader = new BlockHeader(
                    new byte[Consts.hashLength],
                    timestamp,
                    new DummyTreeNode(transactionList)
            );
            Assertions.assertArrayEquals(expectedHash, blockHeader.getHash());
        }
    }

    @Test
    void toStringTest() {
        transactionList = TransactionTest.transactionListInit();
        System.out.println(
                new BlockHeader(
                        new byte[Consts.hashLength],
                        timestamp,
                        new DummyTreeNode(transactionList)
                ).toString()
        );
    }
}
