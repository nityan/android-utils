package com.nityankhanna.androidutils.system;

import android.content.Context;
import android.content.ContextWrapper;

/**
 * Created by Nityan Khanna on 28/06/13.
 */

/**
 * A simplified SharedPreferences class.
 */
public class SharedPreferences extends ContextWrapper {

	private static SharedPreferences sharedInstance;
	private android.content.SharedPreferences sharedPreferences;
	private android.content.SharedPreferences.Editor editor;

	/**
	 * Initializes a new SharedPreferences instance with a specified context.
	 *
	 * @param context The application context.
	 */
	private SharedPreferences(Context context) {
		super(context);

		sharedPreferences = context.getSharedPreferences("3F2504E0-4F89-11D3-9A0C-0305E82C3301", MODE_PRIVATE);
		editor = sharedPreferences.edit();
		editor.commit();
	}

	/**
	 * Returns a shared instance of the SharedPreferences class.
	 *
	 * @param context The application context.
	 * @return Returns a shared instance of the SharedPreferences class.
	 */
	public static SharedPreferences getInstance(Context context) {

		synchronized (SharedPreferences.class) {

			if (sharedInstance == null) {
				sharedInstance = new SharedPreferences(context);
			}
		}

		return sharedInstance;
	}

	/**
	 * Returns a boolean from the SharedPreferences for the specified key.
	 *
	 * @param key The key for an associated value.
	 * @return Returns the value for an associated key.
	 * Returns false if the preferences does not exist.
	 */
	public boolean getBooleanForKey(String key) {
		return sharedPreferences.getBoolean(key, false);
	}

	/**
	 * Returns a byte array from the SharedPreferences for the specified key.
	 *
	 * @param key The key for an associated value.
	 * @return Returns null if the preference does not exist.
	 */
	public byte[] getByteArrayForKey(String key) {
		String value = sharedPreferences.getString(key, null);

		if (value == null) {
			return null;
		}

		return value.getBytes();
	}

	/**
	 * Returns a float from the SharedPreferences for the specified key.
	 *
	 * @param key The key for an associated value.
	 * @return Returns 9000 if the preference does not exist.
	 */
	public float getFloatForKey(String key) {
		return sharedPreferences.getFloat(key, 9000);
	}

	/**
	 * Returns a int from the SharedPreferences for the specified key.
	 *
	 * @param key The key for an associated value.
	 * @return Returns 9000 if the preference does not exist.
	 */
	public int getIntForKey(String key) {
		return sharedPreferences.getInt(key, 9000);
	}

	/**
	 * Returns a String from the SharedPreferences for the specified key.
	 *
	 * @param key The key for an associated value.
	 * @return Returns null if the preference does not exist.
	 */
	public String getStringForKey(String key) {
		return sharedPreferences.getString(key, null);
	}

	/**
	 * Sets a boolean for a specified key
	 *
	 * @param key   The key.
	 * @param value The value.
	 */
	public void setBooleanForKey(String key, boolean value) {
		editor.putBoolean(key, value);
		saveChanges();
	}

	/**
	 * Sets a byte array for a specified key
	 *
	 * @param key   The key.
	 * @param value The value.
	 */
	public void setByteArrayForKey(String key, byte[] value) {
		editor.putString(key, new String(value));
		saveChanges();
	}

	/**
	 * Sets a float for a specified key
	 *
	 * @param key   The key.
	 * @param value The value.
	 */
	public void setFloatForKey(String key, float value) {
		editor.putFloat(key, value);
		saveChanges();
	}

	/**
	 * Sets a int for a specified key
	 *
	 * @param key   The key.
	 * @param value The value.
	 */
	public void setIntForKey(String key, int value) {
		editor.putInt(key, value);
		saveChanges();
	}

	/**
	 * Sets a String for a specified key
	 *
	 * @param key   The key.
	 * @param value The value.
	 */
	public void setStringForKey(String key, String value) {
		editor.putString(key, value);
		saveChanges();
	}

	/**
	 * Saves the changes made to the Shared Preferences.
	 */
	private void saveChanges() {
		editor.commit();
	}
}
