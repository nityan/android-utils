package com.nityankhanna.androidutils.system.serialization;

import com.nityankhanna.androidutils.StringUtils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.util.UUID;

/**
 * Created by Nityan on 2014-08-10.
 */
public class SerializeManagerTest
{
	private SerializeManager serializeManager;

	@Before
	public void setUp() throws IOException
	{
		serializeManager = SerializeManager.getInstance();
	}

	@After
	public void tearDown()
	{
		serializeManager = null;
	}

	@Test(expected = NullPointerException.class)
	public void deserialize_NullData() throws IOException, ClassNotFoundException
	{
		serializeManager.deserialize(StringUtils.toByteArray(null));
	}

	@Test(expected = StreamCorruptedException.class)
	public void deserialize_UnserializedData() throws IOException, ClassNotFoundException
	{
		serializeManager.deserialize(StringUtils.toByteArray(UUID.randomUUID().toString()));
	}

	@Test(expected = NotSerializableException.class)
	public void serialize_NonSerializableData() throws IOException
	{
		serializeManager.serialize(new Object());
	}

	@Test(expected = NullPointerException.class)
	public void serialize_NullData() throws IOException
	{
		serializeManager.serialize(null);
	}

	@Test
	public void serializeTest_Pass() throws IOException, ClassNotFoundException
	{
		String expected = "Test";

		byte[] serializedData = serializeManager.serialize(expected);

		String actual = (String) serializeManager.deserialize(serializedData);

		Assert.assertEquals(expected, actual);
	}
}
