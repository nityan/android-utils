package com.nityankhanna.androidutils.http;

import android.os.NetworkOnMainThreadException;

import com.nityankhanna.androidutils.system.ThreadPool;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieIdentityComparator;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Nityan Khanna on 01/07/13.
 */

/**
 * A utility class to execute and handle HTTP requests and responses.
 */
public class HttpClientService implements HttpHeaderStore, CookieStore {

	private static ThreadPool threadPool = ThreadPool.getInstance();
	private final String UTF8 = "UTF-8";
	private final List<HttpHeader> headers;
	private final List<Cookie> cookies;
	private final Comparator<Cookie> cookieComparator;
	private URI url;
	private RequestType requestType;
	private JSONObject params;
	private OnHttpResponseListener delegate;

	/**
	 * Initializes a new instance of the HttpClientService class with a specified context, URL, request type and response listener.
	 *
	 * @param url         The URL.
	 * @param requestType The type of request to be sent.
	 * @param response    The response listener used to listen for the HTTP response.
	 *
	 * @throws URISyntaxException
	 */
	public HttpClientService(String url, RequestType requestType, OnHttpResponseListener response) throws URISyntaxException {
		this.url = new URI(url);
		this.requestType = requestType;
		this.delegate = response;


		if (this.delegate == null) {
			throw new IllegalArgumentException("The response parameter cannot be null");
		}

		headers = new ArrayList<HttpHeader>();
		cookies = new ArrayList<Cookie>();
		this.cookieComparator = new CookieIdentityComparator();
	}

	/**
	 * Initializes a new instance of the HttpClientService class with a specified context, URL, request type, parameters and response listener.
	 *
	 * @param url         The URL.
	 * @param params      The parameters for the request.
	 * @param requestType The type of request to be sent.
	 * @param response    The response listener used to listen for the HTTP response.
	 *
	 * @throws URISyntaxException
	 */
	public HttpClientService(String url, @NotNull JSONObject params, RequestType requestType, OnHttpResponseListener response) throws URISyntaxException {
		this.url = new URI(url);
		this.requestType = requestType;
		this.params = params;
		this.delegate = response;

		if (this.delegate == null) {
			throw new IllegalArgumentException("The response parameter cannot be null");
		}

		headers = new ArrayList<HttpHeader>();
		cookies = new ArrayList<Cookie>();
		this.cookieComparator = new CookieIdentityComparator();
	}

	/**
	 * Adds an HTTP Header to the request.
	 *
	 * @param header The HTTP header to add to the collection.
	 */
	@Override
	public void addHeader(HttpHeader header) {

		if (header == null) {
			throw new IllegalArgumentException("The header object cannot be null");
		}

		for (HttpHeader httpHeader : headers) {

			if (httpHeader.equals(header)) {
				headers.remove(httpHeader);
				break;
			}
		}

		headers.add(header);
	}

	/**
	 * Adds an HTTP header to the request.
	 *
	 * @param index  The index of where to add the header.
	 * @param header The HTTP header to add to the collection.
	 */
	@Override
	public synchronized void addHeader(int index, HttpHeader header) {

		if (header == null) {
			throw new IllegalArgumentException("The header parameter cannot be null");
		}

		if (index < 0) {
			throw new IllegalArgumentException("The index must be greater than 0");
		}

		headers.add(index, header);
	}

	/**
	 * Adds an array of HttpHeaders to the request.
	 *
	 * @param headers The array of headers to add to the request.
	 */
	@Override
	public void addHeaders(HttpHeader[] headers) {

		if (headers == null) {
			throw new IllegalArgumentException("The headers parameter cannot be null");
		}

		for (HttpHeader header : headers) {
			this.addHeader(header);
		}
	}

	/**
	 * Returns a list of HTTP Headers.
	 *
	 * @return Returns a list of HTTP Headers.
	 */
	@Override
	public List<HttpHeader> getHeaders() {
		return headers;
	}

	/**
	 * Removes an HTTP Header.
	 *
	 * @param index The index of the header to remove.
	 */
	@Override
	public synchronized void removeHeader(int index) {
		headers.remove(index);
	}

	/**
	 * Removes an HTTP Header.
	 *
	 * @param header The HTTP header object to be removed.
	 */
	@Override
	public synchronized void removeHeader(HttpHeader header) {

		if (header == null) {
			throw new IllegalArgumentException("The header parameter cannot be null");
		}

		headers.remove(header);
	}

	/**
	 * Removes all of the current HTTP headers.
	 */
	@Override
	public void removeAllHeaders() {
		headers.removeAll(headers);
	}

	@Override
	public synchronized void addCookie(Cookie cookie) {

		if (cookie == null) {
			throw new IllegalArgumentException("The cookie parameter cannot be null");
		}

		// first remove any old cookie that is equivalent
		for (Iterator<Cookie> it = cookies.iterator(); it.hasNext(); ) {
			if (cookieComparator.compare(cookie, it.next()) == 0) {
				it.remove();
				break;
			}
		}

		if (!cookie.isExpired(new Date())) {
			cookies.add(cookie);
		}
	}

	@Override
	public List<Cookie> getCookies() {
		return null;
	}

	@Override
	public boolean clearExpired(Date date) {
		return false;
	}

	@Override
	public void clear() {

	}

	/**
	 * Executes an HTTP request.
	 * <p/>
	 * This method is not run in the background and will need
	 * to threaded properly.
	 */
	public void executeRequest() {

		if (threadPool.isCurrentThreadMain()) {
			throw new NetworkOnMainThreadException();
		}

		HttpClient client = new DefaultHttpClient();
		HttpResponse httpResponse = null;

		switch (requestType) {

			case GET:
				httpResponse = executeGetRequest(client);
				break;

			case POST:
				httpResponse = executePostRequest(client);
				break;

			case PUT:
				httpResponse = executePutRequest(client);
				break;

			case DELETE:
				httpResponse = executeDeleteRequest(client);
				break;

			default:
				break;
		}

		parseResponse(httpResponse);
	}

	/**
	 * Executes an HTTP request on a background thread.
	 */
	public void executeRequestAsync() {

		threadPool.queueWorkerItem(new Runnable() {

			@Override
			public void run() {
				HttpClient client = new DefaultHttpClient();
				HttpResponse httpResponse = null;

				switch (requestType) {

					case GET:
						httpResponse = executeGetRequest(client);
						break;

					case POST:
						httpResponse = executePostRequest(client);
						break;

					case PUT:
						httpResponse = executePutRequest(client);
						break;

					case DELETE:
						httpResponse = executeDeleteRequest(client);
						break;

					default:
						break;
				}

				parseResponse(httpResponse);
			}

		});
	}

	private HttpResponse executeDeleteRequest(HttpClient client) {

		HttpDelete delete = new HttpDelete(url);
		delete.setHeaders(headers.toArray(new Header[headers.size()]));

		HttpResponse httpResponse = null;

		try {
			httpResponse = client.execute(delete);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return httpResponse;
	}

	private HttpResponse executeGetRequest(HttpClient client) {

		HttpGet get = new HttpGet(url);
		get.setHeaders(headers.toArray(new Header[headers.size()]));

		HttpResponse httpResponse = null;

		try {
			httpResponse = client.execute(get);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return httpResponse;
	}

	private HttpResponse executePostRequest(HttpClient client) {

		HttpPost post = new HttpPost(url);
		post.setHeaders(headers.toArray(new Header[headers.size()]));

		HttpResponse httpResponse = null;

		try {
			post.setEntity(new StringEntity(params.toString(), UTF8));
			httpResponse = client.execute(post);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return httpResponse;
	}

	private HttpResponse executePutRequest(HttpClient client) {

		HttpPut put = new HttpPut(url);
		put.setHeaders(headers.toArray(new Header[headers.size()]));

		HttpResponse httpResponse = null;

		try {
			put.setEntity(new StringEntity(params.toString(), UTF8));
			httpResponse = client.execute(put);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return httpResponse;
	}

	private void parseResponse(final HttpResponse httpResponse) {

		String reasonPhrase = httpResponse.getStatusLine().getReasonPhrase();
		int statusCode = httpResponse.getStatusLine().getStatusCode();

		if (statusCode >= 500) {
			ErrorResponse error = new ErrorResponse();

			error.setMessage(statusCode + " " + reasonPhrase);
			delegate.onServerError(error);
		} else if (statusCode >= 400) {
			ErrorResponse error = new ErrorResponse();

			error.setMessage(statusCode + " " + reasonPhrase);
			delegate.onClientError(error);
		} else {

			HttpEntity entity = httpResponse.getEntity();

			switch (requestType) {

				case GET:
					delegate.onGetCompleted(entity);
					break;

				case POST:
					delegate.onPostCompleted(entity);
					break;

				case PUT:
					delegate.onPutCompleted(entity);
					break;

				case DELETE:
					delegate.onDeleteCompleted(entity);
					break;

				default:
					break;
			}
		}
	}
}