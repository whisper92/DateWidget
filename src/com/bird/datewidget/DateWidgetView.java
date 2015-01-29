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
		mTimeChangeReceiver = new MyTimeChangeReceiver();
		filter = new IntentFilter();
		filter.addAction(Intent.ACTION_TIME_TICK);
		filter.addAction(Intent.ACTION_DATE_CHANGED);
		filter.addAction(Intent.ACTION_TIME_CHANGED);
		filter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		ViewGroup viewGroup = (ViewGroup) inflater.inflate(
				R.layout.date_layout, this);
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
		if (filter !=null && mTimeChangeReceiver !=null) {
			updateView();
			mContext.registerReceiver(mTimeChangeReceiver, filter);
		}		
	}
	
	@Override
	protected void onDetachedFromWindow() {
		// TODO Auto-generated method stub
		super.onDetachedFromWindow();
		if ( mTimeChangeReceiver !=null) {
			mContext.unregisterReceiver(mTimeChangeReceiver);
		}
	}
	
	public void updateView(){
		String hour = Utils.getCurrentHour();
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
