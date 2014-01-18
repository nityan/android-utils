package com.nityankhanna.androidutils.io;

/**
 * Created by Nityan Khanna on 14/11/13.
 */

/**
 * Represents an exception that occurs when writing to a file.
 */
public class FileWriteException extends RuntimeException {

	/**
	 * Initializes a new instance of the FileWriteException.
	 */
	public FileWriteException() {
	}

	/**
	 * Initializes a new instance of the FileWriteException with a specified message.
	 *
	 * @param detailMessage The message of the exception.
	 */
	public FileWriteException(String detailMessage) {
		super(detailMessage);
	}
}
