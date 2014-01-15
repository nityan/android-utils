package com.nityankhanna.androidutils.system.io;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.channels.FileChannel;

/**
 * Created by Nityan Khanna on 11/10/13.
 */

/**
 * A utility class for file operations.
 */
public class FileManager {

	private static FileManager sharedInstance;

	private FileManager() {
	}

	public static FileManager getInstance() {

		synchronized (FileManager.class) {

			if (sharedInstance == null) {
				sharedInstance = new FileManager();
			}
		}

		return sharedInstance;
	}

	/**
	 * Determines if the there is the ability to write to external storage.
	 *
	 * @return Returns true if there is the ability to write to external storage.
	 */
	public boolean canWriteToExternalStorage() {
		return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
	}

	/**
	 * Copies one file to another.
	 *
	 * @param source      The source file.
	 * @param destination The destination file.
	 *
	 * @return Returns the destination file with the copied contents.
	 *
	 * @throws IOException
	 */
	public File copyFile(File source, File destination) throws IOException {

		FileInputStream inputStream = new FileInputStream(source);
		FileOutputStream outputStream = new FileOutputStream(destination);

		FileChannel inChannel = inputStream.getChannel();
		FileChannel outChannel = outputStream.getChannel();

		try {
			inChannel.transferTo(0, inChannel.size(), outChannel);
		} finally {

			if (inChannel != null) {
				inChannel.close();
			}

			if (outChannel != null) {
				outChannel.close();
			}

			inputStream.close();
			outputStream.close();
		}

		return destination;
	}

	/**
	 * Logs an exception to a file.
	 *
	 * @param context   The application context.
	 * @param exception The exception to log to the file.
	 */
	public void logException(Context context, Exception exception) {
		logException(context, exception, null);
	}

	/**
	 * Logs an exception to a file.
	 *
	 * @param context   The application context.
	 * @param exception The exception to log to the file.
	 * @param fileName  The name of the file.
	 */
	public void logException(Context context, Exception exception, String fileName) {

		ObjectOutputStream outputStream = null;
		FileOutputStream fileOutputStream = null;

		try {

			if (fileName == null) {
				fileOutputStream = context.openFileOutput(context.getPackageName() + "-exceptions", Context.MODE_PRIVATE);
			} else {
				fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
			}

			fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
			outputStream = new ObjectOutputStream(fileOutputStream);
			outputStream.writeObject(exception);
			fileOutputStream.getFD().sync();

		} catch (IOException ex) {
			ex.printStackTrace();
			throw new FileWriteException(ex.getMessage());
		} finally {

			try {
				if (outputStream != null) {
					outputStream.close();
				}

				if (fileOutputStream != null) {
					fileOutputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * * Reads an Object from a file.
	 *
	 * @param context  The application context.
	 * @param fileName The name of the file to read from.
	 *
	 * @return Returns the object from the file.
	 */
	public Object readObjectFromFile(Context context, String fileName) {

		ObjectInputStream inputStream = null;
		Object object = null;

		try {

			FileInputStream fileIn = context.openFileInput(fileName);
			inputStream = new ObjectInputStream(fileIn);

			object = inputStream.readObject();

		} catch (IOException ex) {
			ex.printStackTrace();
			throw new FileReadException(ex.getMessage());
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} finally {

			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return object;
	}

	/**
	 * Reads a raw text file from the android resources.
	 *
	 * @param context    The application context.
	 * @param resourceId The id of the resource to find.
	 *
	 * @return Returns a string containing the text from the file.
	 */
	public String readTextFileFromResources(Context context, int resourceId) {

		InputStream inputStream = null;
		BufferedReader bufferedReader = null;

		try {
			inputStream = context.getResources().openRawResource(resourceId);
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

			String line;
			StringBuilder text = new StringBuilder();

			while ((line = bufferedReader.readLine()) != null) {
				text.append(line);
				text.append('\n');
			}

			return text.toString();
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new FileReadException(ex.getMessage());
		} finally {

			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}

				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Writes an object to a file.
	 *
	 * @param context  The application context.
	 * @param object   The object to write to the file.
	 * @param filename The The name of the file to write to.
	 * @param mode     The mode in which to write the file.
	 */
	public void writeObjectToFile(Context context, Object object, String filename, FileMode mode) {

		ObjectOutputStream outputStream = null;
		FileOutputStream fileOutputStream = null;

		try {

			fileOutputStream = context.openFileOutput(filename, mode.getValue());
			outputStream = new ObjectOutputStream(fileOutputStream);
			outputStream.writeObject(object);
			fileOutputStream.getFD().sync();

		} catch (IOException ex) {
			ex.printStackTrace();
			throw new FileWriteException(ex.getMessage());
		} finally {

			try {
				if (outputStream != null) {
					outputStream.close();
				}

				if (fileOutputStream != null) {
					fileOutputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}























