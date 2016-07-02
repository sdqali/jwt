package in.sdqali.jwt;

import java.util.HashMap;
import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;

public class JwtPayload extends JwtPart {
    private static final String ISSUER = "iss";
    private static final String EXPIRY = "exp";

    public JwtPayload(String issuer, long expiry) {
        super(new HashMap(of(ISSUER, issuer, EXPIRY, String.valueOf(expiry))));
    }

    public void addData(Map<String, String> data) {
        super.merge(data);
    }
}
