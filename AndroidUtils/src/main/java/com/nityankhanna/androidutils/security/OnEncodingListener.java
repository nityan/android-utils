package com.nityankhanna.androidutils.security;

/**
 * Created by Nityan Khanna on 02/09/13..
 */

/**
 * An interface containing methods to handle encoding and decoding.
 */
public interface OnEncodingListener {

	/**
	 * Called when encoding data is completed.
	 *
	 * @param data The encoded data.
	 */
	void onEncodingCompleted(final byte[] data);

	/**
	 * Called when decoding data is completed.
	 *
	 * @param data The decoded data.
	 */
	void onDecodingCompleted(final byte[] data);
}
