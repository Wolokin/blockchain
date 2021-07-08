import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Consts {
    public static int hashLength = 32;

    public static byte[] hashBytes(byte[] toHash) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert digest != null;
        return digest.digest(toHash);
    }

    public static String increaseStringIndentation(String s) {
        return s.replace("\n", "\n\t");
    }
}
