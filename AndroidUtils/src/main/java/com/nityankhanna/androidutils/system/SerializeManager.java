package com.nityankhanna.androidutils.system;

import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Nityan Khanna on Dec 16 2013.
 */
public class SerializeManager {

	private static SerializeManager sharedInstance;

	private SerializeManager() {
	}

	public static SerializeManager getInstance() {

		synchronized (SerializeManager.class) {

			if (sharedInstance == null) {
				sharedInstance = new SerializeManager();
			}

			return sharedInstance;
		}
	}

	public byte[] serialize(Object object) {

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
