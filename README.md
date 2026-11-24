### Encrypt and decrypt with symmetric algorithms
The application consists of three separate applications:<br>
* KeyHandler
* EncryptHandler
* DecryptHandler

The application encrypts and decrypts an arbitrary file using the symmetric algorithm AES. 

Packages in use:
java.security  
javax.crypto

The project contains the following three applications to run in CLI/terminal:  
- java KeyHandler ```<secretKey>``` creating a (secret) key and saves this key (not encrypted) in the file ```<secretKey>```.  
- java EncryptHandler ```<data> <secretKey> <encryptedData>``` encrypts the file ```<data>``` containing (the secret) key in the file ```<secretKey>``` and saves what has been encrypted in the file ```<encryptedData>```.
- java DecryptHandler ```<encryptedData> <secretKey> <decryptetData>``` decrypts the file ```<encryptedData>``` with (the secret) key in the file ```<secretKey>``` and saves what has been decrypted in the file ```<decryptedData>```.

### Compile all source files:
In src:
javac *.java

### Run the applications:
- java KeyHandler ```<secretKey>```
- java EncryptHandler ```<data> <secretKey> <encryptedData>```
- java DecryptHandler ```<encryptedData> <secretKey> <decryptetData>```

### Files:
<span style="color:lightgreen">File 'data' and created file/s are stored in /src.</span><br>

### Info:
<b>secretKey:</b> name of file in which secret key will be stored.<br>
<b>data:</b> existing file containing text to be encrypted.<br>
<b>encryptedData:</b> name of file to be created containing encrypted data from 'data'.<br>
<b>decryptedData:</b> file to be created containing text from decrypted data in encryptedData.  