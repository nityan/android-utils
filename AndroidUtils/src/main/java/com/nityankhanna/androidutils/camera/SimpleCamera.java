package com.nityankhanna.androidutils.camera;

/**
 * Created by Nityan Khanna on 14/11/13.
 * Copyright (c) 2013 NAATec. All rights reserved.
 */

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;

/**
 * A simple camera class.
 */
public class SimpleCamera implements Camera.PictureCallback, Camera.ShutterCallback {

	private Context context;
	private Camera camera;
	private int cameraId = 0;
	private CameraCallback cameraCallback;

	public SimpleCamera(Context context, CameraCallback cameraCallback) {
		this.context = context;

		if (cameraCallback == null) {
			throw new IllegalArgumentException("The cameraCallback parameter cannot be null");
		}

		this.cameraCallback = cameraCallback;

		PackageManager packageManager = this.context.getPackageManager();

		cameraId = findFrontFacingCamera();

		if (!packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA) && cameraId < 0) {
			throw new CameraUnavailableException("This device does not have a camera");
		}
	}

	public void takePicture() {
		camera = Camera.open(cameraId);

		camera.takePicture(this, this, this, this);
	}

	@Override
	public void onPictureTaken(byte[] data, Camera camera) {
		Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

		if (bitmap == null) {
			cameraCallback.onPictureTakenFailure();
		} else {
			cameraCallback.onPictureTaken(bitmap);
		}
	}

	@Override
	public void onShutter() {

	}

	private int findFrontFacingCamera() {

		int cameraId = -1;
		// Search for the front facing camera
		int numberOfCameras = Camera.getNumberOfCameras();

		for (int i = 0; i < numberOfCameras; i++) {
			Camera.CameraInfo info = new Camera.CameraInfo();

			Camera.getCameraInfo(i, info);

			if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
				cameraId = i;
				break;
			}
		}

		return cameraId;
	}
}
