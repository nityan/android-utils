package com.nityankhanna.tests;

import android.util.Log;

import junit.framework.TestCase;

import com.nityankhanna.androidutils.StringUtils;
import com.nityankhanna.androidutils.security.EncryptionManager;

import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Nityan Khanna on Feb 15 2014.
 */
public class EncryptionManagerTest extends TestCase {

	private static final EncryptionManager ENCRYPTION_MANAGER = EncryptionManager.getInstance();
	private final String className = getClass().getSimpleName();

	public EncryptionManagerTest() {
	}

	public void testEncryption() throws Exception {

		SecretKeySpec password = ENCRYPTION_MANAGER.createPassword("password");

		byte[] encryptedData = ENCRYPTION_MANAGER.encryptData(password, StringUtils.toByteArray("Nityan Khanna"));

		assertNotNull("The encrypted data is null", encryptedData);

		Log.e(className, "Encrypted data: " + StringUtils.toString(encryptedData));

		byte[] decryptedData = ENCRYPTION_MANAGER.decryptData(password, encryptedData);

		assertNotNull("The decrypted data is null", decryptedData);

		Log.e(className, "Decrypted data: " + StringUtils.toString(decryptedData));
	}
}
