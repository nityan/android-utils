package com.nityankhanna.androidutils.http;

import android.os.AsyncTask;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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
public final class HttpClientService
{

	private OnHttpResponseListener delegate;
	private List<HttpHeader> headers;
	private List<HttpParameter> params;
	private HttpRequestMessage requestMessage;
	private RequestType requestType;
	private URI url;

	/**
	 * Initializes a new instance of the HttpClientService class with a specified context, URL, request type and response listener.
	 *
	 * @param requestMessage The request message.
	 * @param response       The response listener used to listen for the HTTP response.
	 */
	public HttpClientService(HttpRequestMessage requestMessage, OnHttpResponseListener response)
	{
		this.requestMessage = requestMessage;

		try
		{
			this.url = new URI(requestMessage.getUrl());
		} catch (URISyntaxException e)
		{
			throw new IllegalArgumentException("Bad URL: " + url);
		}

		this.requestType = requestMessage.getRequestType();
		this.delegate = response;

		if (requestMessage.containsHeaders())
		{
			headers = requestMessage.getHeaders();
		} else
		{
			headers = new ArrayList<>();
		}

		if (requestMessage.containsParameters())
		{
			params = requestMessage.getParameters();
		}
	}

	/**
	 * Executes an HTTP request on a background thread.
	 */
	public void executeRequestAsync()
	{
		new HttpClientTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
	}

	private class HttpClientTask extends AsyncTask<Void, Void, HttpResponse>
	{

		private DefaultHttpClient client;

		@Override
		protected HttpResponse doInBackground(Void... voids)
		{

			client = new DefaultHttpClient();
			HttpResponse httpResponse;

			switch (requestType)
			{

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
		protected void onPostExecute(HttpResponse httpResponse)
		{
			super.onPostExecute(httpResponse);

			String reasonPhrase = null;
			int statusCode = -1;

			if (httpResponse.getStatusLine() != null)
			{
				reasonPhrase = httpResponse.getStatusLine().getReasonPhrase();
				statusCode = httpResponse.getStatusLine().getStatusCode();
			}

			HttpEntity entity = httpResponse.getEntity();

			List<HttpHeader> httpHeaders = new ArrayList<>();

			for (Header header : httpResponse.getAllHeaders())
			{
				HttpHeader httpHeader = new HttpHeader(header.getName(), header.getValue());
				httpHeaders.add(httpHeader);
			}

			HttpResponseMessage responseMessage = new HttpResponseMessage(statusCode, reasonPhrase, entity, httpHeaders);

			for (HttpHeader header : httpHeaders)
			{

				if (header.getValue().contains("application/json"))
				{
					responseMessage.setContentType(ContentType.JSON);
					break;
				}
			}

			responseMessage.setRequestMessage(requestMessage);

			if (statusCode >= 500)
			{
				ErrorResponse error = new ErrorResponse();

				error.setMessage(statusCode + " " + reasonPhrase);
				responseMessage.setError(error);

				delegate.onServerError(responseMessage);
			} else if (statusCode >= 400)
			{
				ErrorResponse error = new ErrorResponse();

				error.setMessage(statusCode + " " + reasonPhrase);
				responseMessage.setError(error);

				delegate.onClientError(responseMessage);
			} else
			{

				switch (requestType)
				{

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

		private HttpResponse executeDeleteRequest()
		{

			HttpDelete delete = new HttpDelete(url);

			delete.setHeaders(headers.toArray(new Header[headers.size()]));

			HttpResponse httpResponse = null;

			try
			{
				httpResponse = client.execute(delete);
			} catch (IOException e)
			{
				e.printStackTrace();
			}

			return httpResponse;
		}

		private HttpResponse executeGetRequest()
		{

			HttpGet get = new HttpGet(url);

			HttpResponse httpResponse = null;

			get.setHeaders(headers.toArray(new Header[headers.size()]));

			try
			{
				httpResponse = client.execute(get);
			} catch (IOException e)
			{
				e.printStackTrace();
			}

			return httpResponse;
		}

		private HttpResponse executePostRequest()
		{

			HttpPost post = new HttpPost(url);

			HttpResponse httpResponse = null;

			try
			{

				if (requestMessage.getContentType().equals(ContentType.JSON))
				{

					JSONObject body = new JSONObject();

					for (HttpParameter parameter : params)
					{
						body.put(parameter.getName(), parameter.getValue());
					}

					post.setEntity(new StringEntity(body.toString()));
				} else
				{

					List<NameValuePair> data = new ArrayList<>();

					for (HttpParameter parameter : params)
					{
						data.add(new BasicNameValuePair(parameter.getName(), parameter.getValue()));
					}

					post.setEntity(new UrlEncodedFormEntity(data));
				}

				post.setHeaders(headers.toArray(new Header[headers.size()]));

				httpResponse = client.execute(post);
			} catch (JSONException | IOException e)
			{
				e.printStackTrace();
			}

			return httpResponse;
		}

		private HttpResponse executePutRequest()
		{

			HttpPut put = new HttpPut(url);

			HttpResponse httpResponse = null;

			try
			{

				if (requestMessage.getContentType().equals(ContentType.JSON))
				{

					headers.add(new HttpHeader("Content-Type", "application/json;charset=" + requestMessage.getEncoding().getValue()));
					JSONObject body = new JSONObject();

					for (HttpParameter parameter : params)
					{
						body.put(parameter.getName(), parameter.getValue());
					}

					put.setEntity(new StringEntity(body.toString(), requestMessage.getEncoding().getValue()));
				} else
				{

					List<NameValuePair> data = new ArrayList<>();

					for (HttpParameter parameter : params)
					{
						data.add(new BasicNameValuePair(parameter.getName(), parameter.getValue()));
					}

					put.setEntity(new UrlEncodedFormEntity(data));
				}

				put.setHeaders(headers.toArray(new Header[headers.size()]));

				httpResponse = client.execute(put);
			} catch (JSONException | IOException e)
			{
				e.printStackTrace();
			}

			return httpResponse;
		}
	}
}