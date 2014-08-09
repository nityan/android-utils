package com.nityankhanna.androidutils.http;

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
	GET("GET"),

	/**
	 * Represents an HTTP POST request.
	 */
	POST("POST"),

	/**
	 * Represents an HTTP PUT request
	 */
	PUT("PUT"),

	/**
	 * Represents an HTTP DELETE request.
	 */
	DELETE("DELETE");

	private String value;

	private RequestType(String value) {
		this.value = value;
	}

	/**
	 * Returns the value of the RequestType.
	 *
	 * @return Returns the value of the RequestType.
	 */
	public String getValue() {
		return value;
	}
}
