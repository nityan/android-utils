package com.nityankhanna.androidutils.http;

/**
 * Created by Nityan Khanna on Jan 18 2014.
 */
public interface HttpMessage {

	/**
	 * Checks if the Http message contains cookies.
	 *
	 * @return Returns true if the Http message contains cookies.
	 */
	boolean containsCookies();

	/**
	 * Checks if the Http message contains headers.
	 *
	 * @return Returns true if the Http message contains headers.
	 */
	boolean containsHeaders();

	/**
	 * Checks if the Http message contains parameters.
	 *
	 * @return Returns true if the Http message contains parameters.
	 */
	boolean containsParameters();

	/**
	 * Gets the content type of the message.
	 *
	 * @return Returns the content type.
	 */
	ContentType getContentType();

	/**
	 * Gets the encoding of the message.
	 *
	 * @return Returns the encoding.
	 */
	Encoding getEncoding();
}
