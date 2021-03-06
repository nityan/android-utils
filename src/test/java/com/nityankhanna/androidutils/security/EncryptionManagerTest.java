package com.nityankhanna.androidutils.security;

import com.nityankhanna.androidutils.StringUtils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Nityan on 2014-08-09.
 */

public class EncryptionManagerTest
{
	private String data;
	private String password;
	private EncryptionManager encryptionManager;

	@Before
	public void setUp()
	{
		data = "Nityan";
		password = "password";
		encryptionManager = EncryptionManager.getInstance();
	}

	@After
	public void tearDown()
	{
		data = null;
		password = null;
		encryptionManager = null;
	}

	@Test
	public void encryptionManagerTest()
	{
		Assert.assertNotNull(password);

		SecretKeySpec secretKeySpec = encryptionManager.createPassword(password);
		byte[] encryptedData = encryptionManager.encryptData(secretKeySpec, StringUtils.toByteArray(data));

		assert encryptedData != null;

		Assert.assertNotNull(encryptedData);
		
		byte[] decryptedData = encryptionManager.decryptData(secretKeySpec, encryptedData);

		Assert.assertNotNull(decryptedData);
	}
}
