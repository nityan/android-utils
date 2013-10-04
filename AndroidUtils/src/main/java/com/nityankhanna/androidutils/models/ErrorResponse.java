package com.nityankhanna.androidutils.models;

import org.json.JSONObject;

/**
 * Created by Nityan Khanna on 04/10/13.
 */

/**
 * An ErrorResponse class to hold an error message.
 */
public class ErrorResponse {

	private String message;
	private JSONObject content;

	public ErrorResponse() {
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public JSONObject getContent() {
		return content;
	}

	public void setContent(JSONObject content) {
		this.content = content;
	}
}
