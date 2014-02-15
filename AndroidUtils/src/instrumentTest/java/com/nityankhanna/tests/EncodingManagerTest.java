package com.nityankhanna.tests;

import junit.framework.TestCase;

import com.nityankhanna.androidutils.StringUtils;
import com.nityankhanna.androidutils.security.EncodingManager;

/**
 * Created by Nityan Khanna on Feb 15 2014.
 */
public class EncodingManagerTest extends TestCase {

	public EncodingManagerTest() {
	}

	public void setUp() throws Exception {
		super.setUp();
	}

	public void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetInstance() throws Exception {

		Class expectedResult = EncodingManager.class;
		EncodingManager result = EncodingManager.getInstance();

		assertEquals(expectedResult, result.getClass());
	}

	public void testEncodeToByteArray() throws Exception {

		String name = "Nityan Khanna";

		EncodingManager encodingManager = EncodingManager.getInstance();

		byte[] data = encodingManager.encodeToByteArray(StringUtils.toByteArray(name));

		assertNotNull("Data is null", data);
	}


	public void testEncodeToString() throws Exception {

	}
	
	public void testDecodeToByteArray() throws Exception {

	}
	
	public void testDecodeToString() throws Exception {

	}
}
