package com.nityankhanna.androidutils.http;

import org.apache.http.client.CookieStore;

import java.util.List;

/**
 * Created by Nityan Khanna on Jan 18 2014.
 */
public class HttpRequestMessage implements CookieStore, HttpHeaderStore, HttpMessage, ParameterStore {

	private String url;
	private RequestType requestType;
	private List<HttpParameter> params;
	private List<HttpHeader> headers;
	private List<HttpCookie> cookies;

	public HttpRequestMessage(String url, RequestType requestType) {
		this.url = url;
		this.requestType = requestType;
	}
}
