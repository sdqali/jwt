package in.sdqali.jwt.crypto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collection;

import static javax.xml.bind.DatatypeConverter.parseHexBinary;
import static org.junit.Assert.assertArrayEquals;

// Test HMAC SHA-256 generation using test vectors from RFC-4231
@RunWith(Parameterized.class)
public class HS256Test {
    @Parameter
    public String testCase;

    @Parameter(1)
    public String key;

    @Parameter(2)
    public String data;

    @Parameter(3)
    public String expected;

    @Test
    public void shouldGenerateCorrectSha() throws InvalidKeyException, NoSuchAlgorithmException {
        byte[] actual = new HS256().encode(parseHexBinary(key), parseHexBinary(data));
        assertArrayEquals(testCase, parseHexBinary(expected), actual);
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[] [] {
                {"Simple",
                        "0b0b0b0b0b0b0b0b0b0b0b0b0b0b0b0b0b0b0b0b",
                        "4869205468657265",
                        "b0344c61d8db38535ca8afceaf0bf12b881dc200c9833da726e9376c2e32cff7"},
                {"Test with a key shorter than the length of the HMAC output.",
                        "4a656665",
                        "7768617420646f2079612077616e7420666f72206e6f7468696e673f",
                        "5bdcc146bf60754e6a042426089575c75a003f089d2739839dec58b964ec3843"},
                {"Test with a combined length of key and data that is larger than 64 bytes",
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                        "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd",
                        "773ea91e36800e46854db8ebd09181a72959098b3ef8c122d9635514ced565fe"},
                {"Test with a combined length of key and data that is larger than 64 bytes",
                        "0102030405060708090a0b0c0d0e0f10111213141516171819",
                        "cdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcd",
                        "82558a389a443c0ea4cc819899f2083a85f0faa3e578f8077a2e3ff46729665b"},
                {"Test with a key larger than 128 bytes",
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                        "54657374205573696e67204c6172676572205468616e20426c6f636b2d53697a65204b6579202d2048617368204b6579204669727374",
                        "60e431591ee0b67f0d8a26aacbf5b77f8e0bc6213728c5140546040f0ee37f54"},
                {"Test with a key and data that is larger than 128 bytes",
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                        "5468697320697320612074657374207573696e672061206c6172676572207468616e20626c6f636b2d73697a65206b657920616e642061206c6172676572207468616e20626c6f636b2d73697a6520646174612e20546865206b6579206e6565647320746f20626520686173686564206265666f7265206265696e6720757365642062792074686520484d414320616c676f726974686d2e",
                        "9b09ffa71b942fcb27635fbcd5b0e944bfdc63644f0713938a7f51535c3a35e2"}
        });
    }
}
