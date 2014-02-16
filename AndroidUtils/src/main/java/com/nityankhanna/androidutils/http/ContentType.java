package com.nityankhanna.androidutils.http;

/**
 * Created by Nityan Khanna on Jan 19 2014.
 */
public enum ContentType {

	/**
	 * Represents a JSON content type.
	 */
	JSON(0),

	/**
	 * Represents an XML content type.
	 */
	XML(1);

	private int value;

	private ContentType(int value) {
		this.value = value;
	}

	/**
	 * Gets the value of the content type.
	 *
	 * @return Returns the content type.
	 */
	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "ContentType{" + "value=" + value + '}';
	}
}
