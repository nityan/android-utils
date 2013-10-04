package com.nityankhanna.androidutils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.nityankhanna.androidutils.async.ThreadPool;
import com.nityankhanna.androidutils.defines.Constants;
import com.nityankhanna.androidutils.enums.RequestType;
import com.nityankhanna.androidutils.exceptions.InvalidArgumentException;
import com.nityankhanna.androidutils.http.HttpClientService;
import com.nityankhanna.androidutils.http.OnHttpResponseListener;
import com.nityankhanna.androidutils.models.ErrorResponse;

import org.json.JSONArray;

import java.io.IOException;
import java.net.URISyntaxException;

public class MainActivity extends Activity implements View.OnClickListener, OnHttpResponseListener {

	private static ThreadPool threadPool;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		threadPool = ThreadPool.getInstance();

		Button button = (Button) findViewById(R.id.button_execute_request);
		button.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View view) {

		switch (view.getId()) {

			case R.id.button_execute_request:

				try {

					HttpClientService clientService = new HttpClientService("https://thefeed.azurewebsites.net/api/facebook/friends?accessToken=", RequestType.GET, this);
					clientService.executeRequest();

				} catch (URISyntaxException e) {
					e.printStackTrace();
				} catch (InvalidArgumentException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				break;

			default:
				break;
		}

	}

	@Override
	public void onCompleted(JSONArray response) {

	}

	@Override
	public void onCompletedWithError(ErrorResponse errorResponse) {
		Log.d(Constants.DEBUG, errorResponse.getMessage());
		Log.d(Constants.DEBUG, String.valueOf(errorResponse.getContent()));
	}
}
