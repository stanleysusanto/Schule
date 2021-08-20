/**
 * Application name : Recipes App
 * Author			: Taufan Erfiyanto
 * Date				: March 2012
 */
package com.recipes.dewordy;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ProgressBar;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;
import java.util.Calendar;

//import com.facebook.FacebookSdk;

public class SplashScreen extends Activity {
	ProgressBar prgLoading;
	private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
	public static final String EXTRA_MESSAGE = "message";
	public static final String PROPERTY_REG_ID = "registration_id";
	private static final String PROPERTY_APP_VERSION = "appVersion";
	private final static String TAG = "LaunchActivity";
	protected String SENDER_ID = "254926937813";
	private GoogleCloudMessaging gcm = null;
	private String regid = null;
	private Context context = null;

	int progress = 0;

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);
//		FacebookSdk.sdkInitialize(getApplicationContext());

		if (checkPlayServices()) {
			gcm = GoogleCloudMessaging.getInstance(this);
			regid = getRegistrationId(context);

			if (regid.isEmpty()) {
				registerInBackground();
			} else {
				Log.d(TAG, "No valid Google Play Services APK found.");
			}
		}

		prgLoading = (ProgressBar) findViewById(R.id.prgLoading);
		prgLoading.setProgress(progress);

		new Loading().execute();


	}

	private String getRegistrationId(Context context) {
		final SharedPreferences prefs = getGCMPreferences(context);
		String registrationId = prefs.getString(PROPERTY_REG_ID, "");
		if (registrationId.isEmpty()) {
			Log.d(TAG, "Registration ID not found.");
			return "";
		}
		int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
		int currentVersion = getAppVersion(context);
		if (registeredVersion != currentVersion) {
			Log.d(TAG, "App version changed.");
			return "";
		}
		return registrationId;
	}

	private SharedPreferences getGCMPreferences(Context context) {
		return getSharedPreferences(MainActivity.class.getSimpleName(),
				Context.MODE_PRIVATE);
	}

	private static int getAppVersion(Context context) {
		try {
			PackageInfo packageInfo = context.getPackageManager()
					.getPackageInfo(context.getPackageName(), 0);
			return packageInfo.versionCode;
		} catch (PackageManager.NameNotFoundException e) {
			throw new RuntimeException("Could not get package name: " + e);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		checkPlayServices();
	}

	private boolean checkPlayServices() {
		int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		if (resultCode != ConnectionResult.SUCCESS) {
			if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
				GooglePlayServicesUtil.getErrorDialog(resultCode, this,
						PLAY_SERVICES_RESOLUTION_REQUEST).show();
			} else {
				Log.d(TAG, "This device is not supported - Google Play Services.");
				finish();
			}
			return false;
		}
		return true;
	}


	private void registerInBackground() {
		new AsyncTask() {

			@Override
			protected Object doInBackground(Object... params) {
				String msg = "";
				try {
					if (gcm == null) {
						gcm = GoogleCloudMessaging.getInstance(context);
					}
					regid = gcm.register(SENDER_ID);
					LoginState.setRegid(regid);
					SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
					SharedPreferences.Editor editor = settings.edit();
					editor.putString("regid", regid);
					editor.commit();
					Log.d(TAG, "########################################");
					Log.d(TAG, "Current Device's Registration ID is: " + msg);
				} catch (IOException ex) {
					msg = "Error :" + ex.getMessage();
				}
				return null;
			}

			protected void onPostExecute(Object result) { //to do here };
			}


		}.execute(null, null, null);
	}

	/**
	 * this class is used to handle thread
	 */
	public class Loading extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub

		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			while (progress < 100) {
				try {
					Thread.sleep(1000);
					progress += 100;
					prgLoading.setProgress(progress);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			if (SharedPreference.getusername(SplashScreen.this).length() == 0) {
				Intent i = new Intent(SplashScreen.this, LoginActivity.class);
				startActivity(i);
			} else {
				Intent i = new Intent(SplashScreen.this, MainActivity3.class);
				startActivity(i);
			}
//			if (SharedPreference.getusername(SplashScreen.this).length() == 0 && SharedPreference.getverification(SplashScreen.this).length() == 0) {
//				Intent i = new Intent(SplashScreen.this, LoginActivity.class);
//				startActivity(i);
//			}else{
//				Intent i = new Intent(SplashScreen.this, MainActivity3.class);
//				startActivity(i);
//			}
//			if (SharedPreference.getverification(SplashScreen.this).length() == 0) {
//				Intent i = new Intent(SplashScreen.this, MainActivity.class);
//				startActivity(i);
//			}else{
//				Intent i = new Intent(SplashScreen.this, MainActivity3.class);
//				startActivity(i);
//			}
//			Intent i = new Intent(SplashScreen.this, MainActivity.class);
//			startActivity(i);
		}
	}
	private void scheduleNotification(int week){
		Intent notificationIntent = new Intent(this, NotificationPublisher.class);
		notificationIntent.putExtra("notifId", getResources().getInteger(0));

		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, getResources().getInteger(0), notificationIntent, 0);

		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.DAY_OF_WEEK, week);
		calendar.set(Calendar.HOUR_OF_DAY, 9);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		AlarmManager alarmManager = (AlarmManager) MainActivity3.getInstance().getSystemService(Context.ALARM_SERVICE);
		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmManager.INTERVAL_FIFTEEN_MINUTES, pendingIntent);
	}
}