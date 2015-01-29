package com.bird.datewidget;

import android.app.Activity;
import android.os.Bundle;

public class TimeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(new DateWidgetView(this));
	}
}
