package com.nityankhanna.androidutils.security;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
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
	private PublicKey publicKey;
	private PrivateKey privateKey;
	private KeyPair keyPair;

	private EncryptionManager() {

		try {

			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

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
	 * Gets the PublicKey.
	 *
	 * @return Returns the PublicKey.
	 */
	public byte[] getPublicKey() {
		return publicKey.getEncoded();
	}

	/**
	 * Gets the PrivateKey.
	 *
	 * @return Returns the PrivateKey.
	 */
	public byte[] getPrivateKey() {
		return privateKey.getEncoded();
	}

	/**
	 * Generates a PublicKey.
	 */
	public byte[] generatePublicKey() {
		publicKey = keyPair.getPublic();
		return publicKey.getEncoded();
	}

	/**
	 * Generates a PrivateKey.
	 */
	public byte[] generatePrivateKey() {
		privateKey = keyPair.getPrivate();
		return privateKey.getEncoded();
	}

	/**
	 * Encrypts data.
	 *
	 * @param password      The password.
	 * @param dataToEncrypt The data to be encrypted.
	 *
	 * @return Returns the encrypted data as a byte array.
	 *
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 */
	public byte[] encryptData(String password, byte[] dataToEncrypt) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException, NoSuchAlgorithmException {

		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		KeySpec spec = new PBEKeySpec(password.toCharArray(), generateSalt(), 1024, 256);
		SecretKey secret = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");

		byte[] encryptedData;

		cipher.init(Cipher.ENCRYPT_MODE, secret);
		encryptedData = cipher.doFinal(dataToEncrypt);

		return encryptedData;
	}

	/**
	 * Decrypts data.
	 *
	 * @param password      The password.
	 * @param encryptedData The encrypted data to decrypt.
	 *
	 * @return Returns the data as a byte array.
	 *
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public byte[] decryptData(String password, byte[] encryptedData) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException {

		throw new UnsupportedOperationException("This method is not implemented yet");
		/*

		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		KeySpec spec = new PBEKeySpec(password.toCharArray(), generateSalt(), 1024, 256);
		SecretKey secret = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");

		byte[] decryptedData;

		cipher.init(Cipher.DECRYPT_MODE, secret);
		decryptedData = cipher.doFinal(encryptedData);

		return decryptedData;
		*/
	}

	private byte[] generateSalt() {

		Random random = new SecureRandom();

		byte[] salt = new byte[32];

		random.nextBytes(salt);

		return salt;
	}
}