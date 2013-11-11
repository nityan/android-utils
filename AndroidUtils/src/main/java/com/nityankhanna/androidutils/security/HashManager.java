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

	public static HashManager getInstance() {

		synchronized (HashManager.class) {

			if (sharedInstance == null) {
				sharedInstance = new HashManager();
			}

			return sharedInstance;
		}
	}

	public String hashString(String value, HashType type) throws NoSuchAlgorithmException {

		if (value == null || value.isEmpty()) {
			throw new IllegalArgumentException("The string parameter cannot be null or empty");
		}

		MessageDigest digest = MessageDigest.getInstance(type.toString());

		digest.update(value.getBytes());

		return new String(digest.digest());
	}
}
