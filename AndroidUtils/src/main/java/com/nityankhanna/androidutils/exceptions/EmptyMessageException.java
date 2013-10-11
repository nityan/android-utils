package com.nityankhanna.androidutils.exceptions;

/**
 * Created by Tyler Urquhart on 9/20/2013.
 */

/**
 * An exception which is thrown when an unexpected value is input.
 */
public class EmptyMessageException extends Exception {

	/**
	 * The message for the exception.
	 */
	private String message;

	/**
	 * Initializes a new EmptyMessageException with a null message.
	 */
	public EmptyMessageException() {
		super();
	}

	/**
	 * Initializes a new EmptyMessageException with a specified message.
	 *
	 * @param message The message for the exception.
	 */
	public EmptyMessageException(String message) {
		super(message);
		this.message = message;
	}
}
