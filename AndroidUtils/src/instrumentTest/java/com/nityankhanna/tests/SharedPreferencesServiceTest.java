package com.nityankhanna.tests;

import android.test.AndroidTestCase;
import android.util.Log;

import com.nityankhanna.androidutils.SharedPreferencesService;
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
	}

	public void testSecureSharedPreferences() throws Exception {

		SecretKeySpec password = encryptionManager.createPassword("password");

		sharedPreferencesService.setSecureStringForKey(password, "test", "Nityan");

		Log.e("DEBUG", String.valueOf(sharedPreferencesService.getSecureStringForKey(password, "test")));
	}
}
