import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Class handling encryption, decryption and file writing & reading.
 */
public class FileHandler {

    /**
     * Converts SecretKey to bytes, write key to file.
     * @param key = SecretKey.
     * @param file = file to store secret key.
     */
    public void writeSecretKeyToFile(SecretKey key, String file) {
        byte[] rawDataKey = key.getEncoded();

        try (FileOutputStream out = new FileOutputStream(file)) {
            out.write(rawDataKey);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Read secret key from file - convert bytes to Secret Key.
     * @param file = file of secret key in bytes.
     * @return SecretKey.
     * @throws IOException e.
     */
    public SecretKey readSecretKeyFromFile(String file) throws IOException {
        byte[] secretKey;

        try (FileInputStream in = new FileInputStream(file)) {
            secretKey = in.readAllBytes();
        }
        SecretKey originalKey = new SecretKeySpec(secretKey, 0, secretKey.length, "AES");
        return originalKey;
    }


    /**
     * Read text file (to be encrypted), convert content to byte array.
     * @param data = file to read.
     * @return text in byte[].
     */
    public byte[] readFile(String data) {
        File textFile = new File(data);
        byte[] textBytes = new byte[(int) textFile.length()];

        try (FileInputStream in = new FileInputStream(textFile)) {
            in.read(textBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return textBytes;
    }


    /**
     * Encrypt data file using secret key , save to file 'encryptedData'.
     * @param secretKey = file name containing secret key.
     * @throws IOException e.
     */
    public DataStore encryptFileContent(byte[] textBytes, String secretKey) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        // Read secret key from text file.
        SecretKey key = readSecretKeyFromFile(secretKey);

        GCMParameterSpec iv = generateIv();

        // Encrypt text (bytes)
        Cipher encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        encryptionCipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] encryptedTextBytes = encryptionCipher.doFinal(textBytes);

        DataStore dataStore = new DataStore(encryptedTextBytes, iv.getIV());

        return dataStore;
    }


    /**
     * Write encrypted data to file (encryptedData).
     */
    public void writeEncryptedDataToFile(DataStore dataStore, String file) {
        try (ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream(file))){
            out.writeObject(dataStore);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Decrypt the data (read from file).
     * @param file = the file which content to be decrypted
     * @param secretKey = Secret key stored in file.
     * @return decrypted data in bytes.
     * @throws IOException e.
     * @throws NoSuchPaddingException e.
     * @throws NoSuchAlgorithmException e.
     * @throws InvalidKeyException e.
     * @throws InvalidAlgorithmParameterException e.
     * @throws IllegalBlockSizeException e.
     * @throws BadPaddingException e.
     */
    public byte[] decryptDataFile(String file, String secretKey) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

        // Get data from EncryptedData object
        DataStore dataStore = readEncryptedDataFromFile(file);

        byte[] encryptedDataBytes = dataStore.getEncryptedData();
        GCMParameterSpec gcmSpec = new GCMParameterSpec(128, dataStore.getIv());

        // Get Secret Key.
        SecretKey key = readSecretKeyFromFile(secretKey);

        // Configure decryption cipher.
        Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        decryptionCipher.init(Cipher.DECRYPT_MODE, key, gcmSpec);

        // Decrypt encryptedDataBytes (encrypted byte file)
        byte[] decryptedDataBytes = decryptionCipher.doFinal(encryptedDataBytes);

        return decryptedDataBytes;
    }


    /**
     * Read data (from file) to be decrypted.
     * Helper method used in decryptDataFile().
     * @param file = file which content to be decrypted.
     * @return dataStore object holding the data.
     * @throws IOException e.
     */
    public DataStore readEncryptedDataFromFile(String file) throws IOException {

        DataStore dataStore;

        try (ObjectInputStream in = new ObjectInputStream( new FileInputStream(file)))  {
            dataStore = (DataStore) in.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return dataStore;
    }


    /**
     * Write decrypted bytes data to file.
     * @param decryptedDataBytes = bytes to write to the file 'decryptedData' (String file)
     * @param decryptedData = name of file to create.
     */
    public void writeDecryptedDataToFile(byte[] decryptedDataBytes, String decryptedData) {
        File outputFile = new File(decryptedData);

        try (FileOutputStream out = new FileOutputStream(outputFile)) {
            out.write(decryptedDataBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Helper method  generates Initialization Vector.
     * @return GCMParameterSpec.
     */
    public static GCMParameterSpec generateIv() {
        byte[] iv = new byte[12];
        new SecureRandom().nextBytes(iv);
        return new GCMParameterSpec(128, iv);
    }


}