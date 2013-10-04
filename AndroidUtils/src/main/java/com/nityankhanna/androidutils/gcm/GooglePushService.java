package com.nityankhanna.androidutils.gcm;

import android.app.Activity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.nityankhanna.androidutils.defines.Constants;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Nityan Khanna on 03/10/13.
 */
public class GooglePushService {

	private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
	GoogleCloudMessaging gcm;
	AtomicInteger messageId = new AtomicInteger();
	private Activity activity;

	public GooglePushService(Activity activity) {
		this.activity = activity;
	}

	/**
	 * Check the device to make sure it has the Google Play Services APK
	 *
	 * @return Returns true if the device has the GooglePlayServices APK.
	 */
	public boolean checkPlayServices() {

		int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity);

		if (resultCode != ConnectionResult.SUCCESS && GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
			GooglePlayServicesUtil.getErrorDialog(resultCode, activity, PLAY_SERVICES_RESOLUTION_REQUEST).show();
		} else {
			Log.d(Constants.DEBUG, "This device is not supported.");
			activity.finish();
			return false;
		}

		return true;
	}

	public void registerForGCM() {

	}
}
