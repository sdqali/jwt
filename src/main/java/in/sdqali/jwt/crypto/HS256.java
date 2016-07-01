package in.sdqali.jwt.crypto;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

// Encodes data with a key using HMAC-SHA-256
// Does not truncate to 128 bits
public class HS256 {
    public byte[] encode(byte[] key, byte[] data) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac sha256 = Mac.getInstance("HmacSHA256");
        SecretKeySpec keySpec = new SecretKeySpec(key, "HmacSHA256");
        sha256.init(keySpec);
        return sha256.doFinal(data);
    }
}
