import merkle_tree.DummyTreeNode;
import misc.Common;
import primitives.BlockHeader;
import primitives.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

public class BlockHeaderTest {
    long timestamp = 12345;
    LinkedList<Transaction> transactionList;

    @Test
    void hashDeterminismTest() {
        transactionList = TransactionTest.transactionListInit();

        BlockHeader blockHeader = new BlockHeader(
                new byte[Common.hashLength],
                new DummyTreeNode(transactionList), timestamp
        );
        byte[] expectedHash = blockHeader.getHash();

        for (int i = 0; i < 50; ++i) {
            transactionList = TransactionTest.transactionListInit();
            blockHeader = new BlockHeader(
                    new byte[Common.hashLength],
                    new DummyTreeNode(transactionList), timestamp
            );
            Assertions.assertArrayEquals(expectedHash, blockHeader.getHash());
        }
    }

    @Test
    void toStringTest() {
        transactionList = TransactionTest.transactionListInit();
        System.out.println(
                new BlockHeader(
                        new byte[Common.hashLength],
                        new DummyTreeNode(transactionList), timestamp
                ).toString()
        );
    }
}
