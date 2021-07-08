import java.util.Arrays;

public class Transaction {
    private final byte[] transactionId;
    private final byte[] data;
    private final byte[] signature;

    public Transaction(byte[] transactionId, byte[] data, byte[] signature) {
        this.transactionId = transactionId;
        this.data = data;
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "Transaction{\n" +
                "\ttransactionId: \"" + new String(transactionId) +
                "\",\n\tdata: \"" + new String(data) +
                "\",\n\tsignature: \"" + new String(signature) +
                "\"\n}";
    }
}
