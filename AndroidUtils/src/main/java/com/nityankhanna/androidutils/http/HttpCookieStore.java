package com.nityankhanna.androidutils.http;

import java.util.Date;
import java.util.List;

/**
 * Created by Nityan Khanna on Jan 18 2014.
 */
public interface HttpCookieStore {

	/**
	 * Adds a cookie to the collection.
	 *
	 * @param cookie The cookie.
	 */
	void addCookie(HttpCookie cookie);

	/**
	 * Adds a cookie to the collection at the specified index.
	 *
	 * @param index  The index.
	 * @param cookie The cookie.
	 */
	void addCookie(int index, HttpCookie cookie);

	/**
	 * Gets a list of cookies.
	 *
	 * @return Returns a list of cookies.
	 */
	List<HttpCookie> getCookies();

	/**
	 * Clears the expired cookies from the collection.
	 *
	 * @param date The date.
	 */
	void clearExpired(Date date);

	/**
	 * Removes a cookie from the collection.
	 *
	 * @param cookie The cookie.
	 */
	void removeCookie(HttpCookie cookie);

	/**
	 * Removes a cookie from the collection at the specified index.
	 *
	 * @param index The index.
	 */
	void removeCookie(int index);

	/**
	 * Removes all cookies from the collection.
	 */
	void removeAllCookies();

	/**
	 * Removes a duplicate cookie.
	 *
	 * @param cookie The cookie.
	 */
	void removeDuplicateCookie(HttpCookie cookie);
}
