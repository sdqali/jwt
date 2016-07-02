package in.sdqali.jwt;

import java.util.Map;

public class Jwt {
    private JwtHeader header;
    private JwtPayload payload;
    private JwtSignature signature;

    public Jwt(Algorithm algorithm, Map<String, String> data, long expiry, String issuer) {
        payload = new JwtPayload(issuer, expiry);
        payload.addData(data);
        header = new JwtHeader(algorithm);
        signature = new JwtSignature(header, payload);
    }

    public String encode(String key) throws JwtException {
        try {
            return String.format("%s.%s.%s", header.encode(), payload.encode(), signature.encode(key));
        } catch (Exception e) {
            throw new JwtException(e);
        }
    }
}
