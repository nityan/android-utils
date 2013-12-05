package com.nityankhanna.androidutils.database;

/**
 * Created by Nityan Khanna on 05/12/13.
 */

/**
 * An exception which is thrown when the database becomes corrupted.
 */
public class DatabaseCorruptionException extends RuntimeException {

	/**
	 * Initializes a new instance of the DatabaseCorruptionException.
	 */
	public DatabaseCorruptionException() {
	}

	/**
	 * Initializes a new instance of the DatabaseCorruptionException with a specified message.
	 *
	 * @param detailMessage The message of the exception.
	 */
	public DatabaseCorruptionException(String detailMessage) {
		super(detailMessage);
	}
}
