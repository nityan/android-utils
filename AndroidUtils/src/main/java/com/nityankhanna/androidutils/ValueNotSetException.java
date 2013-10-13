package com.nityankhanna.androidutils;

/**
 * Created by Nityan Khanna on 01/07/13.
 */

/**
 * An exception which is thrown when a required value has not been set.
 */
public class ValueNotSetException extends RuntimeException {

	/**
	 * The message for the exception.
	 */
	private String message;

	/**
	 * Initializes a new ValueNotSetException with null as the message.
	 */
	public ValueNotSetException() {
		super();
	}

	/**
	 * Initializes a new ValueNotSetException with a specified message.
	 *
	 * @param message The message for the exception.
	 */
	public ValueNotSetException(String message) {
		super(message);
		this.message = message;
	}
}
