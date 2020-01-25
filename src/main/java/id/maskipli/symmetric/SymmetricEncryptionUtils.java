package id.maskipli.symmetric;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;

public class SymmetricEncryptionUtils {

    public static final String AES = "AES";
    public static final String AES_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

    public static SecretKey createAESKey() throws Exception {
        SecureRandom secureRandom = new SecureRandom();
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
        keyGenerator.init(256, secureRandom);
        return keyGenerator.generateKey();
    }

    public static byte[] createInitializationVector() {
        byte[] initializationVector = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(initializationVector);
        return initializationVector;
    }

    public static byte[] performAEXEncryption(
            String plainText,
            SecretKey secretKey,
            byte[] initializationVector
    ) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initializationVector);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
        return cipher.doFinal(plainText.getBytes());
    }

    public static String performAESDecryption(
            byte[] cipherText,
            SecretKey secretKey,
            byte[] initializationVector
    ) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initializationVector);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
        byte[] result = cipher.doFinal(cipherText);
        return new String(result);
    }
}
