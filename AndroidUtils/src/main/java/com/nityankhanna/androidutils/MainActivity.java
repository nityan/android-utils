package com.nityankhanna.androidutils;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.nityankhanna.androidutils.security.EncryptionManager;
import com.nityankhanna.androidutils.system.ThreadPool;
import com.nityankhanna.androidutils.ui.TextViewField;

/**
 * Created by Nityan Khanna on 22/10/13.
 */
public class MainActivity extends Activity implements View.OnClickListener {

	private EncryptionManager encryptionManager;
	private byte[] temp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		encryptionManager = EncryptionManager.getInstance();

		Button button = (Button) findViewById(R.id.button);
		Button button2 = (Button) findViewById(R.id.button2);

		button.setOnClickListener(this);
		button2.setOnClickListener(this);

		TextViewField textViewField = (TextViewField) findViewById(R.id.text_view_field);

		new TestTask().executeOnExecutor(ThreadPool.EXECUTOR);

		Log.d("DEBUG", textViewField.getTextAsString());
	}

	@Override
	public void onClick(View view) {

		switch (view.getId()) {

			case R.id.button:
				temp = encryptionManager.encryptData("password", "Nityan".getBytes());
				Log.e("DEBUG", new String(temp));

				break;

			case R.id.button2:
				try {
					byte[] temp2 = encryptionManager.decryptData("password", temp);

					Log.e("DEBUG", new String(temp2));
				} catch (Exception ex) {
					ex.printStackTrace();

				}

				break;

			default:
				break;
		}
	}

	private class TestTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... voids) {
			return null;
		}
	}
}