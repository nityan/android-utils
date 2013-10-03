package com.nityankhanna.androidutils.exceptions;

/**
 * Created by Nityan Khanna on 20/09/13.
 */

/**
 * An exception which is thrown when an operation is being attempted which requires authorization.
 */
public class AuthenticationException extends Exception {

	/**
	 * The message for the exception.
	 */
	private String message;

	/**
	 * Initializes a new AuthenticationException with a null message.
	 */
	public AuthenticationException() {
		super();
	}

	/**
	 * Initializes a new AuthenticationException with a specified message
	 *
	 * @param message The message for the exception.
	 */
	public AuthenticationException(String message) {
		super(message);
		this.message = message;
	}
}
