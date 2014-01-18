package com.nityankhanna.androidutils;

/**
 * Created by Nityan Khanna on Jan 18 2014.
 */
public class StringUtils {

	private StringUtils() {
	}

	public static boolean isNullOrEmpty(String string) {
		return string == null || string.trim().isEmpty();
	}

	public static boolean toBoolean(String string) {
		return Boolean.valueOf(string);
	}

	public static double toDouble(String string) {
		return Double.parseDouble(string);
	}

	public static float toFloat(String string) {
		return Float.parseFloat(string);
	}

	public static int toInt(String string) {
		return Integer.parseInt(string);
	}

	public static int wordCount(String string) {
		String trimmed = string.trim();
		return trimmed.isEmpty() ? 0 : trimmed.split("\\s+").length;
	}


}
