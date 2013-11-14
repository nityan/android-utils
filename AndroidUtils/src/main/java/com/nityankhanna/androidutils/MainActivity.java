package com.nityankhanna.androidutils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.nityankhanna.androidutils.security.EncryptionManager;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

/**
 * Created by Nityan Khanna on 22/10/13.
 */
public class MainActivity extends Activity implements View.OnClickListener {

	private static EncryptionManager encryptionManager;
	private String temp;
	private String temp2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		encryptionManager = EncryptionManager.getInstance();

		Button button = (Button) findViewById(R.id.button);
		Button button2 = (Button) findViewById(R.id.button2);

		button.setOnClickListener(this);
		button2.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {

		switch (view.getId()) {

			case R.id.button:

				try {
					temp = new String(encryptionManager.encryptData("password", "Nityan".getBytes()));
					Log.e("DEBUG", temp);
				} catch (InvalidKeyException e) {
					e.printStackTrace();
				} catch (BadPaddingException e) {
					e.printStackTrace();
				} catch (IllegalBlockSizeException e) {
					e.printStackTrace();
				} catch (InvalidKeySpecException e) {
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}

				break;

			case R.id.button2:
				try {
					temp2 = new String(encryptionManager.decryptData("password", temp.getBytes()));

					Log.e("DEBUG", temp2);
				} catch (IllegalBlockSizeException e) {
					e.printStackTrace();
				} catch (BadPaddingException e) {
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				} catch (InvalidKeySpecException e) {
					e.printStackTrace();
				} catch (InvalidKeyException e) {
					e.printStackTrace();
				}

				break;

			default:
				break;
		}
	}
}
