package com.xiao.androiddemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.xiao.androiddemo.util.VoiceDownloadUtil;
import com.xiao.demo.bean.FileBean;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * @filename ThreadPoolAct.java
 * @TODO
 * @date 2015-1-8下午3:27:57
 * @Administrator 萧
 * 
 */
public class ThreadPoolAct extends Activity {

	ListView lv;

	FileBean file;
	String url = "http://219.232.246.189:8080/download/patient/1.2.5/tmp.3gpp";

	List<FileBean> list = new ArrayList<FileBean>();
	List<FileBean> resultList = new ArrayList<FileBean>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_down);
		lv = (ListView) findViewById(R.id.down_lv);
		for (int i = 0; i < 10; i++) {
			file = new FileBean();
			file.setDownUrl(url);
			file.setName("file" + i);
			list.add(file);
		}
		MyAdapter ma = new MyAdapter(this, list);
		lv.setAdapter(ma);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ImageView img = (ImageView) view.findViewById(R.id.voice_item_img);
				if (img.getTag(2)==null) {
					Toast.makeText(getBaseContext(),  " 正在下载音频文件 请稍后再试 ", Toast.LENGTH_LONG).show();
				}else {
					final AnimationDrawable anim = (AnimationDrawable) img.getBackground();
					anim.start();
					Toast.makeText(getBaseContext(),  "开始播放 "+img.getTag(2).toString(), Toast.LENGTH_LONG).show();
					new Timer().schedule(new TimerTask() {
						
						@Override
						public void run() {
							anim.stop();
						}
					}, 1500);
				}
			}
		});
	}

	public class MyAdapter extends BaseAdapter {

		LayoutInflater inflater;
		List<FileBean> list = new ArrayList<FileBean>();
		Context context;

		public MyAdapter(Context context, List<FileBean> list) {
			inflater = LayoutInflater.from(context);
			this.list = list;
			this.context = context;
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = inflater.inflate(R.layout.voice_item, parent,
						false);
				holder.img = (ImageView) convertView
						.findViewById(R.id.voice_item_img);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.img.setBackgroundResource(R.drawable.voice_playing);
			holder.img.setTag(0, list.get(position).getName());
			holder.img.setTag(1, list.get(position).getDownUrl());
			
			holder.img.setTag(2,VoiceDownloadUtil.startDown(list.get(position)).getPath());
			
			
			// holder.img.setOnClickListener(new OnClickListener() {
			//
			// @Override
			// public void onClick(View v) {
			//
			// }
			// });
			//
			return convertView;
		}

		private class ViewHolder {
			private ImageView img;
		}

	}

}
