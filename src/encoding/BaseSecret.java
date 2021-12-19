package encoding;

import java.util.HashSet;

public class BaseSecret {
	private String secret;
	
/*
 *  returns 0 if input is valid
 *  returns negative number if input is invalid.
 *  There are 2 case:
 *  1. The input length <= 1, return code is -200
 *  2. the input includes not unique symbols
  */
public int setSecret(String secret) {
	int validCode = secretValidationCode(secret);
	if(validCode==0) {
		this.secret = secret;
	}
	return validCode;		
}
public BaseSecret() {
	secret = "0123456789";
}
public static String toBinaryString(int num) {
	return toBaseString(num, "01");
}
public static String toDecimalString(int num) {
	return toBaseString(num, "0123456789");
}
public static String toBaseString(int num, String secret) {
	StringBuilder builder = new StringBuilder();
	int base = secret.length();
	do {
		int rem = num % base;
		builder.insert(0, secret.charAt(rem));
		num = num / base;
		
	}while(num != 0);
	return builder.toString();
}
// The first edition
public static int parseIntBase2(String baseStr, String secret) {
	int base = secret.length();
	int res = 0;
	int length = baseStr.length();
	for (int i = 0; i < length; i++) {
		int value = secret.indexOf(baseStr.charAt(i));
		res = res * base + value;
	}
	return res;
}
// The second edition
public static int parseIntBase(String baseStr, String secret) {
	int base = secret.length();
	int res = 0;
	int length = baseStr.length();
	for (int i = 0; i < length; i++) {
		int value = secret.indexOf(baseStr.charAt(i));
		res = res + (int)Math.pow(base, length-i-1) * value;
	}
	return res;
}

public static int parseIntBinary(String binaryStr) {
	return parseIntBase(binaryStr, "01");
}
public static int parseIntDecimal(String decString) {
	return parseIntBase(decString, "0123456789");
}

private int secretValidationCode(String secret) {
	if(secret.length() <= 1) {
		// secret is too short
		return -200;
	}
	HashSet<Character> set = new HashSet<Character>();
	for(Character ch : secret.toCharArray()) {
		set.add(ch);
	}
	// Checking uniqueness symbols
	return set.size() - secret.length();
}

public String toSecretString(int num) {
	//TODO
	if(secret == null) {
		return "setSecret() isn't called";
	}
	String baseString = toBaseString(num, secret);
	char[] charArray = baseString.toCharArray();
	StringBuilder builder = new StringBuilder();
	for(char ch : charArray) {
		builder.append(ch);
	}
	
	return builder.toString();
	// Done
}
public boolean matches(String code, String decString) {
	//TODO
	if(secret == null) {
		return false;
	}
	int number = parseIntBase(decString, "0123456789");
	String strNumber = toSecretString(number);
	return code.equals(strNumber);
	//Done
}

}
