package com.nityankhanna.androidutils.http;

import org.apache.http.HttpEntity;

import java.util.List;

/**
 * Created by Nityan Khanna on Feb 16 2014.
 */
public abstract class HttpResponse implements HttpMessage {

	protected abstract void setContentType(ContentType contentType);

	public abstract HttpEntity getEntity();

	protected abstract void setEntity(HttpEntity entity);

	public abstract ErrorResponse getError();

	protected abstract void setError(ErrorResponse error);

	public abstract List<HttpHeader> getHeaders();

	public abstract String getReasonPhrase();

	protected abstract void setReasonPhrase(String reasonPhrase);

	public abstract HttpRequestMessage getRequestMessage();

	protected abstract void setRequestMessage(HttpRequestMessage requestMessage);

	public abstract int getStatusCode();

	protected abstract void setStatusCode(int statusCode);
}
