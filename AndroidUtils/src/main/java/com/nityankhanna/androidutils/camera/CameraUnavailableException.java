package com.nityankhanna.androidutils.camera;

/**
 * Created by Nityan Khanna on 14/11/13.
 * Copyright (c) 2013 NAATec. All rights reserved.
 */

/**
 * An exception that occurs when the camera is trying to be accessed, but unavailable.
 */
public class CameraUnavailableException extends RuntimeException {

	/**
	 * Initializes a new instance of the CameraUnavailableException.
	 */
	public CameraUnavailableException() {
	}

	/**
	 * Initializes a new instance of the CameraUnavailableException with a specified message.
	 *
	 * @param detailMessage The message of the exception.
	 */
	public CameraUnavailableException(String detailMessage) {
		super(detailMessage);
	}
}
