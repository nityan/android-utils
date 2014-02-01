package com.nityankhanna.androidutils.security;

/**
 * Created by Nityan Khanna on Jan 14 2014.
 */
public enum Base64Mode {
	DEFAULT(0),
	NO_PADDING(1),
	NO_WRAP(2),
	CRLF(4),
	URL_SAFE(8),
	NO_CLOSE(16);

	private int value;

	private Base64Mode(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
