package com.nityankhanna.androidutils.security;

import android.util.Log;

import com.nityankhanna.androidutils.Constants;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

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

			cipher = Cipher.getInstance("RSA");

			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
			keyGen.initialize(2048);

			keyPair = keyGen.genKeyPair();

		} catch (NoSuchAlgorithmException e) {
			Log.d(Constants.DEBUG, e.getMessage());
		} catch (NoSuchPaddingException e) {
			Log.d(Constants.DEBUG, e.getMessage());
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
	public PublicKey getPublicKey() {
		return publicKey;
	}

	/**
	 * Sets the PublicKey.
	 *
	 * @param publicKey The PublicKey.
	 */
	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}

	/**
	 * Gets the PrivateKey.
	 *
	 * @return Returns the PrivateKey.
	 */
	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	/**
	 * Sets the PrivateKey.
	 *
	 * @param privateKey The PrivateKey.
	 */
	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}

	/**
	 * Generates a PublicKey.
	 *
	 * @return Returns the generated public key.
	 */
	public PublicKey generatePublicKey() {
		return keyPair.getPublic();
	}

	/**
	 * Generate a PrivateKey.
	 *
	 * @return Returns the generated private key.
	 */
	public PrivateKey generatePrivateKey() {
		return keyPair.getPrivate();
	}

	/**
	 * Encrypts data.
	 *
	 * @param dataToEncrypt The data to encrypt.
	 *
	 * @return Returns the data as a byte array.
	 *
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public byte[] encryptData(byte[] dataToEncrypt) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

		byte[] encryptedData;
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		encryptedData = cipher.doFinal(dataToEncrypt);

		return encryptedData;
	}

	/**
	 * Decrypts data.
	 *
	 * @param encryptedData The encrypted data to decrypt.
	 *
	 * @return Returns the data as a byte array.
	 *
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public byte[] decryptData(byte[] encryptedData) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

		byte[] decryptedData;


		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		decryptedData = (cipher.doFinal(encryptedData));

		return decryptedData;
	}
}