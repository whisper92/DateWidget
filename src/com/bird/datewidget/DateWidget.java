package com.bird.datewidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

public class DateWidget extends View {

	public DateWidget(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public DateWidget(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public DateWidget(Context context) {
		super(context);
		init(context);
	}

	public void init(Context context) {
      LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      inflater.inflate(R.layout.date_layout, null);
      
	}

}
