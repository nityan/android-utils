package com.nityankhanna.androidutils.http;

/**
 * Created by Nityan Khanna on Jan 20 2014.
 */

/**
 * Represents a collection of encoding types.
 */
public enum Encoding
{

	/**
	 * Represents UTF-8 encoding.
	 */
	UTF_8("UTF-8");

	private String value;

	private Encoding(String value)
	{
		this.value = value;
	}

	/**
	 * Gets the type of encoding.
	 *
	 * @return Returns the type of encoding.
	 */
	public String getValue()
	{
		return value;
	}
}
