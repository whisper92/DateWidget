package com.bird.datewidget;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

public class TwinkleDot extends ImageView {

	Context mContext = null;
	public TwinkleDot(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
        //init(context);
	}

	public TwinkleDot(Context context, AttributeSet attrs) {
		this(context, null,0);

	}

	public TwinkleDot(Context context) {
		this(context, null);

	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.dot),0,0,null);
		
	}
	
	int mWidth = 0;
	int mHeight = 0;
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		Drawable drawable = getResources().getDrawable(R.drawable.dot);
		if (widthMode == MeasureSpec.AT_MOST) {
			
			mWidth = drawable.getIntrinsicWidth();
		} else {
			mWidth = widthSize;
		}
		
		if (heightSize == MeasureSpec.AT_MOST) {
			mHeight = drawable.getIntrinsicHeight();
		} else {
			mHeight = heightSize;
		}
		setMeasuredDimension(mWidth, mHeight);
	}
	public void init(Context context){
		mContext = context;
        Animation animation = AnimationUtils.loadAnimation(context,R.anim.twinkle_dot);
        animation.setInterpolator(new DecelerateInterpolator());
        this.startAnimation(animation);
	}

}
