package com.bird.datewidget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.provider.Settings;
import android.os.Handler;
import android.net.Uri;

public class DateWidgetView extends RelativeLayout {

	private ImageView mHour1;
	private ImageView mHour2;
	private ImageView mMin1;
	private ImageView mMin2;
	private TextView mDate;
	private TextView mWeek;
	private Context mContext;
	IntentFilter filter;
	MyTimeChangeReceiver mTimeChangeReceiver;
	private String mFormat = "12";
	private boolean isAttacted = false;

	public DateWidgetView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public DateWidgetView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public DateWidgetView(Context context) {
		super(context);
		init(context);
	}

	public void init(Context context) {
		mContext = context;
		getTimeFormat();
		mTimeChangeReceiver = new MyTimeChangeReceiver();
		filter = new IntentFilter();
		filter.addAction(Intent.ACTION_TIME_TICK);
		filter.addAction(Intent.ACTION_DATE_CHANGED);
		filter.addAction(Intent.ACTION_TIME_CHANGED);
		filter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		ViewGroup viewGroup = (ViewGroup) inflater.inflate(
				R.layout.simple, this);
		mHour1 = (ImageView) viewGroup.findViewById(R.id.id_hour1);
		mHour2 = (ImageView) viewGroup.findViewById(R.id.id_hour2);
		mMin1 = (ImageView) viewGroup.findViewById(R.id.id_min1);
		mMin2 = (ImageView) viewGroup.findViewById(R.id.id_min2);
		mDate = (TextView) viewGroup.findViewById(R.id.id_date);
		mWeek = (TextView) viewGroup.findViewById(R.id.id_week);
		
	}

	@Override
	protected void onAttachedToWindow() {
		// TODO Auto-generated method stub
		super.onAttachedToWindow();
		if (!isAttacted) {

			isAttacted = true;
			if (filter != null && mTimeChangeReceiver != null) {
				updateView();
				mContext.registerReceiver(mTimeChangeReceiver, filter);
			}
			registerObserver();
		}

	}

	@Override
	protected void onDetachedFromWindow() {
		// TODO Auto-generated method stub
		super.onDetachedFromWindow();
		if (isAttacted) {

			isAttacted = false;
			if (mTimeChangeReceiver != null) {
				mContext.unregisterReceiver(mTimeChangeReceiver);
			}
			unregisterObserver();

		}

	}

	private final ContentObserver mFormatChangeObserver = new ContentObserver(
			new Handler()) {
		@Override
		public void onChange(boolean selfChange) {
			getTimeFormat();
			updateView();
		}

		@Override
		public void onChange(boolean selfChange, Uri uri) {
			getTimeFormat();
			updateView();
		}
	};

	public void getTimeFormat() {
		ContentResolver cv = mContext.getContentResolver();
		String strTimeFormat = android.provider.Settings.System.getString(cv,
				android.provider.Settings.System.TIME_12_24);
		if (strTimeFormat.equals("24")) {
			mFormat = "24";
			Log.e("wxp", "24");
		} else {
			mFormat = "12";
			Log.e("wxp", "12");
		}
	}

	private void registerObserver() {
		final ContentResolver resolver = getContext().getContentResolver();
		resolver.registerContentObserver(Settings.System.CONTENT_URI, true,
				mFormatChangeObserver);
	}

	private void unregisterObserver() {
		final ContentResolver resolver = getContext().getContentResolver();
		resolver.unregisterContentObserver(mFormatChangeObserver);
	}

	public void updateView() {
		String hour = Utils.getCurrentHour(mFormat);
		int h = Integer.valueOf(hour);
		int h1 = h / 10;
		int h2 = h % 10;
		mHour1.setImageResource(Utils.getBigImgResFromNumber(h1));
		mHour2.setImageResource(Utils.getBigImgResFromNumber(h2));
		String minute = Utils.getCurrentMinute();
		int m = Integer.valueOf(minute);
		int m1 = m / 10;
		int m2 = m % 10;
		mMin1.setImageResource(Utils.getSmallImgResFromNumber(m1));
		mMin2.setImageResource(Utils.getSmallImgResFromNumber(m2));

		mDate.setText(Utils.getCurrentDate());
		mWeek.setText(Utils.getCurrentWeek(mContext));
	}

	class MyTimeChangeReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals(Intent.ACTION_TIME_TICK)
					|| action.equals(Intent.ACTION_DATE_CHANGED)
					|| action.equals(Intent.ACTION_TIME_CHANGED)
					|| action.equals(Intent.ACTION_TIMEZONE_CHANGED)) {
				Log.e("wxp", "ACTION_TIME_TICK");
				updateView();
			}

		}
	}

}
