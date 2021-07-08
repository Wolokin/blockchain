import primitives.Transaction;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

public class TransactionTest {
    public static Transaction transaction0 = new Transaction(
            "Transaction0".getBytes(StandardCharsets.UTF_8),
            "Some transaction data".getBytes(StandardCharsets.UTF_8),
            "Transaction signature".getBytes(StandardCharsets.UTF_8)
    );
    public static Transaction transaction1 = new Transaction(
            "Transaction1".getBytes(StandardCharsets.UTF_8),
            "Some other transaction data".getBytes(StandardCharsets.UTF_8),
            "A different transaction signature".getBytes(StandardCharsets.UTF_8)
    );
    public static LinkedList<Transaction> transactionListInit() {
        LinkedList<Transaction> newTransactionList = new LinkedList<>();
        newTransactionList.add(transaction0);
        newTransactionList.add(transaction1);
        return newTransactionList;
    }

    @Test
    void toStringTest() {
        System.out.println(transaction0);
        System.out.println(transaction1);
    }
}
