import java.io.Serial;
import java.io.Serializable;

/**
 * Object holding encrypted data.
 * When serialized, correct iv (Initialization Vector) belongs to encryption/decryption.
 * iv cannot be altered, i.e. has to be the same before and after encryption and decryption.
 */
public class DataStore implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final byte[] encryptedData;
    private final byte[] iv;

    public DataStore(byte[] encryptedData, byte[] iv) {
        this.encryptedData = encryptedData;
        this.iv = iv;
    }

    public byte[] getEncryptedData() {
        return encryptedData;
    }

    public byte[] getIv() {
        return iv;
    }
}
