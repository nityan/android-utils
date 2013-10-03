package com.nityankhanna.androidutils.http;

import org.json.JSONArray;

/**
 * Created by Nityan Khanna on 01/07/13.
 */

/**
 * An interface for containing methods to handle an HTTP response.
 */
public interface OnHttpResponseListener {

	/**
	 * A delegate method to handle a JSONArray response.
	 *
	 * @param response The JSONArray response to be handled.
	 */
	void onCompleted(JSONArray response);

	/**
	 * A delegate method to handle an error response.
	 */
	void onCompletedWithError();
}
