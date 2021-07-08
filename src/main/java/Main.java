import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Block block = new Block(new byte[Consts.hashLength], new LinkedList<>());
        BlockHeader blockHeader = block.getBlockHeader();
        System.out.println(BlockHeader.bytesToHexString(blockHeader.getHash()));
    }
}
