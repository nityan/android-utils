package com.nityankhanna.androidutils.http;

import java.util.List;

/**
 * Created by Nityan Khanna on 10/11/13.
 */
public interface HttpHeaderStore
{

	/**
	 * Adds an Http header to the request.
	 *
	 * @param header The Http header to add to the collection.
	 */
	void addHeader(HttpHeader header);

	/**
	 * Adds an Http header to the collection at the specified index.
	 *
	 * @param index  The index of where to add the header.
	 * @param header The Http header to add to the collection.
	 */
	void addHeader(int index, HttpHeader header);

	/**
	 * Returns a list of Http headers.
	 *
	 * @return Returns a list of Http headers.
	 */
	List<HttpHeader> getHeaders();

	/**
	 * Removes an Http Header.
	 *
	 * @param header The Http header to be removed.
	 */
	void removeHeader(HttpHeader header);

	/**
	 * Removes the header at the specified index.
	 *
	 * @param index The index of the header.
	 */
	void removeHeader(int index);

	/**
	 * Removes all of the Http headers.
	 */
	void removeAllHeaders();

	/**
	 * Removes a duplicate header.
	 *
	 * @param header The Http header to be removed.
	 */
	void removeDuplicateHeader(HttpHeader header);

}
