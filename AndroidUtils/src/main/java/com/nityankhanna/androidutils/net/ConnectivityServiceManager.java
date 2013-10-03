package com.nityankhanna.androidutils.net;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.ContextWrapper;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.provider.Settings;

import com.nityankhanna.androidutils.exceptions.ServiceUnavailableException;

/**
 * Created by Nityan Khanna on 05/07/13.
 */

/**
 * A ConnectivityService providing methods to determine certain service connectivity.
 */
public class ConnectivityServiceManager extends ContextWrapper {

	private static ConnectivityServiceManager sharedInstance;
	private Context context;

	private ConnectivityServiceManager(Context context) {
		super(context);
		this.context = context;
	}

	/**
	 * Returns a shared instance of the ConnectivityServiceManager class.
	 *
	 * @param context The application context.
	 * @return Returns a shared instance of the ConnectivityServiceManager class.
	 */
	public static ConnectivityServiceManager getInstance(Context context) {

		synchronized (ConnectivityServiceManager.class) {

			if (sharedInstance == null) {
				sharedInstance = new ConnectivityServiceManager(context);
			}
		}

		return sharedInstance;
	}

	/**
	 * Determines if there is internet connectivity.
	 *
	 * @return Returns true if there is internet connectivity.
	 */
	public boolean isNetworkAvailable() {

		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);

		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

		return (activeNetworkInfo != null && activeNetworkInfo.isConnected());
	}

	/**
	 * Determines if the user is connected to wifi.
	 *
	 * @return Returns true if the user is connected to wifi.
	 */
	public boolean isOnWiFi() {
		ConnectivityManager connManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
		NetworkInfo wifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		return wifi.isConnected();
	}

	/**
	 * Determines if GPS is enabled.
	 *
	 * @return Returns true if GPS is enabled.
	 */
	public boolean isGPSEnabled(){
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
	}

	/**
	 * Determines if Network Provider is available.
	 *
	 * @return Returns true if Network Provider is available.
	 */
	public boolean isNetworkProviderAvailable(){
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		return locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
	}

	/**
	 * Determines if Bluetooth is available on the current device.
	 *
	 * @return Returns true if bluetooth is available.
	 * @throws ServiceUnavailableException
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
	 * Determines if airplane mode is enabled on the current device.
	 *
	 * @return Returns true if airplane mode is enabled.
	 */
	public boolean isAirplaneModeOn() {
		return Settings.System.getInt(context.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
	}

	/**
	 * Determines if NFC is available on the current device.
	 *
	 * @return Returns true if NFC is available.
	 * @throws ServiceUnavailableException
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
	 * Determines if Android Beam is available on the current device.
	 *
	 * @return Returns true if Android Beam is available.
	 * @throws ServiceUnavailableException
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
}
