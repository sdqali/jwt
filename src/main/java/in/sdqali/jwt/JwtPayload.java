package in.sdqali.jwt;

import java.util.Map;

public class JwtPayload extends JwtPart {
    public JwtPayload(Map<String, String> values) {
        super(values);
    }
}
