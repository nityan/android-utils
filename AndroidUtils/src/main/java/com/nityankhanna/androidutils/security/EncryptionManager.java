package com.nityankhanna.androidutils.security;

import com.nityankhanna.androidutils.StringUtils;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Nityan Khanna on 22/10/13.
 */

/**
 * A utility class for encrypting data.
 */
public class EncryptionManager {

	private static EncryptionManager sharedInstance;
	private Cipher cipher;

	private EncryptionManager() {

		try {
			cipher = Cipher.getInstance("AES");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		}

	}

	public static EncryptionManager getInstance() {

		synchronized (EncryptionManager.class) {

			if (sharedInstance == null) {
				sharedInstance = new EncryptionManager();
			}

			return sharedInstance;
		}
	}

	/**
	 * Encrypts data.
	 *
	 * @param password      The password.
	 * @param dataToEncrypt The data to be encrypted.
	 *
	 * @return Returns the encrypted data as a byte array.
	 */
	public synchronized byte[] encryptData(SecretKeySpec password, byte[] dataToEncrypt) {

		byte[] encryptedData = null;

		try {
			cipher.init(Cipher.ENCRYPT_MODE, password);
			encryptedData = cipher.doFinal(dataToEncrypt);
		} catch (BadPaddingException | InvalidKeyException | IllegalBlockSizeException e) {
			e.printStackTrace();
		}

		return encryptedData;
	}

	/**
	 * Decrypts data.
	 *
	 * @param password      The password. This password must be the same as the password used to encrypt the data.
	 * @param encryptedData The encrypted data to decrypt.
	 *
	 * @return Returns the data as a byte array.
	 */
	public synchronized byte[] decryptData(SecretKeySpec password, byte[] encryptedData) {

		byte[] decryptedData = null;

		try {
			cipher.init(Cipher.DECRYPT_MODE, password);
			decryptedData = cipher.doFinal(encryptedData);
		} catch (BadPaddingException | InvalidKeyException | IllegalBlockSizeException e) {
			e.printStackTrace();
		}

		return decryptedData;
	}

	public synchronized SecretKeySpec createPassword(String password) {

		SecretKeySpec secretKey = null;

		try {
			MessageDigest digest = MessageDigest.getInstance("SHA");
			digest.update(StringUtils.toByteArray(password));
			secretKey = new SecretKeySpec(digest.digest(), 0, 16, "AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return secretKey;
	}
}