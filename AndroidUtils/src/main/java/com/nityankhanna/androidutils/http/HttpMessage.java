package com.nityankhanna.androidutils.http;

/**
 * Created by Nityan Khanna on Jan 18 2014.
 */
public interface HttpMessage {

	/**
	 * Checks if the HttpRequestMessage contains cookies.
	 *
	 * @return Returns true if the HttpRequestMessage contains cookies.
	 */
	boolean containsCookies();

	/**
	 * Checks if the HttpRequestMessage contains headers.
	 *
	 * @return Returns true if the HttpRequestMessage contains headers.
	 */
	boolean containsHeaders();

	/**
	 * Checks if the HttpRequestMessage contains parameters.
	 *
	 * @return Returns true if the HttpRequestMessage contains parameters.
	 */
	boolean containsParams();

	/**
	 * Checks if the URL is valid syntax.
	 *
	 * @return Returns true if the URL is valid syntax.
	 */
	boolean isUrlWellFormed();

	/**
	 * Checks if the URL is reachable.
	 *
	 * @return Returns true if the URL is reachable.
	 */
	boolean isUrlReachable();

}
