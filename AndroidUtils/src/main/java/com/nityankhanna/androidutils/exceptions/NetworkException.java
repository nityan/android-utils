package com.nityankhanna.androidutils.exceptions;

/**
 * Created by Nityan Khanna on 09/08/13.
 */

/**
 * An exception which is thrown when an exception occurs with the network.
 */
public class NetworkException extends Exception {

	/**
	 * The message for the exception.
	 */
	private String message;

	/**
	 * Initializes a new NetworkException with a null message.
	 */
	public NetworkException() {
		super();
	}

	/**
	 * Initializes a new NetworkException with a specified message.
	 *
	 * @param message The message for the exception.
	 */
	public NetworkException(String message) {
		super(message);
		this.message = message;
	}
}
