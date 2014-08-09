package com.nityankhanna.androidutils.security;

import android.util.Base64;

/**
 * Created by Nityan Khanna on 22/10/13.
 */

/**
 * A utility class for encoding data.
 */
public class EncodingManager
{

	private static EncodingManager sharedInstance;

	private EncodingManager()
	{
	}

	/**
	 * Returns an instance of the EncodingManager class.
	 *
	 * @return Returns an instance of the EncodingManager class.
	 */
	public static EncodingManager getInstance()
	{

		synchronized (EncodingManager.class)
		{

			if (sharedInstance == null)
			{
				sharedInstance = new EncodingManager();
			}

			return sharedInstance;
		}
	}

	/**
	 * Encodes a byte array using Base64.
	 *
	 * @param data The data to be encoded.
	 * @return Returns the encoded data as a byte array.
	 */
	public byte[] encodeToByteArray(byte[] data)
	{
		return Base64.encode(data, Base64.DEFAULT);
	}

	/**
	 * Encodes a byte array using Base64.
	 *
	 * @param data The data to be encoded.
	 * @param mode The Base64 encoding mode.
	 * @return Returns the encoded data as a byte array.
	 */
	public byte[] encodeToByteArray(byte[] data, Base64Mode mode)
	{
		return Base64.encode(data, mode.getValue());
	}

	/**
	 * Encodes a byte array using Base64.
	 *
	 * @param data The data to be encoded.
	 * @return Returns the encoded data as a String.
	 */
	public String encodeToString(byte[] data)
	{
		return Base64.encodeToString(data, Base64.DEFAULT);
	}

	/**
	 * Encodes a byte array using Base64.
	 *
	 * @param data The data to be encoded.
	 * @param mode The Base64 encoding mode.
	 * @return Returns the encoded data as a String.
	 */
	public String encodeToString(byte[] data, Base64Mode mode)
	{
		return Base64.encodeToString(data, mode.getValue());
	}

	/**
	 * Decodes a byte array using Base64.
	 *
	 * @param data The data to be decoded.
	 * @return Returns the decoded data as a byte array.
	 */
	public byte[] decodeToByteArray(byte[] data)
	{
		return Base64.decode(data, Base64.DEFAULT);
	}

	/**
	 * Decodes a byte array using Base64.
	 *
	 * @param data The data to be encoded.
	 * @param mode The Base64 encoding mode.
	 * @return Returns the decoded data as a byte array.
	 */
	public byte[] decodeToByteArray(byte[] data, Base64Mode mode)
	{
		return Base64.decode(data, mode.getValue());
	}

	/**
	 * Decodes a byte array using Base64.
	 *
	 * @param data The data to be encoded.
	 * @return Returns the decoded data as a String.
	 */
	public String decodeToString(byte[] data)
	{
		return new String(Base64.decode(data, Base64.DEFAULT));
	}

	/**
	 * Decodes a byte array using Base64.
	 *
	 * @param data The data to be encoded.
	 * @param mode The Base64 encoding mode.
	 * @return Returns the decoded data as a String.
	 */
	public String decodeToString(byte[] data, Base64Mode mode)
	{
		return new String(Base64.decode(data, mode.getValue()));
	}
}
