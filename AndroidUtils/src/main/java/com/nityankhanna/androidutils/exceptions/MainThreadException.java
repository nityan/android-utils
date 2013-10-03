package com.nityankhanna.androidutils.exceptions;

/**
 * Created by Nityan Khanna on 28/06/13.
 */

/**
 * An exception which is thrown when an illegal operation is being performed on the main thread.
 */
public class MainThreadException extends Exception {

	/**
	 * The message for the exception.
	 */
	private String message;

	/**
	 * Initializes a new MainThreadException with a null message.
	 */
	public MainThreadException() {
		super();
	}

	/**
	 * Initializes a new MainThreadException with a specified message
	 *
	 * @param message The message for the exception.
	 */
	public MainThreadException(String message) {
		super(message);
		this.message = message;
	}
}
