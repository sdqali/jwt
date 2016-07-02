package in.sdqali.jwt;

import org.junit.Test;
import static com.google.common.collect.ImmutableMap.of;
import static org.junit.Assert.assertEquals;

public class JwtTest {
    @Test
    public void shouldGenerateToken() throws JwtException {
        Jwt jwt = new Jwt(Algorithm.HS256, of("name", "John Doe", "admin", "true"), 1467452801, "testIssuer");
        assertEquals("Generated JWT", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ0ZXN0SXNzdWVyIiwibmFtZ" +
                "SI6IkpvaG4gRG9lIiwiYWRtaW4iOiJ0cnVlIiwiZXhwIjoiMTQ2NzQ1MjgwMSJ9.lYsWoZRzCs7i/NR+oGQ8wQaXqD/jd" +
                "3K7ApKWjYzINdc=",
                jwt.encode("secret"));
    }
}
