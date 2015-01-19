package com.nityankhanna.androidutils.http;

import org.json.JSONObject;
import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nityan Khanna on Jan 18 2014.
 */

/**
 * Represents an Http request message.
 */
public final class HttpRequestMessage implements HttpHeaderStore, HttpMessage
{
	private ContentType contentType;
	private Encoding encoding;
	private List<HttpHeader> headers;
	private JSONObject jsonBody;
	private Document xmlBody;
	private RequestType requestType;
	private String url;

	/**
	 * Initializes a new instance of the HttpRequestMessage class with a url and request type.
	 * <p>
	 * This is used for GET and DELETE Requests
	 *
	 * @param url         The url.
	 * @param requestType The request type.
	 */
	public HttpRequestMessage(String url, RequestType requestType)
	{
		this.url = url;
		this.requestType = requestType;
		headers = new ArrayList<>();
	}

	/**
	 * Initializes a new instance of the HttpRequestMessage class with a url, request type, content type and encoding.
	 *
	 * This is used for POST and PUT requests.
	 *
	 * @param url         The url.
	 * @param requestType The request type.
	 * @param contentType The content type.
	 * @param encoding    The encoding.
	 */
	public HttpRequestMessage(String url, RequestType requestType, ContentType contentType, Encoding encoding)
	{
		this(url, requestType);
		this.contentType = contentType;
		this.encoding = encoding;
	}

	/**
	 * Gets the request type.
	 *
	 * @return Returns the request type.
	 */
	public RequestType getRequestType()
	{
		return requestType;
	}

	/**
	 * Gets the url.
	 *
	 * @return Returns the url.
	 */
	public String getUrl()
	{
		return url;
	}

	/**
	 * Gets the JSON body.
	 *
	 * @return Returns the JSON body.
	 */
	public JSONObject getJsonBody()
	{
		return jsonBody;
	}

	/**
	 * Sets the JSON body.
	 *
	 * @param body The JSON body.
	 */
	public void setJsonBody(JSONObject body)
	{
		this.jsonBody = body;
	}

	/**
	 * Gets the XML body.
	 *
	 * @return Returns the XML body.
	 */
	public Document getXmlBody()
	{
		return xmlBody;
	}

	/**
	 * Sets the XML body.
	 *
	 * @param xmlBody The XML body.
	 */
	public void setXmlBody(Document xmlBody)
	{
		this.xmlBody = xmlBody;
	}

	/**
	 * Adds an Http header to the request.
	 *
	 * @param header The Http header to add to the collection.
	 */
	@Override
	public void addHeader(HttpHeader header)
	{
		headers.add(header);
	}

	/**
	 * Adds an Http header to the collection at the specified index.
	 *
	 * @param index  The index of where to add the header.
	 * @param header The Http header to add to the collection.
	 */
	@Override
	public void addHeader(int index, HttpHeader header)
	{
		headers.add(index, header);
	}

	/**
	 * Returns a list of Http headers.
	 *
	 * @return Returns a list of Http headers.
	 */
	@Override
	public List<HttpHeader> getHeaders()
	{
		return headers;
	}

	/**
	 * Removes an Http Header.
	 *
	 * @param header The Http header to be removed.
	 */
	@Override
	public void removeHeader(HttpHeader header)
	{
		headers.remove(header);
	}

	/**
	 * Removes the header at the specified index.
	 *
	 * @param index The index of the header.
	 */
	@Override
	public void removeHeader(int index)
	{
		headers.remove(index);
	}

	/**
	 * Removes all of the Http headers.
	 */
	@Override
	public void removeAllHeaders()
	{
		headers.clear();
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
	 * Gets the content type of the message.
	 *
	 * @return Returns the content type.
	 */
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
		return encoding;
	}
}
