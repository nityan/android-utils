package com.nityankhanna.androidutils.file_io;

import android.content.Context;
import android.test.InstrumentationTestCase;

import org.junit.After;
import org.junit.Before;

/**
 * Created by Nityan on 2014-08-10.
 */
public class FileManagerTest extends InstrumentationTestCase
{
	private Context context;

	@Before
	public void setUp()
	{
		context = getInstrumentation().getContext();
	}

	@After
	public void tearDown()
	{
		context = null;
	}

	public void testWriteToFile()
	{
		FileManager.writeObjectToFile(context, "hello world", "file.txt", FileMode.MODE_PRIVATE);
	}
}
