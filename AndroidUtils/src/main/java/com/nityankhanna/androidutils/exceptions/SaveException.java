package com.nityankhanna.androidutils.exceptions;

/**
 * Created by Nityan Khanna on 28/06/13.
 */

/**
 * An exception which is thrown when a saving operation fails.
 */
public class SaveException extends Exception {

	/**
	 * The message for the exception.
	 */
	private String message;

	/**
	 * Initializes a new SaveException with null as the message.
	 */
	public SaveException() {
		super();
	}

	/**
	 * Initializes a new SaveException with a specified message.
	 *
	 * @param message The message for the exception.
	 */
	public SaveException(String message) {
		super(message);
		this.message = message;
	}
}
