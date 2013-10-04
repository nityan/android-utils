package com.nityankhanna.androidutils.http;

import com.nityankhanna.androidutils.async.ThreadPool;
import com.nityankhanna.androidutils.enums.RequestType;
import com.nityankhanna.androidutils.exceptions.InvalidArgumentException;
import com.nityankhanna.androidutils.models.ErrorResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Nityan Khanna Khanna Khanna on 01/07/13.
 */

/**
 * A utility class to execute and handle HTTP requests and responses.
 */
public class HttpClientService extends DefaultHttpClient {

	private static ThreadPool threadPool = ThreadPool.getInstance();
	private final String ACCEPT = "Accept";
	private final String ACCEPT_VALUE = "application/json";
	private final String CONTENT_TYPE = "Content-Type";
	private final String CONTENT_TYPE_VALUE = "application/json;charset=UTF-8";
	private final String UTF8 = "UTF-8";
	private URI url;
	private RequestType requestType;
	private JSONObject params;
	private OnHttpResponseListener response;

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
	}

	/**
	 * Executes an HTTP request.
	 *
	 * @throws IOException
	 * @throws InvalidArgumentException
	 */
	public void executeRequest() throws IOException, InvalidArgumentException {

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
						// TODO: add functionality for HTTP Delete
						break;

					default:
						break;
				}

				parseResponse(httpResponse);
			}
		});
	}

	private HttpResponse executeGetRequest(HttpClient client) {

		HttpGet get = new HttpGet(url);
		get.addHeader(ACCEPT, ACCEPT_VALUE);
		get.addHeader(CONTENT_TYPE, CONTENT_TYPE_VALUE);

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
		post.addHeader(ACCEPT, ACCEPT_VALUE);
		post.addHeader(CONTENT_TYPE, CONTENT_TYPE_VALUE);

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
		put.addHeader(ACCEPT, ACCEPT_VALUE);
		put.addHeader(CONTENT_TYPE, CONTENT_TYPE_VALUE);

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
				response.onCompletedWithError(error);
			} else if (statusCode >= 400) {
				ErrorResponse error = new ErrorResponse();
				JSONObject content = new JSONObject(builder.toString());

				error.setMessage(reasonPhrase);
				error.setContent(content);
				response.onCompletedWithError(error);
			} else {
				JSONArray array;
				JSONObject object = new JSONObject(builder.toString());

				array = new JSONArray();
				array.put(object);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
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