package com.util;

import java.util.Random;

public class TestDataGenerator {

	public static String generateEmail() {
		return "Test" + System.currentTimeMillis() + "@gmail.com";
	}
	
	
	public static String generateOtp() {
		Random rand = new Random();
		int otp = 1000 + rand.nextInt(9000);
		return String.valueOf(otp);
	}
}
