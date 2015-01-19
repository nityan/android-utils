package com.nityankhanna.androidutils;

import java.io.UnsupportedEncodingException;

/**
 * Created by Nityan Khanna on Jan 18 2014.
 */

/**
 * Contains utility methods for processing strings.
 */
public class StringUtils
{

	private StringUtils()
	{
	}

	/**
	 * Checks if a string is null or empty.
	 *
	 * @param string The string to check.
	 * @return Returns true if the string is null or empty.
	 */
	public static boolean isNullOrEmpty(String string)
	{
		return string == null || string.trim().isEmpty();
	}

	/**
	 * Converts a string to a boolean.
	 *
	 * @param string The string to convert.
	 * @return Returns the boolean value of the string.
	 */
	public static boolean toBoolean(String string)
	{
		return Boolean.valueOf(string);
	}

	/**
	 * Converts a string to a byte array.
	 *
	 * @param string The string to convert.
	 * @return Returns the byte array of the string.
	 */
	public static byte[] toByteArray(String string)
	{
		return string.getBytes();
	}

	/**
	 * Converts a string to a byte array.
	 *
	 * @param string   The string to convert.
	 * @param encoding The encoding.
	 * @return Returns the byte array of the string.
	 * @throws UnsupportedEncodingException Unsupported Encoding
	 */
	public static byte[] toByteArray(String string, String encoding) throws UnsupportedEncodingException
	{
		return string.getBytes(encoding);
	}

	/**
	 * Converts a string to a double.
	 *
	 * @param string The string to convert.
	 * @return Returns the double value of the string.
	 */
	public static double toDouble(String string)
	{
		return Double.parseDouble(string);
	}

	/**
	 * Converts a string to a double.
	 *
	 * @param string The string to convert.
	 * @return Returns the float value of the string.
	 */
	public static float toFloat(String string)
	{
		return Float.parseFloat(string);
	}

	/**
	 * Converts a string to an int.
	 *
	 * @param string The string to convert.
	 * @return Returns the int value of the string.
	 */
	public static int toInt(String string)
	{
		return Integer.parseInt(string);
	}

	/**
	 * Converts a string to a long.
	 *
	 * @param string The string to convert.
	 * @return Returns the long value of the string.
	 */
	public static long toLong(String string)
	{
		return Long.parseLong(string);
	}

	/**
	 * Converts a boolean to a string.
	 *
	 * @param value The value to convert.
	 * @return Returns the string value.
	 */
	public static String toString(boolean value)
	{
		return String.valueOf(value);
	}

	/**
	 * Converts a byte array to a string.
	 *
	 * @param value The value to convert.
	 * @return Returns the string value.
	 */
	public static String toString(byte[] value)
	{
		return new String(value);
	}

	/**
	 * Converts a double to a string.
	 *
	 * @param value The value to convert.
	 * @return Returns the string value.
	 */
	public static String toString(double value)
	{
		return String.valueOf(value);
	}

	/**
	 * Converts a float to a string.
	 *
	 * @param value The value to convert.
	 * @return Returns the string value.
	 */
	public static String toString(float value)
	{
		return String.valueOf(value);
	}

	/**
	 * Converts a int to a string.
	 *
	 * @param value The value to convert.
	 * @return Returns the string value.
	 */
	public static String toString(int value)
	{
		return String.valueOf(value);
	}

	/**
	 * Converts a long to a string.
	 *
	 * @param value The value to convert.
	 * @return Returns the string value.
	 */
	public static String toString(long value)
	{
		return String.valueOf(value);
	}


}
