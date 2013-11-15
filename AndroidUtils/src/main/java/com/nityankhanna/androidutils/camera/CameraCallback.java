package com.nityankhanna.androidutils.camera;

import android.graphics.Bitmap;

/**
 * Created by Nityan Khanna on 14/11/13.
 */
public interface CameraCallback {

	void onPictureTaken(Bitmap bitmap);
	void onPictureTakenFailure();
}
