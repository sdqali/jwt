package in.sdqali.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JwtHeaderTest {
    @Test
    public void shouldCreateWithCorrectAlgorithm() throws JsonProcessingException {
        String actual = new JwtHeader(Algorithm.HS256).encode();
        assertEquals("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9", actual);
    }
}