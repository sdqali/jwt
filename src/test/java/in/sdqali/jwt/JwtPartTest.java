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
public class JwtPartTest {
    @Parameter()
    public String testCase;

    @Parameter(1)
    public Map<String, String> values;

    @Parameter(2)
    public String expected;

    @Test
    public void shouldEncodeCorrectly() throws JsonProcessingException {
        String actual = new JwtPart(values).encode();
        assertEquals(testCase, expected, actual);
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[] []{
                {"Header with only typ and alg HS256",
                        of("typ", "JWT", "alg", "HS256"),
                        "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9"},
                {"Header with only typ and alg RS256",
                        of("typ", "JWT", "alg", "RS256"),
                        "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9"},
                {"Header with typ, alg and content type",
                        of("typ", "JWT", "alg", "HS256", "cty", "JWT"),
                        "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiIsImN0eSI6IkpXVCJ9"},
                {"Payload with UUID",
                        of("iss", "test", "sessionId", "3bce58a1-90f5-46b8-87c7-196eb9fa9718", "exp", "1467385989"),
                        "eyJpc3MiOiJ0ZXN0Iiwic2Vzc2lvbklkIjoiM2JjZTU4YTEtOTBmNS00NmI4LTg3YzctMTk2ZWI5ZmE5NzE4IiwiZX" +
                                "hwIjoiMTQ2NzM4NTk4OSJ9"},
                {"Payload with line breaks",
                        of("iss", "test", "text", paragraph(), "exp", "1467385989"),
                        "eyJpc3MiOiJ0ZXN0IiwidGV4dCI6Ikl0IGlzIGEgbG9uZyBlc3RhYmxpc2hlZCBmYWN0IHRoYXQgYSByZWFkZXIgd2lsbCBiZSBkaXN0cmFjdGVkXG4iLCJleHAiOiIxNDY3Mzg1OTg5In0="
                }
        });
    }

    private static String paragraph() {
        return "It is a long established fact that a reader will be distracted\n";
    }
}
