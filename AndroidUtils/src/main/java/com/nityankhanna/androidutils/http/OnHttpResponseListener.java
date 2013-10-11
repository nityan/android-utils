package com.nityankhanna.androidutils.http;

import com.nityankhanna.androidutils.models.ErrorResponse;

import org.apache.http.HttpEntity;

/**
 * Created by Nityan Khanna on 01/07/13.
 */

/**
 * An interface for containing methods to handle an HTTP response.
 */
public interface OnHttpResponseListener {

	/**
	 * A delegate method called when a get request is completed.
	 *
	 * @param response The response.
	 */
	void onGetCompleted(HttpEntity response);

	/**
	 * A delegate method called when a post request is completed.
	 *
	 * @param response The response.
	 */
	void onPostCompleted(HttpEntity response);

	/**
	 * A delegate method called when a put request is completed.
	 *
	 * @param response The response.
	 */
	void onPutCompleted(HttpEntity response);

	/**
	 * A delegate method called when a delete request is completed.
	 *
	 * @param response The response.
	 */
	void onDeleteCompleted(HttpEntity response);

	/**
	 * A delegate method called when an error occurs due to the client.
	 */
	void onClientError();

	/**
	 * A delegate method called when an error occurs on the server.
	 *
	 * @param response The error response.
	 */
	void onServerError(ErrorResponse response);
}
