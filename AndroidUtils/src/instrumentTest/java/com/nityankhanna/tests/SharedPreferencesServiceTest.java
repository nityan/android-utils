package com.nityankhanna.tests;

import android.test.AndroidTestCase;
import android.util.Log;

import com.nityankhanna.androidutils.sharedprefs.SharedPreferencesService;
import com.nityankhanna.androidutils.security.EncryptionManager;

import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Nityan Khanna on Feb 15 2014.
 */
public class SharedPreferencesServiceTest extends AndroidTestCase {

	private SharedPreferencesService sharedPreferencesService;
	private EncryptionManager encryptionManager = EncryptionManager.getInstance();

	public SharedPreferencesServiceTest() {
	}

	@Override
	public void setUp() throws Exception {
		super.setUp();

		assertNotNull(getContext());
		sharedPreferencesService = new SharedPreferencesService(getContext());
		SecretKeySpec password = encryptionManager.createPassword("password");
		sharedPreferencesService.setPassword(password);
	}

	public void testSecureSharedPreferences() throws Exception {
		sharedPreferencesService.setSecureStringForKey("string", "Nityan");
		sharedPreferencesService.setSecureBooleanForKey("boolean", true);
		sharedPreferencesService.setSecureFloatForKey("float", 12.5f);
		sharedPreferencesService.setSecureIntForKey("int", 100);
		sharedPreferencesService.setSecureLongForKey("long", 100000L);

		Log.e("DEBUG", String.valueOf(sharedPreferencesService.getSecureStringForKey("string")));
		Log.e("DEBUG", String.valueOf(sharedPreferencesService.getSecureBooleanForKey("boolean")));
		Log.e("DEBUG", String.valueOf(sharedPreferencesService.getSecureFloatForKey("float")));
		Log.e("DEBUG", String.valueOf(sharedPreferencesService.getSecureIntForKey("int")));
		Log.e("DEBUG", String.valueOf(sharedPreferencesService.getSecureLongForKey("long")));

		Log.e("DEBUG", String.valueOf(sharedPreferencesService.getStringForKey("string")));
		Log.e("DEBUG", String.valueOf(sharedPreferencesService.getStringForKey("boolean")));
		Log.e("DEBUG", String.valueOf(sharedPreferencesService.getStringForKey("float")));
		Log.e("DEBUG", String.valueOf(sharedPreferencesService.getStringForKey("int")));
		Log.e("DEBUG", String.valueOf(sharedPreferencesService.getStringForKey("long")));
	}
}
