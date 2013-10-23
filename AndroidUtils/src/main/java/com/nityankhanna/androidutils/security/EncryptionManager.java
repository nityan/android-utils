package com.nityankhanna.androidutils.security;

import android.util.Log;

import com.nityankhanna.androidutils.Constants;
import com.nityankhanna.androidutils.system.ThreadPool;

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
public class EncryptionManager {

	private Cipher cipher;
	private PublicKey publicKey;
	private PrivateKey privateKey;
	private OnEncryptionListener listener;
	private ThreadPool threadPool;

	public EncryptionManager(OnEncryptionListener listener) {

		try {

			this.listener = listener;
			threadPool = ThreadPool.getInstance();

			cipher = Cipher.getInstance("RSA");

			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
			keyGen.initialize(2048);

			KeyPair keyPair = keyGen.genKeyPair();

			publicKey = keyPair.getPublic();
			privateKey = keyPair.getPrivate();

		} catch (NoSuchAlgorithmException e) {
			Log.d(Constants.DEBUG, e.getMessage());
		} catch (NoSuchPaddingException e) {
			Log.d(Constants.DEBUG, e.getMessage());
		}
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}

	/**
	 * Encrypts data.
	 *
	 * @param dataToEncrypt The data to encrypt.
	 */
	public void encryptData(final byte[] dataToEncrypt) {

		threadPool.queueWorkerItem(new Runnable() {

			@Override
			public void run() {

				byte[] encryptedData = null;

				try {
					cipher.init(Cipher.ENCRYPT_MODE, publicKey);
					encryptedData = cipher.doFinal(dataToEncrypt);
				} catch (InvalidKeyException e) {
					e.printStackTrace();
				} catch (BadPaddingException e) {
					e.printStackTrace();
				} catch (IllegalBlockSizeException e) {
					e.printStackTrace();
				}


				listener.onEncryptionCompleted(encryptedData);

			}
		});
	}

	/**
	 * Decrypts data.
	 *
	 * @param encryptedData The encrypted data to decrypt.
	 *
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public void decryptData(final byte[] encryptedData) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

		threadPool.queueWorkerItem(new Runnable() {

			@Override
			public void run() {

				byte[] decryptedData = null;

				try {
					cipher.init(Cipher.DECRYPT_MODE, privateKey);
					decryptedData = (cipher.doFinal(encryptedData));
				} catch (InvalidKeyException e) {
					e.printStackTrace();
				} catch (BadPaddingException e) {
					e.printStackTrace();
				} catch (IllegalBlockSizeException e) {
					e.printStackTrace();
				}

				listener.onDecryptionCompleted(decryptedData);
			}
		});

	}
}