public class DummyTreeNode implements Hashable{
    public DummyTreeNode() {
    }


    @Override
    public String getHash() {
        return "0".repeat(32);
    }
}
