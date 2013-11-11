package com.nityankhanna.androidutils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.nityankhanna.androidutils.security.HashManager;
import com.nityankhanna.androidutils.security.HashType;
import com.nityankhanna.androidutils.system.ThreadPool;

import java.security.NoSuchAlgorithmException;

/**
 * Created by Nityan Khanna on 22/10/13.
 */
public class MainActivity extends Activity implements View.OnClickListener {

	ThreadPool threadPool = ThreadPool.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button button = (Button) findViewById(R.id.button);

		button.setOnClickListener(this);

		HashManager hashManager = HashManager.getInstance();

		try {
			Log.e(Constants.DEBUG, hashManager.hashString("password", HashType.SHA1));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View view) {

		switch (view.getId()) {

			case R.id.button:

				threadPool.queueWorkerItem(new Runnable() {

					@Override
					public void run() {
						if (!threadPool.isCurrentThreadMain()) {
							Log.e(Constants.DEBUG, "Background thread");
						}
					}
				});
				threadPool.runOnUiThread(new Runnable() {

					@Override
					public void run() {

						if (threadPool.isCurrentThreadMain()) {
							Log.e(Constants.DEBUG, "UI thread");
						}
					}
				});
				break;

			default:
				break;
		}
	}
}
