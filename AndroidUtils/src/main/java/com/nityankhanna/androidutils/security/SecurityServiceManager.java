package com.nityankhanna.androidutils.security;

import android.util.Base64;
import android.util.Log;

import com.nityankhanna.androidutils.Constants;
import com.nityankhanna.androidutils.InvalidArgumentException;
import com.nityankhanna.androidutils.ValueNotSetException;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Created by Nityan Khanna on 08/08/13.
 */

/**
 * A class containing services for encoding and encrypting data.
 */
public class SecurityServiceManager {

	private Cipher cipher;
	private PublicKey publicKey;
	private PrivateKey privateKey;
	private OnEncryptionListener encryptionListener;
	private OnEncodingListener encodingListener;
	private KeyPair keyPair;

	public SecurityServiceManager() {

		try {

			cipher = Cipher.getInstance("RSA");

			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
			keyGen.initialize(2048);
			this.keyPair = keyGen.genKeyPair();

		} catch (NoSuchAlgorithmException e) {
			Log.d(Constants.DEBUG, e.getMessage());
		} catch (NoSuchPaddingException e) {
			Log.d(Constants.DEBUG, e.getMessage());
		}
	}

	/**
	 * Returns the public key.
	 *
	 * @return Returns the public key.
	 */
	public PublicKey getPublicKey() {
		return publicKey;
	}

	/**
	 * Sets the public key.
	 *
	 * @param publicKey The public key.
	 */
	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}

	/**
	 * Returns the private key.
	 *
	 * @return Returns the private key.
	 */
	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	/**
	 * Sets the private key.
	 *
	 * @param privateKey The private key.
	 */
	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}

	/**
	 * Sets the encryption listener.
	 *
	 * @param encryptionListener The encryption listener to set.
	 */
	public void setEncryptionListener(OnEncryptionListener encryptionListener) {
		this.encryptionListener = encryptionListener;
	}

	/**
	 * Sets the encoding listener.
	 *
	 * @param encodingListener The encoding listener to set.
	 */
	public void setEncodingListener(OnEncodingListener encodingListener) {
		this.encodingListener = encodingListener;
	}

	/**
	 * Generate a Public-Private key pair.
	 *
	 * @return Returns a HashMap containing the public and private key.
	 */
	public HashMap<PublicKey, PrivateKey> generateKeyPair() {
		HashMap<PublicKey, PrivateKey> map = new HashMap<PublicKey, PrivateKey>();

		publicKey = keyPair.getPublic();
		privateKey = keyPair.getPrivate();

		map.put(publicKey, privateKey);

		return map;
	}

	/**
	 * Encodes a byte array using Base64.
	 *
	 * @param data The string to be encoded.
	 * @throws ValueNotSetException
	 */
	public void encodeDataUsingBase64(byte[] data) throws ValueNotSetException {

		if (encodingListener == null) {
			throw new ValueNotSetException("The encoding listener must be set.");
		}

		encodingListener.onEncodingCompleted(Base64.encode(data, Base64.DEFAULT));
	}

	/**
	 * Decodes a byte array using Base64.
	 *
	 * @param data The data to be decoded.
	 * @throws ValueNotSetException
	 */
	public void decodeDataUsingBase64(byte[] data) throws ValueNotSetException {

		if (encodingListener == null) {
			throw new ValueNotSetException("The encoding listener must be set.");
		}

		encodingListener.onDecodingCompleted(Base64.decode(data, Base64.DEFAULT));
	}

	/**
	 * Encrypts data.
	 *
	 * @param dataToEncrypt The data to encrypt.
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public void encryptData(byte[] dataToEncrypt) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

		byte[] encryptedData;

		if (dataToEncrypt.length == 0) {
			throw new InvalidArgumentException("The data to encrypt has a length of 0.");
		}

		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		encryptedData = cipher.doFinal(dataToEncrypt);

		if (encryptionListener == null) {
			throw new ValueNotSetException("The encryption listener must be set.");
		}

		encryptionListener.onEncryptionCompleted(encryptedData);
	}

	/**
	 * Decrypts data.
	 *
	 * @param encryptedData The encrypted data to decrypt.
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public void decryptData(byte[] encryptedData) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

		byte[] decryptedData;

		if (encryptedData.length == 0) {
			throw new InvalidArgumentException("The encrypted data has a length of 0.");
		}

		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		decryptedData = (cipher.doFinal(encryptedData));

		if (encryptionListener == null) {
			throw new ValueNotSetException("The encryption listener must be set.");
		}

		encryptionListener.onDecryptionCompleted(decryptedData);
	}
}
