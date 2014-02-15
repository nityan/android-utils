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

	public void testGetInstance() throws Exception {

		Class expectedResult = EncodingManager.class;
		EncodingManager result = EncodingManager.getInstance();

		assertEquals(expectedResult, result.getClass());
	}
	
	public void testDecodeToByteArray() throws Exception {

		String name = "Nityan Khanna";

		EncodingManager encodingManager = EncodingManager.getInstance();

		byte[] encodedData = encodingManager.encodeToByteArray(StringUtils.toByteArray(name));

		assertNotNull("Encoded data is null", encodedData);

		byte[] decodedData = encodingManager.decodeToByteArray(encodedData);

		assertNotNull("Decoded data is null", decodedData);
		assertNotSame("Decoded data is not equal to the original data", StringUtils.toByteArray(name), decodedData);
	}
	
	public void testDecodeToString() throws Exception {

		String name = "Nityan Khanna";

		EncodingManager encodingManager = EncodingManager.getInstance();

		String encodedData = encodingManager.encodeToString(StringUtils.toByteArray(name));

		assertNotNull("Encoded data is null", encodedData);

		String decodedData = encodingManager.decodeToString(StringUtils.toByteArray(encodedData));

		assertNotNull("Decoded data is null", decodedData);
		assertEquals("Decoded data is not equal to the original data", name, decodedData);
	}
}
