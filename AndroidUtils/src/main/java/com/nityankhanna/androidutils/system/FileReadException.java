package com.nityankhanna.androidutils.system;

/**
 * Created by Nityan Khanna on 14/11/13.
 * Copyright (c) 2013 NAATec. All rights reserved.
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
