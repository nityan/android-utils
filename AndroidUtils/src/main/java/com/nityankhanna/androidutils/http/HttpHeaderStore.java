package com.nityankhanna.androidutils.http;

import java.util.List;

/**
 * Created by Nityan Khanna on 10/11/13.
 */
public interface HttpHeaderStore {

	/**
	 * Adds an HTTP header to the request.
	 *
	 * @param header The HTTP header to add to the collection.
	 */
	void addHeader(HttpHeader header);

	/**
	 * Adds an HTTP header to the request.
	 *
	 * @param index  The index of where to add the header.
	 * @param header The HTTP header to add to the collection.
	 */
	void addHeader(int index, HttpHeader header);

	/**
	 * Adds an array of HttpHeaders to the request.
	 *
	 * @param headers The array of headers to add to the request.
	 */
	void addHeaders(HttpHeader[] headers);

	/**
	 * Returns a list of HTTP headers.
	 *
	 * @return Returns a list of HTTP headers.
	 */
	List<HttpHeader> getHeaders();

	/**
	 * Removes an HTTP header.
	 *
	 * @param index The index of the header to remove.
	 */
	void removeHeader(int index);

	/**
	 * Removes an HTTP Header.
	 *
	 * @param header The HTTP header object to be removed.
	 */
	void removeHeader(HttpHeader header);

	/**
	 * Removes all of the current HTTP headers.
	 */
	void removeAllHeaders();


}
