package com.nityankhanna.androidutils.http;

import android.os.AsyncTask;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.w3c.dom.Document;

import java.io.IOException;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


/**
 * Created by Nityan Khanna on 01/07/13.
 */

/**
 * A utility class to execute and handle HTTP requests and responses.
 */
public final class HttpClientService
{
	private static final String APPLICATION_JSON = "application/json";
	private static final String APPLICATION_XML = "application/xml";
	private static final String TEXT_JSON = "text/json";
	private static final String TEXT_XML = "text/xml";

	private OnHttpResponseListener delegate;
	private List<HttpHeader> headers;
	private HttpRequestMessage requestMessage;
	private RequestType requestType;
	private URL url;

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
			this.url = new URL(requestMessage.getUrl());
		}
		catch (MalformedURLException e)
		{
			throw new IllegalArgumentException("Bad URL: " + url);
		}

		this.requestType = requestMessage.getRequestType();
		this.delegate = response;

		if (requestMessage.containsHeaders())
		{
			headers = requestMessage.getHeaders();
		}
		else
		{
			headers = new ArrayList<>();
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

			HttpResponseMessage responseMessage = new HttpResponseMessage(HttpStatusCode.fromInt(statusCode), reasonPhrase, entity, httpHeaders);

			for (HttpHeader header : httpHeaders)
			{
				if (header.getValue().contains(APPLICATION_JSON) || header.getValue().contains(TEXT_JSON))
				{
					responseMessage.setContentType(ContentType.JSON);
					break;
				}
				else if (header.getValue().contains(APPLICATION_XML) || header.getValue().contains(TEXT_XML))
				{
					responseMessage.setContentType(ContentType.XML);
					break;
				}
			}

			responseMessage.setRequestMessage(requestMessage);

			if (statusCode >= 500)
			{
				ErrorResponse error = new ErrorResponse();

				error.setMessage(reasonPhrase);
				responseMessage.setError(error);

				delegate.onServerError(responseMessage);
			}
			else if (statusCode >= 400)
			{
				ErrorResponse error = new ErrorResponse();

				error.setMessage(reasonPhrase);
				responseMessage.setError(error);

				delegate.onClientError(responseMessage);
			}
			else
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
			HttpDelete delete = new HttpDelete(url.toString());

			delete.setHeaders(headers.toArray(new Header[headers.size()]));

			HttpResponse httpResponse;

			try
			{
				httpResponse = client.execute(delete);
			}
			catch (IOException e)
			{
				throw new RuntimeException(e);
			}

			return httpResponse;
		}

		private HttpResponse executeGetRequest()
		{
			HttpGet get = new HttpGet(url.toString());

			HttpResponse httpResponse;

			get.setHeaders(headers.toArray(new Header[headers.size()]));

			try
			{
				httpResponse = client.execute(get);
			}
			catch (IOException e)
			{
				throw new RuntimeException(e);
			}

			return httpResponse;
		}

		private HttpResponse executePostRequest()
		{
			HttpPost post = new HttpPost(url.toString());

			HttpResponse httpResponse;

			try
			{
				String output = null;

				if (requestMessage.getContentType().equals(ContentType.JSON))
				{
					JSONObject body = requestMessage.getJsonBody();
					output = body.toString();
				}
				else if (requestMessage.getContentType().equals(ContentType.XML))
				{
					Document document = requestMessage.getXmlBody();

					TransformerFactory tf = TransformerFactory.newInstance();
					Transformer transformer = tf.newTransformer();
					transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
					StringWriter writer = new StringWriter();
					transformer.transform(new DOMSource(document), new StreamResult(writer));

					output = writer.getBuffer().toString().replaceAll("\n|\r", "");
				}

				post.setHeaders(headers.toArray(new Header[headers.size()]));

				if (output != null)
				{
					post.setEntity(new StringEntity(output));
				}

				httpResponse = client.execute(post);
			}
			catch (IOException | TransformerException e)
			{
				throw new RuntimeException(e);
			}

			return httpResponse;
		}

		private HttpResponse executePutRequest()
		{
			HttpPut put = new HttpPut(url.toString());

			HttpResponse httpResponse;

			try
			{
				String output = null;

				if (requestMessage.getContentType().equals(ContentType.JSON))
				{
					JSONObject body = requestMessage.getJsonBody();
					output = body.toString();
				}
				else if (requestMessage.getContentType().equals(ContentType.XML))
				{
					Document document = requestMessage.getXmlBody();

					TransformerFactory tf = TransformerFactory.newInstance();
					Transformer transformer = tf.newTransformer();
					transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
					StringWriter writer = new StringWriter();
					transformer.transform(new DOMSource(document), new StreamResult(writer));

					output = writer.getBuffer().toString().replaceAll("\n|\r", "");
				}

				put.setHeaders(headers.toArray(new Header[headers.size()]));

				if (output != null)
				{
					put.setEntity(new StringEntity(output));
				}

				httpResponse = client.execute(put);
			}
			catch (IOException | TransformerException e)
			{
				throw new RuntimeException(e);
			}

			return httpResponse;
		}
	}
}