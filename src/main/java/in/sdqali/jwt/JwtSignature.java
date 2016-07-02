package in.sdqali.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import in.sdqali.jwt.crypto.HS256;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

public class JwtSignature {
    private JwtHeader header;
    private JwtPayload payload;

    public JwtSignature(JwtHeader header, JwtPayload payload) {
        this.header = header;
        this.payload = payload;
    }

    public String encode(String key) throws JsonProcessingException, InvalidKeyException, NoSuchAlgorithmException {
        String data = String.format("%s.%s", header.encode(), payload.encode());
        byte[] encodedBytes = new HS256().encode(key.getBytes(UTF_8), data.getBytes(UTF_8));
        return new String(Base64.getEncoder().encode(encodedBytes), UTF_8).replaceAll("\n", "");
    }
}
