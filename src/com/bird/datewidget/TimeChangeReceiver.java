package com.bird.datewidget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.drm.DrmStore.Action;
import android.util.Log;

class TimeChangeReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if (action.equals(Intent.ACTION_TIME_TICK)
				|| action.equals(Intent.ACTION_DATE_CHANGED)
				|| action.equals(Intent.ACTION_TIME_CHANGED)
				|| action.equals(Intent.ACTION_TIMEZONE_CHANGED)) {
			Log.e("wxp", "ACTION_TIME_TICK");
			context.sendBroadcast(new Intent("com.bird.datewidget.timechange"));
		}

	}
}