package com.nityankhanna.androidutils.system;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.nityankhanna.androidutils.Constants;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Nityan Khanna on 11/10/13.
 */

/**
 * A utility class for file operations.
 */
public class FileManager {

	/**
	 * Reads a raw file from the android resources.
	 *
	 * @param context    The application context.
	 * @param resourceId The id of the resource to find.
	 *
	 * @return Returns a string containing the text from the file.
	 *
	 * @throws IOException
	 */
	public static String readRawFileFromResources(Context context, int resourceId) throws IOException {

		InputStream inputStream = context.getResources().openRawResource(resourceId);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

		String line;
		StringBuilder text = new StringBuilder();

		while ((line = bufferedReader.readLine()) != null) {
			text.append(line);
			text.append('\n');
		}

		return text.toString();
	}

	/**
	 * Writes an object to a file.
	 *
	 * @param context  The application context.
	 * @param object   The object to write to the file.
	 * @param filename The The name of the file to write to.
	 * @param mode     The mode in which to write the file.
	 */
	public static void writeObjectToFile(Context context, Object object, String filename, FileMode mode) throws IOException {

		ObjectOutputStream objectOut = null;
		try {

			FileOutputStream fileOut = context.openFileOutput(filename, mode.getValue());
			objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(object);
			fileOut.getFD().sync();

		} finally {
			try {
				objectOut.close();
			} catch (IOException e) {
				Log.e(Constants.DEBUG, "Unable to close the OutputObjectStream");
			} catch (NullPointerException ex) {
				Log.e(Constants.DEBUG, "Unable to close the OutputObjectStream");
			}
		}
	}

	/**
	 * * Reads an Object from a file.
	 *
	 * @param context  The application context.
	 * @param filename The name of the file to read from.
	 *
	 * @return Returns the object from the file.
	 */
	public static Object readObjectFromFile(Context context, String filename) throws IOException {

		ObjectInputStream objectIn = null;
		Object object = null;
		try {

			FileInputStream fileIn = context.openFileInput(filename);
			objectIn = new ObjectInputStream(fileIn);

			object = objectIn.readObject();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				objectIn.close();
			} catch (IOException e) {
				e.printStackTrace();
				Log.e(Constants.DEBUG, "Unable to close the OutputObjectStream");
			} catch (NullPointerException ex) {
				ex.printStackTrace();
				Log.e(Constants.DEBUG, "Unable to close the OutputObjectStream");
			}
		}

		return object;
	}

	public static boolean canWriteToExternalStorage() {
		return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
	}
}























