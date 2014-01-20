package com.nityankhanna.androidutils.http;

/**
 * Created by Nityan Khanna on Jan 19 2014.
 */
public enum ContentType {
	JSON(0),
	XML(1);

	private int value;

	private ContentType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
