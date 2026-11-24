import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Application encrypts the file 'data' with the secret key in the file 'secretKey'
 * and saves what has been encrypted in the file 'encryptedData'.
 */
// run the program: java EncryptHandler <data> <secretKey> <encryptedData>
// java EncryptHandler data secretKey encryptedData
public class EncryptHandler {

    public static void main (String[] args) throws IOException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        String data = args[0];             // file to encrypt.
        String secretKey = args[1];        // file containing the secret key.
        String encryptedData = args[2];    // file to be created containing encrypted data from file 'data'.

        FileHandler fileHandler = new FileHandler();

        // Read file to be encrypted. Return in byte format.
        byte[] textBytes = fileHandler.readFile(data);

        // Encrypt text in byte format using the secret key. Return encrypted byte data.
        DataStore dataStore = fileHandler.encryptFileContent(textBytes, secretKey);

        // Write encrypted byte data to file (encryptedData)
        fileHandler.writeEncryptedDataToFile(dataStore, encryptedData);
    }
}
