package in.sdqali.jwt;

import static com.google.common.collect.ImmutableMap.of;

public class JwtHeader extends JwtPart {
    private static final String TYPE = "typ";
    private static final String ALG = "alg";

    public JwtHeader(Algorithm alg) {
        super(of(TYPE, "JWT", ALG, alg.toString()));
    }
}
