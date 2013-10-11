package com.nityankhanna.androidutils.http;

import android.util.Log;

import com.nityankhanna.androidutils.async.ThreadPool;
import com.nityankhanna.androidutils.defines.Constants;
import com.nityankhanna.androidutils.enums.RequestType;
import com.nityankhanna.androidutils.models.ErrorResponse;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nityan Khanna on 01/07/13.
 */

/**
 * A utility class to execute and handle HTTP requests and responses.
 */
public class HttpClientService {

	private static ThreadPool threadPool = ThreadPool.getInstance();
	private final String UTF8 = "UTF-8";
	private URI url;
	private RequestType requestType;
	private JSONObject params;
	private OnHttpResponseListener response;
	private List<Header> headers;

	/**
	 * Initializes a new instance of the HttpClientService class with a specified context, URL, request type and response listener.
	 *
	 * @param url         The URL.
	 * @param requestType The type of request to be sent.
	 * @param response    The response listener used to listen for the HTTP response.
	 * @throws URISyntaxException
	 */
	public HttpClientService(String url, RequestType requestType, OnHttpResponseListener response) throws URISyntaxException {
		super();
		this.url = new URI(url);
		this.requestType = requestType;
		this.response = response;
		headers = new ArrayList<Header>();
	}

	/**
	 * Initializes a new instance of the HttpClientService class with a specified context, URL, request type, parameters and response listener.
	 *
	 * @param url         The URL.
	 * @param params      The parameters for the request.
	 * @param requestType The type of request to be sent.
	 * @param response    The response listener used to listen for the HTTP response.
	 * @throws URISyntaxException
	 */
	public HttpClientService(String url, @NotNull JSONObject params, RequestType requestType, OnHttpResponseListener response) throws URISyntaxException {
		super();
		this.url = new URI(url);
		this.requestType = requestType;
		this.params = params;
		this.response = response;
		headers = new ArrayList<Header>();
	}

	/**
	 * Adds an HTTP Header to the request.
	 *
	 * @param key   The header key.
	 * @param value The header value.
	 */
	public void addHeader(@NotNull String key, @NotNull String value) {
		headers.add(new BasicHeader(key, value));
	}

	/**
	 * Executes an HTTP request.
	 * <p/>
	 * This method is not run in the background and will need
	 * to threaded properly.
	 */
	public void executeRequest() {

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
				// TODO: add functionality for HTTP Put
				Log.e(Constants.DEBUG, "Put requests have not been implemented yet.");
				break;

			case DELETE:
				// TODO: add functionality for HTTP Delete
				Log.e(Constants.DEBUG, "Delete requests have not been implemented yet.");
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
						// TODO: add functionality for HTTP Put
						Log.e(Constants.DEBUG, "Put requests have not been implemented yet.");
						break;

					case DELETE:
						// TODO: add functionality for HTTP Delete
						Log.e(Constants.DEBUG, "Delete requests have not been implemented yet.");
						break;

					default:
						break;
				}


				parseResponse(httpResponse);
			}

		});
	}

	/**
	 * Removes an Http Header.
	 *
	 * @param index The index of the header to remove.
	 */
	public void removeHeader(int index) {
		headers.remove(index);
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

		BufferedReader reader = null;
		StringBuilder builder;

		try {
			reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
			builder = new StringBuilder();


			for (String line; (line = reader.readLine()) != null; ) {
				builder.append(line).append("\n");
			}

			if (statusCode >= 500) {
				ErrorResponse error = new ErrorResponse();

				error.setMessage(statusCode + " " + reasonPhrase);
				response.onServerError(error);
			} else if (statusCode >= 400) {

			} else {

				HttpEntity entity = httpResponse.getEntity();

				switch (requestType) {

					case GET:
						response.onGetCompleted(entity);
						break;

					case POST:
						response.onPostCompleted(entity);
						break;

					case PUT:
						response.onPutCompleted(entity);
						break;

					case DELETE:
						response.onDeleteCompleted(entity);
						break;

					default:
						break;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}