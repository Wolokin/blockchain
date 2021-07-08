package primitives;

import merkle_tree.Hashable;
import misc.Common;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.time.Instant;

public class BlockHeader implements Hashable {
    private final byte[] previousBlockHash;
    private final Hashable merkleRoot;
    private final long timestamp;

    public BlockHeader(byte[] previousBlockHash, Hashable merkleRoot, long timestamp) {
        this.previousBlockHash = previousBlockHash;
        this.merkleRoot = merkleRoot;
        this.timestamp = timestamp;
    }

    public BlockHeader(byte[] previousBlockHash, Hashable merkleRoot) {
        this(previousBlockHash, merkleRoot, Instant.now().toEpochMilli());
    }

    // Courtesy of https://stackoverflow.com/a/943963/14708982
    public static String bytesToHexString(byte[] bytes) {
        BigInteger bi = new BigInteger(1, bytes);
        return String.format("%0" + (bytes.length << 1) + "X", bi);
    }

    public byte[] getPreviousBlockHash() {
        return previousBlockHash;
    }

    @Override
    public byte[] getHash() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        try {
            dos.write(previousBlockHash);
            dos.write(merkleRoot.getHash());
            dos.writeLong(timestamp);
            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Common.hashBytes(baos.toByteArray());
    }

    @Override
    public String toString() {
        return "BlockHeader{" +
                "\n\tpreviousBlockHash: \"" + BlockHeader.bytesToHexString(previousBlockHash) +
                "\",\n\tmerkleRoot: \"" + merkleRoot +
                "\",\n\ttimestamp: \"" + timestamp +
                "\"\n}";
    }
}
