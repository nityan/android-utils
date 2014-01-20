package com.nityankhanna.androidutils.http;

import org.apache.http.HttpEntity;

import java.util.List;

/**
 * Created by Nityan Khanna on Jan 03 2014.
 */
public final class HttpResponseMessage implements HttpMessage {

	private ContentType contentType;
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
		this(statusCode, reasonPhrase);
		this.entity = entity;
	}

	public HttpResponseMessage(int statusCode, String reasonPhrase, HttpEntity entity, List<HttpHeader> headers) {
		this(statusCode, reasonPhrase, entity);
		this.headers = headers;
	}

	/**
	 * Checks if the HttpRequestMessage contains cookies.
	 *
	 * @return Returns true if the HttpRequestMessage contains cookies.
	 */
	@Override
	public boolean containsCookies() {
		return false;
	}

	/**
	 * Checks if the HttpRequestMessage contains headers.
	 *
	 * @return Returns true if the HttpRequestMessage contains headers.
	 */
	@Override
	public boolean containsHeaders() {
		return headers.size() > 0;
	}

	/**
	 * Checks if the HttpRequestMessage contains parameters.
	 *
	 * @return Returns true if the HttpRequestMessage contains parameters.
	 */
	@Override
	public boolean containsParameters() {
		return requestMessage.containsParameters();
	}

	@Override
	public ContentType getContentType() {
		return contentType;
	}

	/**
	 * Gets the encoding of the message.
	 *
	 * @return Returns the encoding.
	 */
	@Override
	public Encoding getEncoding() {
		return null;
	}

	/**
	 * Gets a list of HttpCookies.
	 *
	 * @return Returns a list of HttpCookies.
	 */
	@Override
	public List<HttpCookie> getCookies() {
		return null;
	}

	/**
	 * Gets a list of HttpHeaders.
	 *
	 * @return Returns a list of HttpHeaders.
	 */
	@Override
	public List<HttpHeader> getHeaders() {
		return headers;
	}

	/**
	 * Gets a list of HttpParameters.
	 *
	 * @return Returns a list of HttpParameters.
	 */
	@Override
	public List<HttpParameter> getParameters() {
		return null;
	}

	protected void setContentType(ContentType contentType) {
		this.contentType = contentType;
	}

	public HttpEntity getEntity() {
		return entity;
	}

	protected void setEntity(HttpEntity entity) {
		this.entity = entity;
	}

	public ErrorResponse getError() {
		return error;
	}

	protected void setError(ErrorResponse error) {
		this.error = error;
	}

	public String getReasonPhrase() {
		return reasonPhrase;
	}

	protected void setReasonPhrase(String reasonPhrase) {
		this.reasonPhrase = reasonPhrase;
	}

	public HttpRequestMessage getRequestMessage() {
		return requestMessage;
	}

	protected void setRequestMessage(HttpRequestMessage requestMessage) {
		this.requestMessage = requestMessage;
	}

	public int getStatusCode() {
		return statusCode;
	}

	protected void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
}
