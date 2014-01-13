package com.nityankhanna.androidutils.http;

import android.os.AsyncTask;

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
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;

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

	private final List<HttpHeader> headers;
	private final List<Cookie> cookies;
	private final Comparator<Cookie> cookieComparator;
	private URI url;
	private RequestType requestType;
	private BasicHttpParams params;
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
	public HttpClientService(String url, BasicHttpParams params, RequestType requestType, OnHttpResponseListener response) throws URISyntaxException {
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
			addHeader(header);
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
		// TODO: implement the getCookies() method
		throw new UnsupportedOperationException("This method has not been implemented yet");
	}

	@Override
	public boolean clearExpired(Date date) {
		// TODO: implement the clearExpired(...) method
		throw new UnsupportedOperationException("This method has not been implemented yet");
	}

	@Override
	public void clear() {
		// TODO: implement the clear() method
		throw new UnsupportedOperationException("This method has not been implemented yet");
	}

	/**
	 * Executes an HTTP request on a background thread.
	 */
	public void executeRequestAsync() {
		new HttpClientTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
	}

	private class HttpClientTask extends AsyncTask<Void, Void, HttpResponse> {

		private HttpClient client;

		@Override
		protected HttpResponse doInBackground(Void... voids) {

			client = new DefaultHttpClient();
			HttpResponse httpResponse;

			switch (requestType) {

				case GET:
					httpResponse = executeGetRequest();
					break;

				case POST:
					httpResponse = executePostRequest();
					break;

				case PUT:
					httpResponse = executePutRequest();
					break;

				case DELETE:
					httpResponse = executeDeleteRequest();
					break;

				default:
					throw new RuntimeException("Invalid request type");
			}

			return httpResponse;
		}

		@Override
		protected void onPostExecute(HttpResponse httpResponse) {
			super.onPostExecute(httpResponse);

			String reasonPhrase = httpResponse.getStatusLine().getReasonPhrase();
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			HttpEntity entity = httpResponse.getEntity();
			Header[] basicHeaders = httpResponse.getAllHeaders();

			List<HttpHeader> httpHeaders = new ArrayList<HttpHeader>();

			for (Header header : basicHeaders) {
				HttpHeader httpHeader = new HttpHeader(header.getName(), header.getValue());
				httpHeaders.add(httpHeader);
			}

			HttpResponseMessage responseMessage = new HttpResponseMessage(statusCode, reasonPhrase, entity, httpHeaders);

			if (statusCode >= 500) {
				ErrorResponse error = new ErrorResponse();

				error.setMessage(statusCode + " " + reasonPhrase);
				responseMessage.setError(error);

				delegate.onServerError(responseMessage);
			} else if (statusCode >= 400) {
				ErrorResponse error = new ErrorResponse();

				error.setMessage(statusCode + " " + reasonPhrase);
				responseMessage.setError(error);

				delegate.onClientError(responseMessage);
			} else {

				switch (requestType) {

					case GET:
						delegate.onGetCompleted(responseMessage);
						break;

					case POST:
						delegate.onPostCompleted(responseMessage);
						break;

					case PUT:
						delegate.onPutCompleted(responseMessage);
						break;

					case DELETE:
						delegate.onDeleteCompleted(responseMessage);
						break;

					default:
						throw new RuntimeException("Invalid request type");
				}
			}
		}

		private HttpResponse executeDeleteRequest() {

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

		private HttpResponse executeGetRequest() {

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

		private HttpResponse executePostRequest() {

			HttpPost post = new HttpPost(url);
			post.setHeaders(headers.toArray(new Header[headers.size()]));

			HttpResponse httpResponse = null;

			try {
				post.setParams(params);
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

		private HttpResponse executePutRequest() {

			HttpPut put = new HttpPut(url);
			put.setHeaders(headers.toArray(new Header[headers.size()]));

			HttpResponse httpResponse = null;

			try {
				put.setParams(params);
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
	}
}