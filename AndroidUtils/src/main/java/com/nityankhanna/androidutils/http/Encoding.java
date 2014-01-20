package com.nityankhanna.androidutils.http;

/**
 * Created by Nityan Khanna on Jan 20 2014.
 */
public enum Encoding {
	UTF_8("UTF-8");

	private String value;

	private Encoding(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
