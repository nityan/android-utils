package com.nityankhanna.androidutils.sharedprefs;

import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Nityan on 02/03/14.
 */
public interface SecureSharedPreferences {

	/**
	 * Gets a secure boolean for a key.
	 *
	 * @param key The key.
	 * @return Returns a boolean.
	 */
	boolean getSecureBooleanForKey(String key);

	/**
	 * Sets a secure boolean for a key.
	 *
	 * @param key   The key.
	 * @param value The value.
	 */
	void setSecureBooleanForKey(String key, boolean value);

	/**
	 * Gets a secure float for a key.
	 *
	 * @param key The key.
	 * @return Returns a float.
	 */
	float getSecureFloatForKey(String key);

	/**
	 * Sets a secure float for a key.
	 *
	 * @param key   The key.
	 * @param value The value.
	 */
	void setSecureFloatForKey(String key, float value);

	/**
	 * Gets a secure int for a key.
	 *
	 * @param key The key.
	 * @return Returns an int.
	 */
	int getSecureIntForKey(String key);

	/**
	 * Sets a secure int for a key.
	 *
	 * @param key   The key.
	 * @param value The value.
	 */
	void setSecureIntForKey(String key, int value);

	/**
	 * Gets a secure long for a key.
	 *
	 * @param key The key.
	 * @return Returns a long.
	 */
	long getSecureLongForKey(String key);

	/**
	 * Sets a secure long for a key.
	 *
	 * @param key   The key.
	 * @param value The value.
	 */
	void setSecureLongForKey(String key, long value);

	/**
	 * Sets a secure string for a key.
	 *
	 * @param key The key.
	 * @return Returns a string.
	 */
	String getSecureStringForKey(String key);

	/**
	 * Sets a secure string for a key.
	 *
	 * @param key   The key.
	 * @param value The value.
	 */
	void setSecureStringForKey(String key, String value);

	/**
	 * Sets the password to encrypt the shared preferences with.
	 *
	 * @param password The password.
	 */
	void setPassword(SecretKeySpec password);
}
