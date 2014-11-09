package com.nityankhanna.androidutils.system.async;

import com.nityankhanna.androidutils.StringUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.RejectedExecutionException;

/**
 * Created by Nityan on 2014-08-10.
 */
public class ThreadPoolTest
{
	private ThreadPool threadPool;

	@Before
	public void setUp()
	{
		threadPool = ThreadPool.getInstance();
	}

	@After
	public void tearDown()
	{
		threadPool = null;
	}

	@Test(expected = RejectedExecutionException.class)
	public void submit_FullQueue()
	{
		for (int i = 0; i < Integer.MAX_VALUE; i++)
		{
			threadPool.submit(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < Integer.MAX_VALUE; j++)
					{
						StringUtils.toByteArray("Test");
					}
				}
			});
		}
	}

	@Test(expected = NullPointerException.class)
	public void submit_NullTask()
	{
		threadPool.submit(null);
	}
}
