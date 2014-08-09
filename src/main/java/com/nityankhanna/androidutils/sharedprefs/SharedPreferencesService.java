package com.nityankhanna.androidutils.sharedprefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.nityankhanna.androidutils.StringUtils;
import com.nityankhanna.androidutils.security.EncodingManager;
import com.nityankhanna.androidutils.security.EncryptionManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Nityan Khanna on 28/06/13.
 */

/**
 * A simplified SharedPreferences class.
 */
public class SharedPreferencesService implements SecureSharedPreferences
{

	private final SharedPreferences sharedPreferences;
	private final SharedPreferences.Editor editor;
	private final Context context;

	private static final EncryptionManager ENCRYPTION_MANAGER = EncryptionManager.getInstance();

	/**
	 * Initializes a new SharedPreferencesService instance with a specified context.
	 *
	 * @param context The application context.
	 */
	public SharedPreferencesService(Context context)
	{
		this.context = context;
		sharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
		editor = sharedPreferences.edit();
		editor.commit();
	}

	/**
	 * Returns a boolean from the SharedPreferences for the specified key.
	 *
	 * @param key The key.
	 * @return Returns the value for an associated key.
	 * Returns false if the preferences does not exist.
	 */
	public boolean getBooleanForKey(String key)
	{
		return sharedPreferences.getBoolean(key, false);
	}

	/**
	 * Returns a byte array from the SharedPreferences for the specified key.
	 *
	 * @param key The key.
	 * @return Returns null if the preference does not exist.
	 */
	public byte[] getByteArrayForKey(String key)
	{
		String value = sharedPreferences.getString(key, null);

		if (value == null)
		{
			return null;
		}

		return EncodingManager.getInstance().decodeToByteArray(value.getBytes());
	}

	/**
	 * Returns a float from the SharedPreferences for the specified key.
	 *
	 * @param key The key.
	 * @return Returns 9000 if the preference does not exist.
	 */
	public float getFloatForKey(String key)
	{
		return sharedPreferences.getFloat(key, 9000);
	}

	/**
	 * Returns a int from the SharedPreferences for the specified key.
	 *
	 * @param key The key.
	 * @return Returns 9000 if the preference does not exist.
	 */
	public int getIntForKey(String key)
	{
		return sharedPreferences.getInt(key, 9000);
	}

	/**
	 * Returns a long from the SharedPreferences for the specified key.
	 *
	 * @param key The key.
	 * @return Returns 9000 if the preference does not exist.
	 */
	public long getLongForKey(String key)
	{
		return sharedPreferences.getLong(key, 9000);
	}

	/**
	 * Returns a String from the SharedPreferences for the specified key.
	 *
	 * @param key The key.
	 * @return Returns null if the preference does not exist.
	 */
	public String getStringForKey(String key)
	{
		return sharedPreferences.getString(key, null);
	}

	/**
	 * Sets a boolean for a specified key
	 *
	 * @param key   The key.
	 * @param value The value.
	 */
	public void setBooleanForKey(String key, boolean value)
	{
		editor.putBoolean(key, value).commit();
	}

	/**
	 * Sets a byte array for a specified key
	 *
	 * @param key   The key.
	 * @param value The value.
	 */
	public void setByteArrayForKey(String key, byte[] value)
	{
		editor.putString(key, EncodingManager.getInstance().encodeToString(value)).commit();
	}

	/**
	 * Sets a float for a specified key
	 *
	 * @param key   The key.
	 * @param value The value.
	 */
	public void setFloatForKey(String key, float value)
	{
		editor.putFloat(key, value).commit();
	}

	/**
	 * Sets a int for a specified key
	 *
	 * @param key   The key.
	 * @param value The value.
	 */
	public void setIntForKey(String key, int value)
	{
		editor.putInt(key, value).commit();
	}

	/**
	 * Sets a long for a specified key.
	 *
	 * @param key   The key.
	 * @param value The value.
	 */
	public void setLongForKey(String key, long value)
	{
		editor.putLong(key, value).commit();
	}

	/**
	 * Sets a String for a specified key
	 *
	 * @param key   The key.
	 * @param value The value.
	 */
	public void setStringForKey(String key, String value)
	{
		editor.putString(key, value).commit();
	}

	/**
	 * Gets a secure boolean for a key.
	 *
	 * @param key The key.
	 * @return Returns a boolean.
	 */
	@Override
	public boolean getSecureBooleanForKey(String key)
	{
		return StringUtils.toBoolean(getSecureStringForKey(key));
	}

	/**
	 * Sets a secure boolean for a key.
	 *
	 * @param key   The key.
	 * @param value The value.
	 */
	@Override
	public void setSecureBooleanForKey(String key, boolean value)
	{
		setSecureStringForKey(key, StringUtils.toString(value));
	}

	/**
	 * Gets a secure float for a key.
	 *
	 * @param key The key.
	 * @return Returns a float.
	 */
	@Override
	public float getSecureFloatForKey(String key)
	{
		return StringUtils.toFloat(getSecureStringForKey(key));
	}

	/**
	 * Sets a secure float for a key.
	 *
	 * @param key   The key.
	 * @param value The value.
	 */
	@Override
	public void setSecureFloatForKey(String key, float value)
	{
		setSecureStringForKey(key, StringUtils.toString(value));
	}

	/**
	 * Gets a secure int for a key.
	 *
	 * @param key The key.
	 * @return Returns an int.
	 */
	@Override
	public int getSecureIntForKey(String key)
	{
		return StringUtils.toInt(getSecureStringForKey(key));
	}

	/**
	 * Sets a secure int for a key.
	 *
	 * @param key   The key.
	 * @param value The value.
	 */
	@Override
	public void setSecureIntForKey(String key, int value)
	{
		setSecureStringForKey(key, StringUtils.toString(value));
	}

	/**
	 * Gets a secure long for a key.
	 *
	 * @param key The key.
	 * @return Returns a long.
	 */
	@Override
	public long getSecureLongForKey(String key)
	{
		return StringUtils.toLong(getSecureStringForKey(key));
	}

	/**
	 * Sets a secure long for a key.
	 *
	 * @param key   The key.
	 * @param value The value.
	 */
	@Override
	public void setSecureLongForKey(String key, long value)
	{
		setSecureStringForKey(key, StringUtils.toString(value));
	}

	/**
	 * Sets a secure string for a key.
	 *
	 * @param key The key.
	 * @return Returns a string.
	 */
	@Override
	public String getSecureStringForKey(String key)
	{
		return new String(ENCRYPTION_MANAGER.decryptData(getPassword(), getSecureData(key)));
	}

	/**
	 * Sets a secure string for a key.
	 *
	 * @param key   The key.
	 * @param value The value.
	 */
	@Override
	public void setSecureStringForKey(String key, String value)
	{
		setByteArrayForKey(key, ENCRYPTION_MANAGER.encryptData(getPassword(), StringUtils.toByteArray(value)));
	}

	/**
	 * Sets the password to encrypt the shared preferences with.
	 *
	 * @param password The password.
	 */
	@Override
	public void setPassword(SecretKeySpec password)
	{
		setByteArrayForKey(context.getPackageName(), password.getEncoded());
	}

	/**
	 * Gets the secure data from the shared preferences.
	 *
	 * @param key The key.
	 * @return Returns the secured data.
	 */
	private synchronized byte[] getSecureData(String key)
	{
		return getByteArrayForKey(key);
	}

	/**
	 * Gets the password from the shared preferences.
	 *
	 * @return Returns the password as a SecretKeySpec object.
	 */
	private synchronized SecretKeySpec getPassword()
	{

		SecretKeySpec secretKey = null;

		try
		{
			MessageDigest digest = MessageDigest.getInstance("SHA");
			digest.update(getByteArrayForKey(context.getPackageName()));
			secretKey = new SecretKeySpec(digest.digest(), 0, 16, "AES");
		} catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}

		return secretKey;
	}
}
