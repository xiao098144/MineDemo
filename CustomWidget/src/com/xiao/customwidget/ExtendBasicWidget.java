package com.xiao.customwidget;

import android.app.Activity;
import android.os.Bundle;

public class ExtendBasicWidget extends Activity{

	Button btn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_extendwidget);
	}
	
}
