package com.xiao.customwidget.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;


public class MyRadioButton extends RadioButton implements
		OnCheckedChangeListener {

	public MyRadioButton(Context context) {
		super(context);
	}

	public MyRadioButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

	}

}
