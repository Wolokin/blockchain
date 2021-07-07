public class Main {
    public static void main(String[] args) {
        Block block = new Block("0".repeat(32), new DummyTreeNode(), null);
        BlockHeader blockHeader = block.getBlockHeader();
        block.mineBlock();
        System.out.println(blockHeader.getHash());
    }
}
