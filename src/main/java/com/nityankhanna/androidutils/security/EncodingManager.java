package com.nityankhanna.androidutils.security;

import org.apache.commons.codec.binary.Base64;

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
		return new Base64().encode(data);
	}

	/**
	 * Encodes a byte array using Base64.
	 *
	 * @param data The data to be encoded.
	 * @return Returns the encoded data as a String.
	 */
	public String encodeToString(byte[] data)
	{
		return new String(new Base64().encode(data));
	}

	/**
	 * Decodes a byte array using Base64.
	 *
	 * @param data The data to be decoded.
	 * @return Returns the decoded data as a byte array.
	 */
	public byte[] decodeToByteArray(byte[] data)
	{
		return Base64.decodeBase64(data);
	}

	/**
	 * Decodes a byte array to a string.
	 *
	 * @param data The data to be decoded.
	 * @return Returns the decoded string.
	 */
	public String decodeToString(byte[] data)
	{
		return new String(Base64.decodeBase64(data));
	}
}
