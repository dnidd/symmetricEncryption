
import javax.crypto.SecretKey;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Application creates a secret key and saves it not encrypted i the file 'secretKey'.
 */

// run the program: java KeyHandler <secretKey>
// java KeyHandler secretKey
public class KeyHandler {

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

        // the file in which secret key is stored
        String fileName = args[0];

        SecretKeyGenerator keyGenerator = new SecretKeyGenerator();
        FileHandler fileHandler = new FileHandler();

        // Create Secret Key.
        SecretKey key = keyGenerator.initKey();
        // Write Secret Key to file.
        fileHandler.writeSecretKeyToFile(key, fileName);

    }

}
