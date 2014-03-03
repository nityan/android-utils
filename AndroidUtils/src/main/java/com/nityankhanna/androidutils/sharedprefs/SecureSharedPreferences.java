package com.nityankhanna.androidutils.sharedprefs;

import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Nityan on 02/03/14.
 */
public interface SecureSharedPreferences {

	boolean getSecureBooleanForKey(String key);

	void setSecureBooleanForKey(String key, boolean value);

	float getSecureFloatForKey(String key);

	void setSecureFloatForKey(String key, float value);

	int getSecureIntForKey(String key);

	void setSecureIntForKey(String key, int value);

	long getSecureLongForKey(String key);

	void setSecureLongForKey(String key, long value);

	public String getSecureStringForKey(String key);

	void setSecureStringForKey(String key, String value);

	void setPassword(SecretKeySpec password);
}
