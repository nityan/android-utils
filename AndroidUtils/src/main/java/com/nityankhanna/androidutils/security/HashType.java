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
	MD5("MD5"),

	/**
	 * SHA-1 algorithm.
	 */
	SHA1("SHA-1"),

	/**
	 * SHA-256 algorithm.
	 */
	SHA256("SHA-256");

	private String value;

	private HashType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
