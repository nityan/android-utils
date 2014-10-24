package com.nityankhanna.androidutils.http;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Nityan on 2014-10-23.
 */
public class HttpClientServiceTest
{
	private HttpClientService clientService;

	@Before
	public void setUp()
	{
	}

	@After
	public void tearDown()
	{
	}

	@Test
	public void initializeHttpClientService_ShouldPass()
	{
		HttpRequestMessage requestMessage = new HttpRequestMessage("http://google.ca/", RequestType.GET);

		clientService = new HttpClientService(requestMessage, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void initializeHttpClientService_Null_URL()
	{
		HttpRequestMessage requestMessage = new HttpRequestMessage(null, RequestType.GET);

		clientService = new HttpClientService(requestMessage, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void initializeHttpClientService_Malformed_URL()
	{
		HttpRequestMessage requestMessage = new HttpRequestMessage("", RequestType.GET);

		clientService = new HttpClientService(requestMessage, null);
	}
}
