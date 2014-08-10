package com.nityankhanna.androidutils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

public class StringUtilsTest
{
	@Before
	public void setUp()
	{

	}

	@After
	public void tearDown()
	{

	}

	@Test
	public void isNullOrEmpty_ValidData()
	{
		Assert.assertTrue(StringUtils.isNullOrEmpty(null));
		Assert.assertTrue(StringUtils.isNullOrEmpty(""));
	}

	@Test
	public void toBoolean_ValidData()
	{
		Assert.assertEquals(true, StringUtils.toBoolean("true"));
	}

	@Test
	public void toBoolean_InvalidData()
	{
		Assert.assertEquals(false, StringUtils.toBoolean("Test"));
	}

	@Test
	public void toBoolean_NullData()
	{
		Assert.assertEquals(false, StringUtils.toBoolean(null));
	}

	@Test(expected = UnsupportedEncodingException.class)
	public void toByteArray_InvalidEncoding() throws UnsupportedEncodingException
	{
		StringUtils.toByteArray("Test", "asdfasdfasdf");
	}

	@Test(expected = NullPointerException.class)
	public void toByteArray_NullData()
	{
		StringUtils.toByteArray(null);
	}

	@Test(expected = NullPointerException.class)
	public void toByteArray_NullEncoding() throws UnsupportedEncodingException
	{
		StringUtils.toByteArray("Test", null);
	}
}