import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.time.Instant;
import java.util.Arrays;

public class BlockHeader implements Hashable{
    private final byte[] previousBlockHash;
    private final Hashable merkleRoot;
    private final long timestamp;

    public BlockHeader(byte[] previousBlockHash, long timestamp, Hashable merkleRoot) {
        this.previousBlockHash = previousBlockHash;
        this.merkleRoot = merkleRoot;
        this.timestamp = timestamp;
    }

    public BlockHeader(byte[] previousBlockHash, Hashable merkleRoot) {
        this(previousBlockHash, Instant.now().toEpochMilli(), merkleRoot);
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

        return Consts.hashBytes(baos.toByteArray());
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
