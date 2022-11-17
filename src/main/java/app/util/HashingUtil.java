package app.util;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class HashingUtil {
	private static HashFunction SHA256 = Hashing.sha256();
	private HashingUtil() {
	}
	
	public static String toSHA256(String rawString) {
		return SHA256.hashString(rawString, StandardCharsets.UTF_8).toString();
	}
}
