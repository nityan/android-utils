package com.nityankhanna.androidutils;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.nityankhanna.androidutils.enums.RequestType;
import com.nityankhanna.androidutils.http.HttpClientService;
import com.nityankhanna.androidutils.http.OnHttpResponseListener;
import com.nityankhanna.androidutils.models.ErrorResponse;

import org.apache.http.HttpEntity;

import java.net.URISyntaxException;

public class MainActivity extends Activity implements View.OnClickListener, OnHttpResponseListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

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
					clientService.executeRequestAsync();

				} catch (URISyntaxException e) {
					e.printStackTrace();
				}

				break;

			default:
				break;
		}

	}

	@Override
	public void onGetCompleted(HttpEntity response) {

	}

	@Override
	public void onPostCompleted(HttpEntity response) {

	}

	@Override
	public void onPutCompleted(HttpEntity response) {

	}

	@Override
	public void onDeleteCompleted(HttpEntity response) {

	}

	@Override
	public void onClientError(ErrorResponse response) {

	}

	@Override
	public void onServerError(ErrorResponse response) {

	}
}
