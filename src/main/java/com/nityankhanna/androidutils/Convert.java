package com.nityankhanna.androidutils;

import com.nityankhanna.androidutils.security.EncodingManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by Nityan on 2015-01-23.
 */

/**
 * Contains methods to easily convert between data types.
 */
public class Convert
{
	private Convert()
	{
	}

	/**
	 * Converts a value to a boolean.
	 *
	 * @param value The value to convert.
	 * @return Returns a boolean representation of the value or null if the value is null.
	 */
	public static boolean toBoolean(Object value)
	{
		return value == null ? false : Boolean.valueOf(value.toString());
	}

	/**
	 * Serializes an object to a byte array.
	 *
	 * @param object The object to be serialized.
	 * @return Returns a byte array.
	 * @throws java.io.IOException If an error occurs during the conversion.
	 */
	public static byte[] toByteArray(Object object) throws IOException
	{
		if (!(object instanceof Serializable))
		{
			throw new NotSerializableException("Class" + object.getClass().getName() + " must implement " + Serializable.class.getCanonicalName() + " to be serialized");
		}

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

		objectOutputStream.writeObject(object);

		return byteArrayOutputStream.toByteArray();
	}

	/**
	 * Converts a byte array to a Base64 encoded string.
	 * @param value The value to convert.
	 * @return Returns a Base64 encoded string.
	 */
	public static String toBase64String(byte[] value)
	{
		return EncodingManager.getInstance().encodeToString(value);
	}

	/**
	 * Converts a value to a double.
	 *
	 * @param value The value to convert.
	 * @return Returns a double representation of the value or 0 if the value is null.
	 */
	public static double toDouble(Object value)
	{
		return value == null ? 0 : Double.parseDouble(value.toString());
	}

	/**
	 * Converts a value to a float.
	 *
	 * @param value The value to convert.
	 * @return Returns a float representation of the value or 0 if the value is null.
	 */
	public static float toFloat(Object value)
	{
		return value == null ? 0 : Float.parseFloat(value.toString());
	}

	/**
	 * Converts a value to a integer.
	 *
	 * @param value The value to convert.
	 * @return Returns an integer representation if the value or 0 if the value is null.
	 */
	public static int toInt(Object value)
	{
		return value == null ? 0 : Integer.parseInt(value.toString());
	}

	/**
	 * Converts a value to a string.
	 *
	 * @param value The value to convert.
	 * @return Returns a string representation of the value or null if the value is null.
	 */
	public static String toString(Object value)
	{
		return value == null ? null : value.toString();
	}
}