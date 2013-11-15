package com.nityankhanna.androidutils.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Nityan Khanna on 11/11/13.
 * Copyright (c) 2013 NAATec. All rights reserved.
 */
public class HashManager {

	private static HashManager sharedInstance;

	private HashManager() {
	}

	/**
	 * Returns an instance of the HashManager class.
	 *
	 * @return Returns an instance of the HashManager class.
	 */
	public static HashManager getInstance() {

		synchronized (HashManager.class) {

			if (sharedInstance == null) {
				sharedInstance = new HashManager();
			}

			return sharedInstance;
		}
	}

	/**
	 * Hashes a string and returns the hashed string.
	 *
	 * @param value The string to hash.
	 * @param type  The type of algorithm to use
	 *
	 * @return Returns the hashed string.
	 *
	 */
	public String hashString(String value, HashType type) {

		if (value == null || value.isEmpty()) {
			throw new IllegalArgumentException("The string parameter cannot be null or empty");
		}

		try {
			MessageDigest digest = MessageDigest.getInstance(type.toString());
			digest.update(value.getBytes());
			return new String(digest.digest());

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return null;
	}
}
