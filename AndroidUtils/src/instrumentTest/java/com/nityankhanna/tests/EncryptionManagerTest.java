package com.nityankhanna.tests;

import android.util.Log;

import junit.framework.TestCase;

import com.nityankhanna.androidutils.StringUtils;
import com.nityankhanna.androidutils.security.EncryptionManager;

/**
 * Created by Nityan Khanna on Feb 15 2014.
 */
public class EncryptionManagerTest extends TestCase {

	private static final EncryptionManager ENCRYPTION_MANAGER = EncryptionManager.getInstance();
	private final String className = getClass().getSimpleName();

	public EncryptionManagerTest() {
	}

	public void testGetInstance() throws Exception {
		Class expectedResult = EncryptionManager.class;

		assertEquals(expectedResult, ENCRYPTION_MANAGER.getClass());
	}

	public void testEncryptData() throws Exception {
		byte[] encryptedData = encryptData();

		assertNotNull("The encrypted data is null", encryptedData);

		Log.e(className, "Encrypted data: " + StringUtils.toString(encryptedData));
	}

	public void testDecryptData() throws Exception {
		byte[] decryptedData = decryptData();

		assertNotNull("The decrypted data is null", decryptedData);

		Log.e(className, "Decrypted data: " + StringUtils.toString(decryptedData));
	}

	private byte[] encryptData() {
		return ENCRYPTION_MANAGER.encryptData("password", StringUtils.toByteArray("Nityan Khanna"));
	}

	private byte[] decryptData() {
		return ENCRYPTION_MANAGER.decryptData("password", encryptData());
	}
}
