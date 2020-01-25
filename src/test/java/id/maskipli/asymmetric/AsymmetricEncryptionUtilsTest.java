package id.maskipli.asymmetric;

import org.junit.jupiter.api.Test;

import javax.xml.bind.DatatypeConverter;
import java.security.KeyPair;

import static org.junit.jupiter.api.Assertions.*;

class AsymmetricEncryptionUtilsTest {

    @Test
    void generateKeyPair() throws Exception {
        KeyPair keyPair = AsymmetricEncryptionUtils.generateRSAKeyPair();
        assertNotNull(keyPair);
        System.out.println("private key: " + DatatypeConverter.printHexBinary(keyPair.getPrivate().getEncoded()));
        System.out.println("public key: " + DatatypeConverter.printHexBinary(keyPair.getPublic().getEncoded()));
    }

    @Test
    void testRSACryptoRoutine() throws  Exception {
        String plainText = "Sample Text";
        KeyPair keyPair = AsymmetricEncryptionUtils.generateRSAKeyPair();
        byte[] cipherText = AsymmetricEncryptionUtils.performRSAEncryption(plainText, keyPair.getPrivate());
        assertNotNull(cipherText);
        System.out.println(DatatypeConverter.printHexBinary(cipherText));
        String decryptText = AsymmetricEncryptionUtils.performRSADecryption(cipherText, keyPair.getPublic());
        assertEquals(plainText, decryptText);
    }

}