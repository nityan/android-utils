package com.nityankhanna.androidutils.system.serialization;

import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by Nityan Khanna on Dec 16 2013.
 */
public class SerializeManager {

	private static SerializeManager sharedInstance;

	private SerializeManager() {
	}

	/**
	 * Returns a shared instance of the SerializeManager class.
	 *
	 * @return Returns a shared instance of the SerializeManager class.
	 */
	public static SerializeManager getInstance() {

		synchronized (SerializeManager.class) {

			if (sharedInstance == null) {
				sharedInstance = new SerializeManager();
			}

			return sharedInstance;
		}
	}

	/**
	 * Serializes an object to a byte array.
	 *
	 * @param object The object to be serialized.
	 *
	 * @return Returns a byte array.
	 */
	public byte[] serialize(Object object) {

		if (!(object instanceof Serializable)) {
			throw new IllegalArgumentException("Class" + object.getClass().getName() + " must implement java.io.Serializable to be serialized");
		}

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = null;

		try {
			objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(object);
		} catch (IOException ex) {
			Log.e("ERROR", "Unable to close the output stream");
			ex.printStackTrace();
		} finally {
			try {
				byteArrayOutputStream.close();
				objectOutputStream.close();
			} catch (IOException e) {
				Log.e("ERROR", "Unable to close the output stream");
				e.printStackTrace();
			}
		}

		return byteArrayOutputStream.toByteArray();
	}

	/**
	 * Deserializes a byte array to an object.
	 *
	 * @param data The data to be recreated into an object.
	 *
	 * @return Returns the object.
	 */
	public Object deserialize(byte[] data) {

		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
		ObjectInputStream objectInputStream = null;

		try {
			objectInputStream = new ObjectInputStream(byteArrayInputStream);
			return objectInputStream.readObject();
		} catch (IOException ex) {
			Log.e("ERROR", "Unable to close the output stream");
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			Log.e("ERROR", "Class not found exception.");
			e.printStackTrace();
		} finally {
			try {
				byteArrayInputStream.close();
				objectInputStream.close();
			} catch (IOException e) {
				Log.e("ERROR", "Unable to close the output stream");
				e.printStackTrace();
			}
		}

		return null;
	}
}
