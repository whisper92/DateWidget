package com.bird.datewidget;


import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.RemoteViews;

public class TimeProvider extends AppWidgetProvider {

	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context,intent);
		String action = intent.getAction();
		if (action.equals("com.bird.datewidget.timechange")) {
			Log.e("wxp", "com.bird.datewidget.timechange");
			updateTime( context, AppWidgetManager.getInstance(context),null);
		} else {
			Log.e("wxp", "widget");
		}
		
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		Log.e("wxp", "onUpdate:"+appWidgetIds.length);
		updateTime(context,appWidgetManager,appWidgetIds);
	}

	public void updateTime(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds){
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.simple);
		setHour(remoteViews);
		setMinute(remoteViews);
		setDate(remoteViews);
		setWeek(remoteViews,context);
		if (appWidgetIds != null) {
			appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
        } else {
        	appWidgetManager.updateAppWidget( new ComponentName(context, this.getClass()), remoteViews);
        }
		
	}
	
	public void setHour(RemoteViews remoteViews) {
		String hour = Utils.getCurrentHour("12");
		int h = Integer.valueOf(hour);
		int h1 = h / 10;
		int h2 = h % 10;
		remoteViews.setImageViewResource(R.id.id_hour1, Utils.getBigImgResFromNumber(h1));
		remoteViews.setImageViewResource(R.id.id_hour2, Utils.getBigImgResFromNumber(h2));
	}

	public void setMinute(RemoteViews remoteViews ) {
		String minute = Utils.getCurrentMinute();
		int m = Integer.valueOf(minute);
		int m1 = m / 10;
		int m2 = m % 10;
		remoteViews.setImageViewResource(R.id.id_min1, Utils.getSmallImgResFromNumber(m1));
		remoteViews.setImageViewResource(R.id.id_min2, Utils.getSmallImgResFromNumber(m2));
	}

	public void setDate(RemoteViews remoteViews ) {
		String date = Utils.getCurrentDate();
		remoteViews.setTextViewText(R.id.id_date, date);
	}
	public void setWeek(RemoteViews remoteViews , Context context) {
		String week = Utils.getCurrentWeek(context);
		remoteViews.setTextViewText(R.id.id_week, week);
	}
	
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		super.onDeleted(context, appWidgetIds);
		Log.e("wxp", "onDeleted");
	}

	@Override
	public void onEnabled(Context context) {
		super.onEnabled(context);
		Log.e("wxp", "onEnabled");
	}

	@Override
	public void onDisabled(Context context) {
		super.onDisabled(context);
		Log.e("wxp", "onDisabled");
	}
	

	
}

