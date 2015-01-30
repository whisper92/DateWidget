package com.bird.datewidget;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import android.content.Context;

public class Utils {
	private static int[] BIGIMGS = new int[] { R.drawable.big_0,
			R.drawable.big_1, R.drawable.big_2, R.drawable.big_3,
			R.drawable.big_4, R.drawable.big_5, R.drawable.big_6,
			R.drawable.big_7, R.drawable.big_8, R.drawable.big_9 };

	private static int[] SMALLIMGS = new int[] { R.drawable.small_0,
			R.drawable.small_1, R.drawable.small_2, R.drawable.small_3,
			R.drawable.small_4, R.drawable.small_5, R.drawable.small_6,
			R.drawable.small_7, R.drawable.small_8, R.drawable.small_9 };

	public static String getCurrentDate() {
		SimpleDateFormat sDateFormat = new SimpleDateFormat("MM/dd");
		String date = sDateFormat.format(new java.util.Date());
		return date;
	}

	public static String getCurrentHour(String format) {
		if (format.equals("12")) {
			SimpleDateFormat sDateFormat = new SimpleDateFormat("hh");
			String date = sDateFormat.format(new java.util.Date());
			return date;
		} else {
			SimpleDateFormat sDateFormat = new SimpleDateFormat("HH");
			String date = sDateFormat.format(new java.util.Date());
			return date;
		}
		
	}

	public static String getCurrentMinute() {
		SimpleDateFormat sDateFormat = new SimpleDateFormat("mm");
		String date = sDateFormat.format(new java.util.Date());
		return date;
	}

	public static String getCurrentWeek(Context context) {
		String[] weekarray = context.getResources().getStringArray(R.array.week_array);
		final Calendar c = Calendar.getInstance();
		String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
		if ("1".equals(mWay)) {
			mWay = weekarray[0];
		} else if ("2".equals(mWay)) {
			mWay = weekarray[1];
		} else if ("3".equals(mWay)) {
			mWay = weekarray[2];
		} else if ("4".equals(mWay)) {
			mWay = weekarray[3];
		} else if ("5".equals(mWay)) {
			mWay = weekarray[4];
		} else if ("6".equals(mWay)) {
			mWay = weekarray[5];
		} else if ("7".equals(mWay)) {
			mWay = weekarray[6];
		}
		return  mWay;
	}

	/**
	 * 根据数字返回相应的图片资源的id
	 * 
	 * @param num
	 *            数字
	 * @return 图片资源id
	 */
	public static int getBigImgResFromNumber(int num) {
		return BIGIMGS[num];
	}

	public static int getSmallImgResFromNumber(int num) {
		return SMALLIMGS[num];
	}
}
