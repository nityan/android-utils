package com.nityankhanna.androidutils.http;

import org.apache.http.HttpEntity;

/**
 * Created by Nityan Khanna on 01/07/13.
 */

/**
 * An interface for containing methods to handle an HTTP response.
 */
public interface OnHttpResponseListener {

	/**
	 * A delegate method called when a GET request is completed.
	 *
	 * @param response The response.
	 */
	void onGetCompleted(HttpEntity response);

	/**
	 * A delegate method called when a POST request is completed.
	 *
	 * @param response The response.
	 */
	void onPostCompleted(HttpEntity response);

	/**
	 * A delegate method called when a PUT request is completed.
	 *
	 * @param response The response.
	 */
	void onPutCompleted(HttpEntity response);

	/**
	 * A delegate method called when a DELETE request is completed.
	 *
	 * @param response The response.
	 */
	void onDeleteCompleted(HttpEntity response);

	/**
	 * A delegate method called when an error occurs due to the client.
	 *
	 * @param response The error response.
	 */
	void onClientError(ErrorResponse response);

	/**
	 * A delegate method called when an error occurs on the server.
	 *
	 * @param response The error response.
	 */
	void onServerError(ErrorResponse response);
}
