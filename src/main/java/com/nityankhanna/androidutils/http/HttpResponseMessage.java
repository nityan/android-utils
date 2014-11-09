package com.nityankhanna.androidutils.http;

import org.apache.http.HttpEntity;

import java.util.List;

/**
 * Created by Nityan Khanna on Jan 03 2014.
 */

/**
 * Represents an Http response message.
 */
public final class HttpResponseMessage implements HttpMessage
{

	private ContentType contentType;
	private HttpEntity entity;
	private ErrorResponse error;
	private List<HttpHeader> headers;
	private List<HttpParameter> parameters;
	private String reasonPhrase;
	private HttpRequestMessage requestMessage;
	private HttpStatusCode statusCode;

	/**
	 * Initializes a new instance of the HttpResponseMessage class with a status code and reason phrase.
	 *
	 * @param statusCode   The status code.
	 * @param reasonPhrase The reason phrase.
	 */
	protected HttpResponseMessage(HttpStatusCode statusCode, String reasonPhrase)
	{
		this.statusCode = statusCode;
		this.reasonPhrase = reasonPhrase;
	}

	/**
	 * Initializes a new instance of the HttpResponseMessage class with a status code, reason phrase and entity.
	 *
	 * @param statusCode   The status code.
	 * @param reasonPhrase The reason phrase.
	 * @param entity       The entity.
	 */
	public HttpResponseMessage(HttpStatusCode statusCode, String reasonPhrase, HttpEntity entity)
	{
		this(statusCode, reasonPhrase);
		this.entity = entity;
	}

	/**
	 * Initializes a new instance of the HttpResponseMessage class with a status code, reason phrase, entity and http headers.
	 *
	 * @param statusCode   The status code.
	 * @param reasonPhrase The reason phrase.
	 * @param entity       The entity.
	 * @param headers      The http headers.
	 */
	public HttpResponseMessage(HttpStatusCode statusCode, String reasonPhrase, HttpEntity entity, List<HttpHeader> headers)
	{
		this(statusCode, reasonPhrase, entity);
		this.headers = headers;
	}

	/**
	 * Sets the content type.
	 *
	 * @param contentType The content type.
	 */
	protected void setContentType(ContentType contentType)
	{
		this.contentType = contentType;
	}

	/**
	 * Gets the entity.
	 *
	 * @return Returns the entity.
	 */
	public HttpEntity getEntity()
	{
		return entity;
	}

	/**
	 * Sets the entity.
	 *
	 * @param entity The entity.
	 */
	protected void setEntity(HttpEntity entity)
	{
		this.entity = entity;
	}

	/**
	 * Gets the error response.
	 *
	 * @return Returns the error response.
	 */
	public ErrorResponse getError()
	{
		return error;
	}

	/**
	 * Sets the error.
	 *
	 * @param error The error response.
	 */
	protected void setError(ErrorResponse error)
	{
		this.error = error;
	}

	/**
	 * Gets the reason phrase.
	 *
	 * @return The reason phrase.
	 */
	public String getReasonPhrase()
	{
		return reasonPhrase;
	}

	/**
	 * Sets the reason phrase.
	 *
	 * @param reasonPhrase The reason phrase.
	 */
	protected void setReasonPhrase(String reasonPhrase)
	{
		this.reasonPhrase = reasonPhrase;
	}

	/**
	 * Gets the request message.
	 *
	 * @return Returns the request message.
	 */
	public HttpRequestMessage getRequestMessage()
	{
		return requestMessage;
	}

	/**
	 * Sets the request message.
	 *
	 * @param requestMessage The request message.
	 */
	protected void setRequestMessage(HttpRequestMessage requestMessage)
	{
		this.requestMessage = requestMessage;
	}

	/**
	 * Gets the status code.
	 *
	 * @return Returns the status code.
	 */
	public HttpStatusCode getStatusCode()
	{
		return statusCode;
	}

	/**
	 * Sets the status code.
	 *
	 * @param statusCode The status code.
	 */
	protected void setStatusCode(HttpStatusCode statusCode)
	{
		this.statusCode = statusCode;
	}

	/**
	 * Checks if the Http message contains headers.
	 *
	 * @return Returns true if the Http message contains headers.
	 */
	@Override
	public boolean containsHeaders()
	{
		return headers.size() > 0;
	}

	/**
	 * Checks if the Http message contains parameters.
	 *
	 * @return Returns true if the Http message contains parameters.
	 */
	@Override
	public boolean containsParameters()
	{
		// TODO: implement a check to see if the response body has parameters
		return false;
	}

	@Override
	public ContentType getContentType()
	{
		return contentType;
	}

	/**
	 * Gets the encoding of the message.
	 *
	 * @return Returns the encoding.
	 */
	@Override
	public Encoding getEncoding()
	{
		// TODO: get the encoding
		return null;
	}

	/**
	 * Gets a list of HttpHeaders.
	 *
	 * @return Returns a list of HttpHeaders.
	 */
	public List<HttpHeader> getHeaders()
	{
		return headers;
	}
}
