import java.util.LinkedHashMap;
import java.util.LinkedList;

// W ktora strone chcemy moc sie poruszac po blockchainie (prawdopodobnie wstecz), co z wskaznikami wstecznymi?
// Czy chcemy miec random access do blokow?
// Czy robimy podzial na block i blockheader?
// Jakie operacje chcemy obecnie miec (poza oczywistymi jak dodawanie blokow)?

public class Blockchain {
    private LinkedList<Block> blockchain;

    public Blockchain() {
        blockchain = new LinkedList<>();

        Block originBlock = new Block("0".repeat(32), null, null);
        blockchain.add(originBlock);
    }

    public void addBlock() {

    }
}
