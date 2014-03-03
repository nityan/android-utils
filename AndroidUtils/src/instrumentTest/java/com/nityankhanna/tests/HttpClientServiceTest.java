package com.nityankhanna.tests;

import android.util.Log;

import junit.framework.TestCase;

import com.nityankhanna.androidutils.http.HttpClientService;
import com.nityankhanna.androidutils.http.HttpCookie;
import com.nityankhanna.androidutils.http.HttpHeader;
import com.nityankhanna.androidutils.http.HttpRequestMessage;
import com.nityankhanna.androidutils.http.HttpResponseMessage;
import com.nityankhanna.androidutils.http.OnHttpResponseListener;
import com.nityankhanna.androidutils.http.RequestType;

/**
 * Created by Nityan Khanna on Feb 15 2014.
 */
public class HttpClientServiceTest extends TestCase implements OnHttpResponseListener {

	private final String className = ((Object) this).getClass().getSimpleName();

	public void testExecuteRequestAsync() throws Exception {

		String params = "?token=1278153967-usUbTZhCXNE0sv0QySuaUhDB6drhEN6vu0WXfDJ&secret=QYXr5JCvKMSTRfEG1NJk8Y9WmVaZiMNolkrYzN8zVw";

		HttpRequestMessage requestMessage = new HttpRequestMessage("http://192.168.2.11:10235/api/social/hometimeline" + params, RequestType.GET);

		HttpHeader header = new HttpHeader("test-header", "test-header-value");

		HttpCookie cookie = new HttpCookie("test-cookie", "test-cookie-value");
		cookie.setDomain("*.*");
		cookie.setPath("/;");

		HttpCookie cookie2 = new HttpCookie("test-cookie2", "test-cookie-value2");
		cookie2.setDomain("*.2");
		cookie2.setPath("//;");

		requestMessage.addHeader(header);
		requestMessage.addCookie(cookie);
		requestMessage.addCookie(cookie2);

		HttpClientService client = new HttpClientService(requestMessage, this);

		client.executeRequestAsync();

		Thread.sleep(60000);
	}

	/**
	 * A delegate method called when a GET request is completed.
	 *
	 * @param response The response.
	 */
	@Override
	public void onGetCompleted(HttpResponseMessage response) {
		Log.e(className, response.getContentType().toString());
	}

	/**
	 * A delegate method called when a POST request is completed.
	 *
	 * @param response The response.
	 */
	@Override
	public void onPostCompleted(HttpResponseMessage response) {

	}

	/**
	 * A delegate method called when a PUT request is completed.
	 *
	 * @param response The response.
	 */
	@Override
	public void onPutCompleted(HttpResponseMessage response) {

	}

	/**
	 * A delegate method called when a DELETE request is completed.
	 *
	 * @param response The response.
	 */
	@Override
	public void onDeleteCompleted(HttpResponseMessage response) {

	}

	/**
	 * A delegate method called when an error occurs due to the client.
	 *
	 * @param response The error response.
	 */
	@Override
	public void onClientError(HttpResponseMessage response) {

	}

	/**
	 * A delegate method called when an error occurs on the server.
	 *
	 * @param response The error response.
	 */
	@Override
	public void onServerError(HttpResponseMessage response) {

	}
}
