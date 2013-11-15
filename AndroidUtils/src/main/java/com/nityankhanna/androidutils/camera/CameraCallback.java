package com.nityankhanna.androidutils.camera;

import android.graphics.Bitmap;

/**
 * Created by Nityan Khanna on 14/11/13.
 * Copyright (c) 2013 NAATec. All rights reserved.
 */
public interface CameraCallback {

	void onPictureTaken(Bitmap bitmap);
	void onPictureTakenFailure();
}
