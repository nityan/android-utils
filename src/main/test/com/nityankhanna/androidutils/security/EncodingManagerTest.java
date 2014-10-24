package com.nityankhanna.androidutils.security;

import com.nityankhanna.androidutils.StringUtils;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Nityan Khanna on 10-08-14.
 */

public class EncodingManagerTest
{
	private static final Logger logger = Logger.getLogger(EncodingManagerTest.class.getSimpleName());
	private EncodingManager encodingManager;

	@Before
	public void setUp()
	{
		encodingManager = EncodingManager.getInstance();

	}

	@After
	public void tearDown()
	{
		encodingManager = null;
	}

	@Test
	public void byteArrayTest()
	{
		String expected = "Nityan";
		String expected2 = UUID.randomUUID().toString();

		byte[] regData = encodingManager.encodeToByteArray(StringUtils.toByteArray(expected));
		byte[] larData = encodingManager.encodeToByteArray(StringUtils.toByteArray(expected2));

		assertNotNull(regData);
		assertNotNull(larData);

		logger.info(StringUtils.toString(regData));
		logger.info(StringUtils.toString(larData));

		byte[] actual = encodingManager.decodeToByteArray(regData);
		byte[] actual2 = encodingManager.decodeToByteArray(larData);

		logger.info(StringUtils.toString(regData));
		logger.info(StringUtils.toString(larData));

		Assert.assertEquals(expected, StringUtils.toString(actual));
		Assert.assertEquals(expected2, StringUtils.toString(actual2));
	}

	@Test
	public void stringTest() throws EncoderException, DecoderException
	{
		Integer expected = 2;
		String expected2 = UUID.randomUUID().toString();

		String regData = encodingManager.encodeToString(StringUtils.toByteArray(expected.toString()));
		String larData = encodingManager.encodeToString(StringUtils.toByteArray(expected2));

		assertNotNull(regData);
		assertNotNull(larData);

		logger.info(regData);
		logger.info(larData);

		String actual = encodingManager.decodeToString(StringUtils.toByteArray(regData));
		String actual2 = encodingManager.decodeToString(StringUtils.toByteArray(larData));

		logger.info(regData);
		logger.info(larData);

		Assert.assertEquals(expected.intValue(), Integer.parseInt(actual));
		Assert.assertEquals(expected2, actual2);
	}
}