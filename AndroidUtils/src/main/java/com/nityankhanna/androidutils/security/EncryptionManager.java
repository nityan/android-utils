package com.nityankhanna.androidutils.security;

import android.util.Log;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import com.nityankhanna.androidutils.StringUtils;

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
	 * @return Returns the encrypted data as a byte array, or null if an error occurs during the encryption process.
	 */
	public synchronized byte[] encryptData(String password, byte[] dataToEncrypt) {

		byte[] encryptedData = null;

		try {

			MessageDigest digest = MessageDigest.getInstance("SHA");
			digest.update(StringUtils.toByteArray(password));

			byte[] hash = digest.digest();
			SecretKeySpec secretKey = new SecretKeySpec(hash, 0, 16, "AES");

			Log.e(getClass().getSimpleName(), "Encoded password: " + StringUtils.toString(hash));
			Log.e(getClass().getSimpleName(), "Encoded password length: " + hash.length);

			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			encryptedData = cipher.doFinal(dataToEncrypt);

		} catch (BadPaddingException | InvalidKeyException | IllegalBlockSizeException |
				NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return encryptedData;
	}

	/**
	 * Decrypts data.
	 *
	 * @param password      The password.
	 * @param encryptedData The encrypted data to decrypt.
	 *
	 * @return Returns the data as a byte array, or null if an exception occurs during the decryption process.
	 */
	public synchronized byte[] decryptData(String password, byte[] encryptedData) {

		byte[] decryptedData = null;

		try {

			MessageDigest digest = MessageDigest.getInstance("SHA");
			digest.update(StringUtils.toByteArray(password));

			byte[] hash = digest.digest();
			SecretKeySpec secretKey = new SecretKeySpec(hash, 0, 16, "AES");

			Log.e(getClass().getSimpleName(), "Encoded password: " + StringUtils.toString(hash));
			Log.e(getClass().getSimpleName(), "Encoded password length: " + hash.length);

			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			decryptedData = cipher.doFinal(encryptedData);

		} catch (BadPaddingException | InvalidKeyException | IllegalBlockSizeException |
				NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return decryptedData;
	}
}