package com.nityankhanna.tests;

import android.content.Context;
import android.test.AndroidTestCase;

import com.nityankhanna.androidutils.file_io.FileManager;
import com.nityankhanna.androidutils.file_io.FileMode;

/**
 * Created by Nityan Khanna on Feb 15 2014.
 */
public class FileManagerTest extends AndroidTestCase {

	public FileManagerTest() {
	}

	public void testReadObjectFromFile() throws Exception {

		Context context = getContext();
		String filename = "test-data.dat";

		assertNotNull("The context is null", context);

		Object object = FileManager.readObjectFromFile(context, filename);

		assertEquals("The read object is not the same type as the written object", object.getClass(), String.class);
	}

	public void testReadTextFileFromResources() throws Exception {

	}

	public void testWriteObjectToFile() throws Exception {

		Context context = getContext();

		String filename = "test-data.dat";
		String data = "Nityan Khanna, Age: 221";

		assertNotNull("The context is null", context);

		FileManager.writeObjectToFile(context, data, filename, FileMode.MODE_PRIVATE);
	}
}
