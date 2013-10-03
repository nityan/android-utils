package com.nityankhanna.androidutils.http;

import com.nityankhanna.androidutils.enums.RequestType;
import com.nityankhanna.androidutils.exceptions.InvalidArgumentException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Nityan Khanna on 01/07/13.
 */

/**
 * A utility class to execute and handle HTTP requests and responses.
 */
public class HttpClient extends DefaultHttpClient {

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
	 * Initializes a new instance of the HttpClient class with a specified context, URL, request type and response listener.
	 *
	 * @param url         The URL.
	 * @param requestType The type of request to be sent.
	 * @param response    The response listener used to listen for the HTTP response.
	 * @throws URISyntaxException
	 */
	public HttpClient(String url, RequestType requestType, OnHttpResponseListener response) throws URISyntaxException {
		super();
		this.url = new URI(url);
		this.requestType = requestType;
		this.response = response;
	}

	/**
	 * Initializes a new instance of the HttpClient class with a specified context, URL, request type, parameters and response listener.
	 *
	 * @param url         The URL.
	 * @param params      The parameters for the request.
	 * @param requestType The type of request to be sent.
	 * @param response    The response listener used to listen for the HTTP response.
	 * @throws URISyntaxException
	 */
	public HttpClient(String url, JSONObject params, RequestType requestType, OnHttpResponseListener response) throws URISyntaxException {
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

		org.apache.http.client.HttpClient client = new DefaultHttpClient();
		HttpResponse httpResponse = null;

		switch (requestType) {

			case GET:
				HttpGet get = new HttpGet(url);
				get.addHeader(ACCEPT, ACCEPT_VALUE);
				get.addHeader(CONTENT_TYPE, CONTENT_TYPE_VALUE);
				httpResponse = client.execute(get);

				break;

			case POST:

				if (params == null || params.length() == 0) {
					throw new InvalidArgumentException("Params are required when sending a POST request.");
				}

				HttpPost post = new HttpPost(url);
				post.addHeader(ACCEPT, ACCEPT_VALUE);
				post.addHeader(CONTENT_TYPE, CONTENT_TYPE_VALUE);
				post.setEntity(new StringEntity(params.toString(), UTF8));
				httpResponse = client.execute(post);

				break;


			case PUT:
				break;

			case DELETE:
				break;

			default:
				break;
		}

		final HttpResponse finalResponse = httpResponse;

		Future<Object> future = Executors.newSingleThreadExecutor().submit(new Callable<Object>() {

			@Override
			public Object call() throws Exception {

				Object result = null;
				BufferedReader reader = new BufferedReader(new InputStreamReader(finalResponse.getEntity().getContent(), "UTF-8"));
				StringBuilder builder = new StringBuilder();

				try {
					for (String line; (line = reader.readLine()) != null; ) {
						builder.append(line).append("\n");
					}

					String reasonPhrase = finalResponse.getStatusLine().getReasonPhrase();
					int statusCode = finalResponse.getStatusLine().getStatusCode();

				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					reader.close();
				}

				return result;
			}
		});
	}

	/**
	 * Sets the response listener to listen for the HTTP response.
	 *
	 * @param response The response listener.
	 */
	public void setResponseListener(OnHttpResponseListener response) {
		this.response = response;
	}
}