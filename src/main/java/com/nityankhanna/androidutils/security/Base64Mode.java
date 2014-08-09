package com.nityankhanna.androidutils.security;

/**
 * Created by Nityan Khanna on Jan 14 2014.
 */

/**
 * Represents a collection of different Base64 encoding modes.
 */
public enum Base64Mode {
	/**
	 * The default mode.
	 */
	DEFAULT(0),
	/**
	 * No padding.
	 */
	NO_PADDING(1),
	/**
	 * No wrap.
	 */
	NO_WRAP(2),
	/**
	 * CRLF.
	 */
	CRLF(4),
	/**
	 * Url safe.
	 */
	URL_SAFE(8),
	/**
	 * No close mode.
	 */
	NO_CLOSE(16);

	private int value;

	private Base64Mode(int value) {
		this.value = value;
	}

	/**
	 * Gets the Base64 encoding mode.
	 *
	 * @return Returns the Base64 mode.
	 */
	public int getValue() {
		return value;
	}
}
