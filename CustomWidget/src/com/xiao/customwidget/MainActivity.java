package com.xiao.customwidget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	Button btn1;
	Button btn2;
	Button btn3;
	Button btn4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn1 = (Button) findViewById(R.id.btn1);

		btn1.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btn1:{
			Intent in = new Intent();
			in.setClass(this, ViewPagerAct.class);
			startActivity(in);
		}
			
			break;

		default:
			break;
		}
	}

}
