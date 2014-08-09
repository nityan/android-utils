package com.nityankhanna.androidutils.http;

/**
 * Created by Nityan Khanna on Feb 16 2014.
 */
public abstract class HttpRequest implements HttpHeaderStore, HttpParameterStore, HttpMessage {

	public abstract RequestType getRequestType();

	public abstract String getUrl();
}
