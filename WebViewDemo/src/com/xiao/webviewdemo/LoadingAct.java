package com.xiao.webviewdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 *Date:2015-1-13下午11:26:15
 *Author:Administrator
 *TODO:TODO
 */
public class LoadingAct extends Activity{

	Button btn;
	EditText et_url1;
	EditText et_url2;
	private String base = "http://www.ddoctor.cn/sugardoctorcms/knowledge.jsp?contentid="; 
	private String url ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_loading);
		et_url1 = (EditText) findViewById(R.id.et_url1);
		et_url2 = (EditText) findViewById(R.id.et_url2);
		btn = (Button) findViewById(R.id.btn_ok);
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String url1 = et_url1.getText().toString();
				if (TextUtils.isEmpty(url1)) {
					String url2 = et_url2.getText().toString();
					url = base+url2;
				}else {
					url = base+url1;
				}
				Intent in = new Intent();
				in.putExtra("url", url);
				in.setClass(LoadingAct.this, MainActivity.class);
				startActivity(in);
			}
		});
		
	}
	
}

		