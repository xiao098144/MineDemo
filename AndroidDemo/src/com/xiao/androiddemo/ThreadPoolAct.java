package com.xiao.androiddemo;

import java.util.ArrayList;
import java.util.List;

import com.xiao.androiddemo.util.VoiceDownloadUtil;
import com.xiao.demo.bean.FileBean;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 *@filename ThreadPoolAct.java
 *@TODO
 *@date 2015-1-8下午3:27:57
 *@Administrator 萧
 *
 */
public class ThreadPoolAct extends Activity{

	Button btn;
	FileBean file;
	String url = "http://219.232.246.189:8080/download/patient/1.2.5/tmp.3gpp";
	
	List<FileBean> list = new ArrayList<FileBean>();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_down);
		btn = (Button) findViewById(R.id.down_btn1);
		for (int i = 0; i < 10; i++) {
			file = new FileBean();
			file.setDownUrl(url);
			file.setName(System.currentTimeMillis()+""+i);
		}
		
		file = VoiceDownloadUtil.startDown(file);
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Toast.makeText(getBaseContext(), ""+file.getFileSize()+"  "+file.getPath(), Toast.LENGTH_LONG).show();
			}
		});
	}
	
}
