package com.nityankhanna.androidutils;

import android.content.Context;
import android.content.SharedPreferences;

import javax.crypto.spec.SecretKeySpec;

import com.nityankhanna.androidutils.security.EncryptionManager;

/**
 * Created by Nityan Khanna on 28/06/13.
 */

/**
 * A simplified SharedPreferences class.
 */
public class SharedPreferencesService {

	private final SharedPreferences sharedPreferences;
	private final SharedPreferences.Editor editor;

	/**
	 * Initializes a new SharedPreferencesService instance with a specified context.
	 *
	 * @param context The application context.
	 */
	public SharedPreferencesService(Context context, String filename) {
		sharedPreferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
		editor = sharedPreferences.edit();
		editor.commit();
	}

	public SharedPreferencesService(Context context, String filename, int mode) {
		sharedPreferences = context.getSharedPreferences(filename, mode);
		editor = sharedPreferences.edit();
		editor.commit();
	}

	public SharedPreferencesService(Context context, String filename, SharedPreferences.OnSharedPreferenceChangeListener delegate) {
		sharedPreferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
		sharedPreferences.registerOnSharedPreferenceChangeListener(delegate);
		editor = sharedPreferences.edit();
		editor.commit();
	}

	public SharedPreferencesService(Context context, String filename, int mode, SharedPreferences.OnSharedPreferenceChangeListener delegate) {
		sharedPreferences = context.getSharedPreferences(filename, mode);
		sharedPreferences.registerOnSharedPreferenceChangeListener(delegate);
		editor = sharedPreferences.edit();
		editor.commit();
	}

	/**
	 * Returns a boolean from the SharedPreferences for the specified key.
	 *
	 * @param key The key for an associated value.
	 *
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
	 *
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
	 *
	 * @return Returns 9000 if the preference does not exist.
	 */
	public float getFloatForKey(String key) {
		return sharedPreferences.getFloat(key, 9000);
	}

	/**
	 * Returns a int from the SharedPreferences for the specified key.
	 *
	 * @param key The key for an associated value.
	 *
	 * @return Returns 9000 if the preference does not exist.
	 */
	public int getIntForKey(String key) {
		return sharedPreferences.getInt(key, 9000);
	}

	/**
	 * Returns a long from the SharedPreferences for the specified key.
	 *
	 * @param key The key for an associated value.
	 *
	 * @return Returns 9000 if the preference does not exist.
	 */
	public long getLongForKey(String key) {
		return sharedPreferences.getLong(key, 9000);
	}

	/**
	 * Returns a String from the SharedPreferences for the specified key.
	 *
	 * @param key The key for an associated value.
	 *
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
		editor.putBoolean(key, value).commit();
	}

	/**
	 * Sets a byte array for a specified key
	 *
	 * @param key   The key.
	 * @param value The value.
	 */
	public void setByteArrayForKey(String key, byte[] value) {
		editor.putString(key, new String(value)).commit();
	}

	/**
	 * Sets a float for a specified key
	 *
	 * @param key   The key.
	 * @param value The value.
	 */
	public void setFloatForKey(String key, float value) {
		editor.putFloat(key, value).commit();
	}

	/**
	 * Sets a int for a specified key
	 *
	 * @param key   The key.
	 * @param value The value.
	 */
	public void setIntForKey(String key, int value) {
		editor.putInt(key, value).commit();
	}

	/**
	 * Sets a long for a specified key.
	 *
	 * @param key   The key.
	 * @param value The value.
	 */
	public void setLongForKey(String key, long value) {
		editor.putLong(key, value).commit();
	}

	/**
	 * Sets a String for a specified key
	 *
	 * @param key   The key.
	 * @param value The value.
	 */
	public void setStringForKey(String key, String value) {
		editor.putString(key, value).commit();
	}
}
