package com.xiao.androiddemo;

import java.util.ArrayList;
import java.util.List;

import com.xiao.androiddemo.util.VoiceDownloadUtil;
import com.xiao.androiddemo.util.VoicePlayUtil;
import com.xiao.demo.bean.FileBean;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
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
public class ThreadPoolAct extends Activity implements VoicePlayUtil.OnStateChangedListener{

	ListView lv;

	FileBean file;
	String url = "http://219.232.246.189:8080/download/patient/1.2.5/tmp.3gpp";

	List<FileBean> list = new ArrayList<FileBean>();
	List<FileBean> resultList = new ArrayList<FileBean>();

	protected AnimationDrawable anim;

	protected ImageView current_img;
	protected ImageView last_img;
	
	private int playerState = VoicePlayUtil.IDLE_STATE;

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
		VoicePlayUtil.getInstance().setOnStateChangedListener(this);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				current_img = (ImageView) view.findViewById(R.id.voice_item_img);
				if (current_img.getTag(R.id.tag_path)==null) {
					Toast.makeText(getBaseContext(),  " 正在下载语音文件 请稍后再试 ", Toast.LENGTH_LONG).show();
				}else {
					if (playerState == VoicePlayUtil.PLAYING_STATE) {
						VoicePlayUtil.getInstance().stopPlay();
						if (last_img != current_img) {
							current_img.setBackgroundResource(R.anim.voice_play);
							anim = (AnimationDrawable) current_img.getBackground();
							anim.start();  
							VoicePlayUtil.getInstance().startPlay(current_img.getTag(R.id.tag_path).toString());
						}
					}else {
						current_img.setBackgroundResource(R.anim.voice_play);
						anim = (AnimationDrawable) current_img.getBackground();
						anim.start();
						VoicePlayUtil.getInstance().startPlay(current_img.getTag(R.id.tag_path).toString());
					}
					last_img = current_img;
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

			holder.img.setBackgroundResource(R.anim.voice_play);
			holder.img.setTag(R.id.tag_name, list.get(position).getName());
			holder.img.setTag(R.id.tag_url, list.get(position).getDownUrl());  
			
			VoiceDownloadUtil.startDown1(list.get(position) , holder.img);

			return convertView;
		}

		private class ViewHolder {
			private ImageView img;
		}

	}
	
	@Override
	protected void onStop() {
		super.onStop();
		if (playerState == VoicePlayUtil.PLAYING_STATE) {
			VoicePlayUtil.getInstance().stopPlay();
		}
	}

	@Override
	public void onStateChanged(int state) {
		playerState = state;
	}

	@Override
	public void onError(int error) {
		Resources res = getResources();

        String message = null;
        switch (error) {
            case VoicePlayUtil.STORAGE_ACCESS_ERROR:
                message = res.getString(R.string.error_sdcard_access);
                break;
            case VoicePlayUtil.INTERNAL_ERROR:
                message = res.getString(R.string.error_app_internal);
                break;
        }
        if (message != null) {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        }
	}
	
	/**
	 * 实现回调接口  播放完毕 结束音量播放动画		
	 */
	@Override
	public void onComplete() {
		if (anim != null && anim.isRunning()) {
			anim.stop();
			anim = null;
		}
		last_img.setBackgroundResource(R.drawable.voice_playing);
		
	}

}
