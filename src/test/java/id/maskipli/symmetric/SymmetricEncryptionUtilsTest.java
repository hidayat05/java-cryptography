package id.maskipli.symmetric;

import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SymmetricEncryptionUtilsTest {


    @Test
    void createAESKey() throws Exception {
        SecretKey secretKey = SymmetricEncryptionUtils.createAESKey();
        assertNotNull(secretKey);
        System.out.println(DatatypeConverter.printHexBinary(secretKey.getEncoded()));
    }

    @Test
    void testAESCryptoRoutine() throws Exception {
        String plainText = "Sample text";
        SecretKey secretKey = SymmetricEncryptionUtils.createAESKey();
        byte[] initializationVector = SymmetricEncryptionUtils.createInitializationVector();
        byte[] cipherText = SymmetricEncryptionUtils.performAEXEncryption(plainText, secretKey, initializationVector);
        assertNotNull(cipherText);
        System.out.println(DatatypeConverter.printHexBinary(cipherText));
        String decryptionText = SymmetricEncryptionUtils.performAESDecryption(cipherText, secretKey, initializationVector);
        assertEquals(plainText, decryptionText);
    }

}