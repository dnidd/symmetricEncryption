import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

/**
 * Create AES secret key.
 */
public class SecretKeyGenerator {

    private SecretKey key;
    private int KEY_SIZE = 256;

    /**
     * Generate Secret AES key.
     * @return SecretKey.
     * @throws NoSuchAlgorithmException e.
     */
    public SecretKey initKey() throws NoSuchAlgorithmException {
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(KEY_SIZE);
        key = generator.generateKey();

        return key;
    }
}
