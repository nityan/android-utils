package com.nityankhanna.androidutils.http.core;

import java.util.List;

import com.nityankhanna.androidutils.http.HttpCookie;
import com.nityankhanna.androidutils.http.HttpHeader;
import com.nityankhanna.androidutils.http.HttpParameter;

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
	boolean containsParameters();

	/**
	 * Gets the content type of the message.
	 *
	 * @return Returns an HttpHeader with the content type.
	 */
	HttpHeader getContentType();

	/**
	 * Gets a list of HttpCookies.
	 *
	 * @return Returns a list of HttpCookies.
	 */
	List<HttpCookie> getCookies();

	/**
	 * Gets a list of HttpHeaders.
	 *
	 * @return Returns a list of HttpHeaders.
	 */
	List<HttpHeader> getHeaders();

	/**
	 * Gets a list of HttpParameters.
	 *
	 * @return Returns a list of HttpParameters.
	 */
	List<HttpParameter> getParameters();

}
