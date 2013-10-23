package com.nityankhanna.androidutils.system;

/**
 * Created by Nityan Khanna on 28/08/13.
 */

/**
 * An exception which is thrown when an unavailable service is trying to be accessed.
 */
public class ServiceUnavailableException extends Exception {

	/**
	 * The message for the exception.
	 */
	private String message;

	/**
	 * Initializes a new ServiceUnavailableException with null as the message.
	 */
	public ServiceUnavailableException() {
		super();
	}

	/**
	 * Initializes a new ServiceUnavailableException with a specified message.
	 *
	 * @param message The message for the exception.
	 */
	public ServiceUnavailableException(String message) {
		super(message);
		this.message = message;
	}
}
