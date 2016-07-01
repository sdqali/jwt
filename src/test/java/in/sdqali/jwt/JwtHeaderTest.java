package in.sdqali.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class JwtHeaderTest {
    @Parameter()
    public String testCase;

    @Parameter(1)
    public Map<String, String> values;

    @Parameter(2)
    public String expected;

    @Test
    public void shouldEncodeCorrectly() throws JsonProcessingException {
        String actual = new JwtHeader(values).encode();
        assertEquals(testCase, expected, actual);
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[] []{
                {"Test with only typ and alg HS256",
                        of("typ", "JWT", "alg", "HS256"),
                        "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9"},
                {"Test with only typ and alg RS256",
                        of("typ", "JWT", "alg", "RS256"),
                        "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9"},
                {"Test with typ, alg and content type",
                        of("typ", "JWT", "alg", "HS256", "cty", "JWT"),
                        "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiIsImN0eSI6IkpXVCJ9"}
        });
    }
}
