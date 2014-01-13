package com.nityankhanna.androidutils.security;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
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
	private IvParameterSpec iv;

	private EncryptionManager() {

		try {

			cipher = Cipher.getInstance("AES");
			//cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "BC");

			SecureRandom random = new SecureRandom();

			byte[] data = new byte[16];

			random.nextBytes(data);

			iv = new IvParameterSpec(data);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
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

			String encodedPassword = Base64.encodeToString(password.getBytes("UTF-8"), Base64.DEFAULT);

			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			KeySpec spec = new PBEKeySpec(encodedPassword.toCharArray(), generateSalt(), 64, 256);
			Key secret = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");

			cipher.init(Cipher.ENCRYPT_MODE, secret);
			encryptedData = cipher.doFinal(dataToEncrypt);

		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
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

		// TODO: implement the decryptData() method
		throw new UnsupportedOperationException("This method has not been implemented yet");

		/*
		byte[] decryptedData = null;

		try {

			String encodedPassword = Base64.encodeToString(password.getBytes("UTF-8"), Base64.DEFAULT);

			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			KeySpec spec = new PBEKeySpec(encodedPassword.toCharArray(), generateSalt(), 64, 256);
			Key secret = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");

			cipher.init(Cipher.DECRYPT_MODE, secret, iv);
			decryptedData = cipher.doFinal(encryptedData);

		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}

		return decryptedData;
		*/
	}

	private byte[] generateSalt() {

		Random random = new SecureRandom();

		byte[] salt = new byte[16];

		random.nextBytes(salt);

		return salt;
	}

	private IvParameterSpec generateIV() {

		SecureRandom random = new SecureRandom();

		byte[] iv = new byte[cipher.getBlockSize()];

		random.nextBytes(iv);

		return new IvParameterSpec(iv);
	}
}