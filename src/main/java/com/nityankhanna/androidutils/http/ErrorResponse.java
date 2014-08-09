package com.nityankhanna.androidutils.http;

/**
 * Created by Nityan Khanna on 04/10/13.
 */

/**
 * Represents an error response.
 */
public class ErrorResponse
{

	private String message;

	/**
	 * Initializes a new instance of the ErrorResponse class.
	 */
	public ErrorResponse()
	{
	}

	/**
	 * Gets the message.
	 *
	 * @return Returns the message.
	 */
	public String getMessage()
	{
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message The message.
	 */
	public void setMessage(String message)
	{
		this.message = message;
	}

	@Override
	public String toString()
	{
		return "ErrorResponse{" +
				"message='" + message + '\'' +
				'}';
	}
}
