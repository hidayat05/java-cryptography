package id.maskipli.keystore;

import id.maskipli.symmetric.SymmetricEncryptionUtils;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
import java.security.KeyStore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class KeyStoreUtilsTest {

    @Test
    void createPrivateKeyJavaKeyStore() throws Exception {
        String password = "password";
        String keyAlias = "foo";
        String keyStorePassword = "keyStorePassword";

        SecretKey secretKey = SymmetricEncryptionUtils.createAESKey();
        String secretKeyHex = DatatypeConverter.printHexBinary(secretKey.getEncoded());
        KeyStore keyStore = KeyStoreUtils.createPrivateKeyJavaKeyStore(
                password,
                keyAlias,
                secretKey,
                keyStorePassword
        );
        assertNotNull(keyStore);
        keyStore.load(null, password.toCharArray());
        KeyStore.ProtectionParameter entryPassword = new KeyStore.PasswordProtection(keyStorePassword.toCharArray());
        KeyStore.SecretKeyEntry resultEntry = (KeyStore.SecretKeyEntry) keyStore.getEntry(keyAlias, entryPassword);
        SecretKey result = resultEntry.getSecretKey();
        String resultKeyHex = DatatypeConverter.printHexBinary(result.getEncoded());
        assertEquals(secretKeyHex, resultKeyHex);
    }
}