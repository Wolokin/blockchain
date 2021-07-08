import misc.Common;
import primitives.Block;
import primitives.Transaction;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

public class BlockTest {
    @Test
    void toStringTest() {
        LinkedList<Transaction> transactionList = TransactionTest.transactionListInit();

        Block block = new Block(new byte[Common.hashLength], transactionList);
        System.out.println(block);
    }
}
