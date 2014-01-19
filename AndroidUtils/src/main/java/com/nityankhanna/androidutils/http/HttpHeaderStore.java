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
	 * Adds an HTTP header to the collection at the specified index.
	 *
	 * @param index  The index of where to add the header.
	 * @param header The HTTP header to add to the collection.
	 */
	void addHeader(int index, HttpHeader header);

	/**
	 * Returns a list of HTTP headers.
	 *
	 * @return Returns a list of HTTP headers.
	 */
	List<HttpHeader> getHeaders();

	/**
	 * Removes an HTTP Header.
	 *
	 * @param header The HTTP header object to be removed.
	 */
	void removeHeader(HttpHeader header);

	/**
	 * Removes the header at the specified index.
	 *
	 * @param index The index of the header to be removed.
	 */
	void removeHeader(int index);

	/**
	 * Removes all of the current HTTP headers.
	 */
	void removeAllHeaders();


}
