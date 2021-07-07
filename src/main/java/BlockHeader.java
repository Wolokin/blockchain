import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

public class BlockHeader implements Hashable{
    private final String previousBlockHash;
    private final Hashable merkleRoot;
    private final long timestamp;
    private int nonce;

    private MessageDigest digest = null;    // used for sha-256

    public BlockHeader(String previousBlockHash, Hashable merkleRoot) {
        this.previousBlockHash = previousBlockHash;
        this.merkleRoot = merkleRoot;
        timestamp = Instant.now().toEpochMilli();
        nonce = 0;

        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert digest != null;
    }

    // Courtesy of https://stackoverflow.com/a/943963/14708982
    public static String toHex(byte[] bytes) {
        BigInteger bi = new BigInteger(1, bytes);
        return String.format("%0" + (bytes.length << 1) + "X", bi);
    }

    @Override
    public String getHash() {
        String toHash = previousBlockHash
                + merkleRoot.getHash()
                + timestamp
                + nonce;
        byte[] encodedHash = digest.digest(
                toHash.getBytes(StandardCharsets.UTF_8));
        return toHex(encodedHash);
    }

    public void setNonce(int newNonce) {
        nonce = newNonce;
    }
}
