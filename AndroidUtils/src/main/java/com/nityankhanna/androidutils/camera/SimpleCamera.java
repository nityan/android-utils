package com.nityankhanna.androidutils.camera;

/**
 * Created by Nityan Khanna on 14/11/13.
 */

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.provider.MediaStore;

import java.util.List;

/**
 * A simple camera class.
 */
public class SimpleCamera implements Camera.PictureCallback, Camera.ShutterCallback {

	private Activity activity;
	private int cameraId = 0;
	private CameraCallback cameraCallback;
	private PackageManager packageManager;

	public SimpleCamera(Activity activity, CameraCallback cameraCallback) {
		this.activity = activity;

		if (cameraCallback == null) {
			throw new IllegalArgumentException("The cameraCallback parameter cannot be null");
		}

		this.cameraCallback = cameraCallback;

		this.packageManager = this.activity.getPackageManager();

		cameraId = findFrontFacingCamera();

		if (!packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA) && cameraId < 0) {
			throw new CameraUnavailableException("This device does not have a camera");
		}
	}

	public void takePicture() {

		Camera camera = Camera.open(cameraId);
		camera.takePicture(this, this, this, this);
	}

	public void takeVideo() {

		Intent intent = new Intent(Intent.ACTION_VIEW);
		List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);

		if (list.size() > 0) {
			Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
			activity.startActivityForResult(takeVideoIntent, 9001);
		}
	}

	@Override
	public void onPictureTaken(byte[] data, Camera camera) {

		if (camera != null) {
			camera.stopPreview();
			camera.release();
		}

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
