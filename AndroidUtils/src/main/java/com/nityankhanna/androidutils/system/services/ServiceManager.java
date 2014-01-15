package com.nityankhanna.androidutils.system.services;

import android.annotation.TargetApi;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;

import org.jetbrains.annotations.Nullable;

import com.nityankhanna.androidutils.system.SerializeManager;

/**
 * Created by Nityan Khanna on 05/07/13.
 */

/**
 * A Service Manager providing methods to determine certain service connectivity.
 */
public class ServiceManager extends Service {

	private static ServiceManager sharedInstance;
	private final Context context;

	private ServiceManager() {
		this.context = getApplicationContext();
	}

	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	public static ServiceManager getInstance() {

		synchronized (SerializeManager.class) {

			if (sharedInstance == null) {
				sharedInstance = new ServiceManager();
			}
		}

		return sharedInstance;
	}

	/**
	 * Determines if airplane mode is enabled on the current device.
	 *
	 * @return Returns true if airplane mode is enabled.
	 */
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
	public boolean isAirplaneModeOn() {
		return Settings.System.getInt(context.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
	}

	/**
	 * Determines if Android Beam is available on the current device.
	 *
	 * @return Returns true if Android Beam is available.
	 *
	 * @throws ServiceUnavailableException If the device does not support Android beam.
	 */
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public boolean isAndroidBeamAvailable() throws ServiceUnavailableException {

		NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(context);

		if (nfcAdapter == null) {
			throw new ServiceUnavailableException("The device does not support NFC.");
		} else {
			return nfcAdapter.isNdefPushEnabled();
		}
	}

	/**
	 * Determines if Bluetooth is available on the current device.
	 *
	 * @return Returns true if bluetooth is available.
	 *
	 * @throws ServiceUnavailableException If the device does not support bluetooth.
	 */
	public boolean isBluetoothAvailable() throws ServiceUnavailableException {

		BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

		if (bluetoothAdapter == null) {
			throw new ServiceUnavailableException("The device does not support Bluetooth.");
		} else {
			return bluetoothAdapter.isEnabled();
		}
	}

	/**
	 * Determines if GPS is enabled.
	 *
	 * @return Returns true if GPS is enabled.
	 */
	public boolean isGPSEnabled() {
		LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
	}

	/**
	 * Determines if there is internet connectivity.
	 *
	 * @return Returns true if there is internet connectivity.
	 */
	public boolean isNetworkAvailable() {

		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

		return (activeNetworkInfo != null && activeNetworkInfo.isConnected());
	}

	/**
	 * Determines if Network Provider is available.
	 *
	 * @return Returns true if Network Provider is available.
	 */
	public boolean isNetworkProviderAvailable() {
		LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		return locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
	}

	/**
	 * Determines if NFC is available on the current device.
	 *
	 * @return Returns true if NFC is available.
	 *
	 * @throws ServiceUnavailableException If the device does not support NFC.
	 */
	public boolean isNFCAvailable() throws ServiceUnavailableException {

		NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(context);

		if (nfcAdapter == null) {
			throw new ServiceUnavailableException("The device does not support NFC.");
		} else {
			return nfcAdapter.isEnabled();
		}
	}

	/**
	 * Determines if the user is connected Wi-Fi.
	 *
	 * @return Returns true if the user is connected to Wi-Fi.
	 */
	public boolean isOnWiFi() {
		ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo wifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		return wifi.isConnected();
	}

	/**
	 * Determines if the device is running JellyBean or higher.
	 *
	 * @return Returns true if the device is running JellyBean or higher.
	 */
	public boolean isJellyBeanOrHigher() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
	}

	/**
	 * Determines if the device is running IceCreamSandwich or higher.
	 *
	 * @return Returns true if the device is running IceCreamSandwich or higher.
	 */
	public boolean isICSOrHigher() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
	}

	/**
	 * Determines if the device is running HoneyComb or higher.
	 *
	 * @return Returns true if the device is running HoneyComb or higher.
	 */
	public boolean isHoneycombOrHigher() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
	}

	/**
	 * Determines if the device is running Gingerbread or higher.
	 *
	 * @return Returns true if the device is running Gingerbread or higher.
	 */
	public boolean isGingerbreadOrHigher() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD;
	}

	/**
	 * Determines if the device is running Froyo or higher.
	 *
	 * @return Returns true if the device is running Froyo or higher.
	 */
	public boolean isFroyoOrHigher() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
	}

	/**
	 * Determines if the device is a GoogleTV.
	 *
	 * @return Returns true if the device is a GoogleTV.
	 */
	public boolean isGoogleTV(Context context) {
		return context.getPackageManager().hasSystemFeature("com.google.android.tv");
	}


}
