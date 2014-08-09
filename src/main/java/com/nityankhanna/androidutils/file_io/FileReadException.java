package com.nityankhanna.androidutils.file_io;

/**
 * Created by Nityan Khanna on 14/11/13.
 */

/**
 * Represents an exception that occurs when reading from a file.
 */
public class FileReadException extends RuntimeException {

	/**
	 * Initializes a new instance of the FileReadException.
	 */
	public FileReadException() {
	}

	/**
	 * Initializes a new instance of the FileReadException with a specified message.
	 *
	 * @param detailMessage The message of the exception.
	 */
	public FileReadException(String detailMessage) {
		super(detailMessage);
	}
}
