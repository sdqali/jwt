package in.sdqali.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static com.google.common.collect.ImmutableMap.of;


public class JwtPayloadTest {
    @Test
    public void shouldCreateWithIssuerAndExpiry() throws JsonProcessingException {
        String actual = new JwtPayload("testIssuer", 1467385989).encode();
        assertEquals("eyJpc3MiOiJ0ZXN0SXNzdWVyIiwiZXhwIjoiMTQ2NzM4NTk4OSJ9", actual);
    }

    @Test
    public void shouldCreateWithData() throws JsonProcessingException {
        JwtPayload payload = new JwtPayload("testIssuer", 1467385989);
        payload.addData(of("text", "some text", "sessionId", "test"));
        String actual = payload.encode();
        String expected = "eyJpc3MiOiJ0ZXN0SXNzdWVyIiwidGV4dCI6InNvbWUgdGV4dCIsInNl" +
                "c3Npb25JZCI6InRlc3QiLCJleHAiOiIxNDY3Mzg1OTg5In0=";
        assertEquals
                (expected, actual);
    }
}