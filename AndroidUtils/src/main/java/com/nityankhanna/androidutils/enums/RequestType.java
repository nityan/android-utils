package com.nityankhanna.androidutils.enums;

/**
 * Created by Nityan Khanna on 01/07/13.
 */

/**
 * An enum for different HTTP request types.
 */
public enum RequestType {
	/**
	 * Represents an HTTP GET request.
	 */
	GET,

	/**
	 * Represents an HTTP POST request.
	 */
	POST,

	/**
	 * Represents an HTTP PUT request
	 */
	PUT,

	/**
	 * Represents an HTTP DELETE request.
	 */
	DELETE;

	private RequestType() {
	}

	/**
	 * Returns the int value of the RequestType.
	 *
	 * @return Returns the string value of the RequestType.
	 */
	@Override
	public String toString() {

		String value;

		switch (this) {

			case GET:
				value = "GET";
				break;

			case POST:
				value = "POST";
				break;

			case PUT:
				value = "PUT";
				break;

			case DELETE:
				value = "DELETE";
				break;

			default:
				value = null;
				break;
		}

		return value;
	}
}
