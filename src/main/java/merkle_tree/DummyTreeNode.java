package merkle_tree;

import misc.Common;
import primitives.Transaction;

import java.util.LinkedList;

public class DummyTreeNode implements Hashable{
    byte[] hash;

    public DummyTreeNode(LinkedList<Transaction> transactionsList) {
        StringBuilder toHash = new StringBuilder();
        for(Transaction transaction : transactionsList) {
            toHash.append(transaction.toString());
        }
        hash = Common.hashBytes(toHash.toString().getBytes());
    }

    @Override
    public byte[] getHash() {
        return hash;
    }
}
