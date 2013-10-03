package com.nityankhanna.androidutils.security;

/**
 * Created by Nityan Khanna on 02/09/13.
 */

/**
 * An interface containing methods to handle encryption and decryption.
 */
public interface OnEncryptionListener {

	/**
	 * Called when encrypting data is completed.
	 *
	 * @param encryptedData The encrypted data.
	 */
	void onEncryptionCompleted(final byte[] encryptedData);

	/**
	 * Called when decrypting data is completed.
	 *
	 * @param decryptedData The decrypted data.
	 */
	void onDecryptionCompleted(final byte[] decryptedData);
}
