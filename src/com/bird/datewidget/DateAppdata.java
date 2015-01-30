package com.bird.datewidget;

import android.app.Application;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

public class DateAppdata extends Application {
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.e("wxp", "DateAppdata-onCreate");
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_TIME_TICK);
		filter.addAction(Intent.ACTION_DATE_CHANGED);
		filter.addAction(Intent.ACTION_TIME_CHANGED);
		filter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
		registerReceiver(new TimeChangeReceiver(), filter);
	}

}
