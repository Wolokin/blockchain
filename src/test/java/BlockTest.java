import org.junit.jupiter.api.Test;

import java.util.LinkedList;

public class BlockTest {
    @Test
    void toStringTest() {
        LinkedList<Transaction> transactionList = TransactionTest.transactionListInit();

        Block block = new Block(new byte[Consts.hashLength], transactionList);
        System.out.println(block);
    }
}
