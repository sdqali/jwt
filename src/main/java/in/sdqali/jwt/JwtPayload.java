package in.sdqali.jwt;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;

import static com.google.common.collect.ImmutableMap.of;

public class JwtPayload extends JwtPart {
    private static final String ISSUER = "iss";
    private static final String EXPIRY = "exp";

    public JwtPayload(String issuer, long expiry) {
        super(new HashMap(of(ISSUER, issuer, EXPIRY, String.valueOf(expiry))));
    }

    public void addData(ImmutableMap<String, String> data) {
        super.merge(data);
    }
}
