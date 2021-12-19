package encoding;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BaseSecretTest {
	BaseSecret bs = new BaseSecret();
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testToBinaryString() {
		assertEquals("100", BaseSecret.toBinaryString(4));
	}

	@Test
	void testToDecimalString() {
		assertEquals("1234", BaseSecret.toDecimalString(1234));
	}

	@Test
	void testParseIntBinary() {
		assertEquals(4, BaseSecret.parseIntBinary("100"));
	}

	@Test
	void testParseIntDecimal() {
		assertEquals(1234, BaseSecret.parseIntDecimal("1234"));
	}

	@Test
	void testToSecretString() {
		String secret = ".-";
		int validCode = bs.setSecret(secret); 
		if(validCode<0) {
			fail("input isn't valid. secret="+secret+" code="+validCode);
		}
		assertEquals("-..", bs.toSecretString(4));
		bs.setSecret("0123456789abcdef");
		assertEquals("11", bs.toSecretString(17));
		
	}

	@Test
	void testMatches() {
		String secret = "()";
		int validCode = bs.setSecret(secret); 
		if(validCode<0) {
			fail("input isn't valid. secret="+secret+" code="+validCode);
		}
		assertTrue(bs.matches(")((", "4"));
		bs.setSecret("0123456789abcdef");
		assertTrue(bs.matches("13", "19"));
	}

}
