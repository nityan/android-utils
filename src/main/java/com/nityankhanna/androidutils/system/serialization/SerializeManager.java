package com.nityankhanna.androidutils.system.serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by Nityan Khanna on Dec 16 2013.
 */

/**
 * Contains utility methods to serialize and deserialize objects.
 */
public class SerializeManager
{
	private static SerializeManager sharedInstance;

	private SerializeManager()
	{
	}

	/**
	 * Returns a shared instance of the SerializeManager class.
	 *
	 * @return Returns a shared instance of the SerializeManager class.
	 */
	public static SerializeManager getInstance()
	{

		synchronized (SerializeManager.class)
		{

			if (sharedInstance == null)
			{
				sharedInstance = new SerializeManager();
			}

			return sharedInstance;
		}
	}

	/**
	 * Serializes an object to a byte array.
	 *
	 * @param object The object to be serialized.
	 * @return Returns a byte array.
	 * @throws IOException
	 */
	public byte[] serialize(Object object) throws IOException
	{

		if (!(object instanceof Serializable))
		{
			throw new IllegalArgumentException("Class" + object.getClass().getName() + " must implement java.io.Serializable to be serialized");
		}

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

		objectOutputStream.writeObject(object);

		return byteArrayOutputStream.toByteArray();
	}

	/**
	 * Deserializes a byte array to an object.
	 *
	 * @param data The data to be recreated into an object.
	 * @return Returns the object.
	 * @throws IOException
	 * @throws ClassCastException
	 */
	public Object deserialize(byte[] data) throws IOException, ClassNotFoundException
	{
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
		ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

		return objectInputStream.readObject();
	}
}
