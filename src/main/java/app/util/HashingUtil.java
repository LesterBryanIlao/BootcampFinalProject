package app.util;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;

public class HashingUtil {
	public String toSHA256(String rawString) {
		return Hashing.sha256().hashString(rawString, StandardCharsets.UTF_8).toString();
	}
}
