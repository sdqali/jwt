package in.sdqali.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Base64;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

public class JwtPart {
    private final Map<String, String> values;

    public JwtPart(Map<String, String> values) {
        this.values = values;
    }

    public String encode() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] valueAsBytes = objectMapper.writeValueAsBytes(values);
        byte[] encodedValue = Base64.getEncoder().encode(valueAsBytes);
        return new String(encodedValue, UTF_8);
    }
}
