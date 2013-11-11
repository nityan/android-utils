package com.nityankhanna.androidutils.security;

import android.util.Base64;

/**
 * Created by Nityan Khanna on 22/10/13.
 */

/**
 * A utility class for encoding data.
 */
public class EncodingManager {

	private static EncodingManager sharedInstance;

	private EncodingManager() {
	}

	public static EncodingManager getInstance() {

		synchronized (EncodingManager.class) {

			if (sharedInstance == null) {
				sharedInstance = new EncodingManager();
			}

			return sharedInstance;
		}
	}

	/**
	 * Encodes a byte array using Base64.
	 *
	 * @param data The string to be encoded.
	 *
	 * @return Returns the encoded data as a byte array.
	 */
	public byte[] encodeDataUsingBase64(byte[] data) {
		return Base64.encode(data, Base64.DEFAULT);
	}


	/**
	 * Decodes a byte array using Base64.
	 *
	 * @param data The data to be decoded.
	 *
	 * @return Returns the decoded data as a byte array.
	 */
	public byte[] decodeDataUsingBase64(byte[] data) {
		return Base64.decode(data, Base64.DEFAULT);
	}
}
