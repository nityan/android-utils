package com.nityankhanna.androidutils.security;

/**
 * Created by Nityan Khanna on 11/11/13.
 */

/**
 * Represents a collection of different algorithms used for hashing.
 */
public enum HashType {

	/**
	 * MD5 algorithm.
	 */
	MD5,

	/**
	 * SHA-1 algorithm.
	 */
	SHA1,

	/**
	 * SHA-256 algorithm.
	 */
	SHA256;

	private HashType() {
	}

	@Override
	public String toString() {

		String value;

		switch (this) {

			case MD5:
				value = "MD5";
				break;

			case SHA1:
				value = "SHA-1";
				break;

			case SHA256:
				value = "SHA-256";
				break;

			default:
				value = null;
				break;
		}

		return value;
	}
}
