package io.vehicle.rest.util;

import java.security.SecureRandom;

public class RandomString {

	static final String alphaNum = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom rnd = new SecureRandom();

	public static String getALphaNum(int len) {
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(alphaNum.charAt(rnd.nextInt(alphaNum.length())));
		return sb.toString();
	}

}
