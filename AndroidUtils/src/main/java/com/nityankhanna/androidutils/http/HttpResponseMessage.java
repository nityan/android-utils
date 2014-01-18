package com.nityankhanna.androidutils.http;

import org.apache.http.HttpEntity;

import java.util.List;

/**
 * Created by Nityan Khanna on Jan 03 2014.
 */
public class HttpResponseMessage {

	private HttpHeader contentType;
	private HttpEntity entity;
	private ErrorResponse error;
	private List<HttpHeader> headers;
	private String reasonPhrase;
	private HttpRequestMessage requestMessage;
	private int statusCode;

	public HttpResponseMessage() {
	}

	public HttpResponseMessage(int statusCode, String reasonPhrase) {
		this.statusCode = statusCode;
		this.reasonPhrase = reasonPhrase;
	}

	public HttpResponseMessage(int statusCode, String reasonPhrase, HttpEntity entity) {
		this.statusCode = statusCode;
		this.reasonPhrase = reasonPhrase;
		this.entity = entity;
	}

	public HttpResponseMessage(int statusCode, String reasonPhrase, HttpEntity entity, List<HttpHeader> headers) {
		this.statusCode = statusCode;
		this.reasonPhrase = reasonPhrase;
		this.entity = entity;
		this.headers = headers;
	}

	public HttpResponseMessage(int statusCode, String reasonPhrase, HttpEntity entity, List<HttpHeader> headers, ErrorResponse error) {
		this.statusCode = statusCode;
		this.reasonPhrase = reasonPhrase;
		this.entity = entity;
		this.headers = headers;
		this.error = error;
	}

	public HttpHeader getContentType() {
		return contentType;
	}

	public void setContentType(HttpHeader contentType) {
		this.contentType = contentType;
	}

	public HttpEntity getEntity() {
		return entity;
	}

	public void setEntity(HttpEntity entity) {
		this.entity = entity;
	}

	public ErrorResponse getError() {
		return error;
	}

	public void setError(ErrorResponse error) {
		this.error = error;
	}

	public List<HttpHeader> getHeaders() {
		return headers;
	}

	public void setHeaders(List<HttpHeader> headers) {
		this.headers = headers;
	}

	public String getReasonPhrase() {
		return reasonPhrase;
	}

	public void setReasonPhrase(String reasonPhrase) {
		this.reasonPhrase = reasonPhrase;
	}

	public HttpRequestMessage getRequestMessage() {
		return requestMessage;
	}

	public void setRequestMessage(HttpRequestMessage requestMessage) {
		this.requestMessage = requestMessage;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public boolean containsError() {
		return error != null;
	}
}
