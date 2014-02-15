package com.nityankhanna.tests;

import junit.framework.TestCase;

import com.nityankhanna.androidutils.security.EncodingManager;

/**
 * Created by Nityan Khanna on Feb 15 2014.
 */
public class EncodingManagerTest extends TestCase {

	public void setUp() throws Exception {

	}

	public void tearDown() throws Exception {

	}

	public void testGetInstance() throws Exception {
		EncodingManager encodingManager = EncodingManager.getInstance();

		assertTrue(encodingManager != null);
	}

	public void testEncodeToByteArray() throws Exception {
		EncodingManager encodingManager = EncodingManager.getInstance();

		String data = encodingManager.encodeToString("Nityan".getBytes());

		assertTrue("Data is not null", data == null);
	}


	public void testEncodeToString() throws Exception {

	}
	
	public void testDecodeToByteArray() throws Exception {

	}
	
	public void testDecodeToString() throws Exception {

	}
}
