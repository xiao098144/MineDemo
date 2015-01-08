package com.xiao.androiddemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 *@filename ThirdAct.java
 *@TODO
 *@date 2014-12-19上午10:20:39
 *@Administrator 萧
 *
 */
public class ThirdAct extends Activity{

	TextView tv;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.third);
		tv = (TextView) findViewById(R.id.third);
		tv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.putExtra("test", "ceshi");
				setResult(201 , intent);
				finish();
			}
		});
	}
	
}
