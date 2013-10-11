package com.nityankhanna.androidutils.exceptions;

/**
 * Created by Nityan Khanna on 29/06/13.
 */

/**
 * An exception which is thrown when a class, constructor, or method is being accessed which has not been implemented.
 */
public class NotImplementedException extends Exception {

	/**
	 * THe message for the exception.
	 */
	private String message;

	/**
	 * Initializes a new NotImplementedException with a null message.
	 */
	public NotImplementedException() {
		super();
	}

	/**
	 * Initializes a new NotImplementedException with a specified message.
	 *
	 * @param message The message for the exception.
	 */
	public NotImplementedException(String message) {
		super(message);
		this.message = message;
	}
}
