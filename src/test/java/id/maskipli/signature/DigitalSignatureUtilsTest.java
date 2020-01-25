package id.maskipli.signature;

import id.maskipli.asymmetric.AsymmetricEncryptionUtils;
import org.junit.jupiter.api.Test;

import javax.xml.bind.DatatypeConverter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyPair;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DigitalSignatureUtilsTest {

    @Test
    void digitalSignatureRoutine() throws Exception {
        URL uri = this.getClass().getClassLoader().getResource("test.txt");
        Path path = Paths.get(uri.toURI());
        byte[] input = Files.readAllBytes(path);

        KeyPair keyPair = AsymmetricEncryptionUtils.generateRSAKeyPair();
        byte[] signature = DigitalSignatureUtils.createDigitalSignature(input, keyPair.getPrivate());
        System.out.println(DatatypeConverter.printHexBinary(signature));
        assertTrue(DigitalSignatureUtils.verifyDigitalSignature(input, signature, keyPair.getPublic()));
    }

}