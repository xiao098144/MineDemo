package com.xiao.customwidget.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * @filename MyViewPager.java
 * @TODO
 * @date 2015-1-19下午5:00:08
 * @Administrator 萧
 * 
 */
public class MyViewPager extends ViewPager {

	private GestureDetector gestureDetector;
	private MyImageView imageView;
	
	public void setGestureDetector(GestureDetector gestureDetector){
		this.gestureDetector = gestureDetector;
	}
	
	public MyViewPager(Context context) {
		super(context);
	}

	public MyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		try {
			return super.onInterceptTouchEvent(arg0);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
}
