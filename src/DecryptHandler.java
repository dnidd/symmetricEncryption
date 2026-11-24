import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Application decrypts the file 'encryptedData' with the secret key in the file 'secretKey'
 * and saves what has been decrypted in the file 'decryptedData'.
 */
// run the program: java DecryptHandler <encryptedData> <secretKey> <decryptedData>
// java DecryptHandler encryptedData secretKey decryptedData
public class DecryptHandler {

    public static void main(String[] args) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, IOException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        String encryptedData = args[0];      // file to be encrypted
        String secretKey = args[1];          // file containing the secret key.
        String decryptedData = args[2];      // file in which decrypted data is written.

        FileHandler fileHandler = new FileHandler();

        // Decrypting the content of the file <encryptedData>.
        // Returns byte array of decrypted data.
        byte[] decryptedDataBytes = fileHandler.decryptDataFile(encryptedData, secretKey);

        // Write decrypted data to file in human-readable language.
        fileHandler.writeDecryptedDataToFile(decryptedDataBytes, decryptedData);
    }
}
