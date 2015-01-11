package com.xiao.androiddemo.util;

import java.io.IOException;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;

/**
 * @filename VoicePlayUtil.java
 * @TODO 1、采用类级内部类实现单例 
 * 		 2、点击播放、停止、实现切换播放下一条
 * 		 3、	通过回调接口  通知界面播放状态以便更新界面
 * 		 4、使用前先调用setOnStateChangeListener()	
 * @date 2015-1-9下午5:20:57
 * @Administrator 萧
 * 
 */
public class VoicePlayUtil implements OnCompletionListener, OnErrorListener {

	public static final int PLAYING_STATE = 2;
	/** 空闲 */
	public static final int IDLE_STATE = 0;
	/** 当前状态 */
	private int mState = IDLE_STATE;
	/** 正常 */
	public static final int NO_ERROR = 0;
	/** 存储访问异常 */
	public static final int STORAGE_ACCESS_ERROR = 1;
	/** 内部异常 */
	public static final int INTERNAL_ERROR = 2;

	private VoicePlayUtil() {

	}

	private static class VoiceInstance {
		private static VoicePlayUtil voicePlayUtil = new VoicePlayUtil();
	}

	public static VoicePlayUtil getInstance() {
		return VoiceInstance.voicePlayUtil;
	}

	public interface OnStateChangedListener {
		public void onStateChanged(int state);

		public void onError(int error);

		public void onComplete();
	}

	private OnStateChangedListener mOnStateChangedListener = null;

	 public void setOnStateChangedListener(OnStateChangedListener listener) {
	        mOnStateChangedListener = listener;
	 }
	
	private MediaPlayer player;

	public void startPlay(String filePath) {
		player = new MediaPlayer();
		try {
			player.setDataSource(filePath);
			player.setOnCompletionListener(this);
			player.setOnErrorListener(this);
			player.prepare();
			player.start();
		} catch (IllegalArgumentException e) {
			setError(INTERNAL_ERROR);
            player = null;
            return;
		} catch (IOException e) {
			setError(STORAGE_ACCESS_ERROR);
            player = null;
            return;
		}
		setState(PLAYING_STATE);
	}

	public void stopPlay() {
		if (player == null)
			return;

		player.stop();
		player.release();
		player = null;
		setState(IDLE_STATE);
		if (mOnStateChangedListener != null) {
			mOnStateChangedListener.onComplete();
		}
	}

	public boolean onError(MediaPlayer mp, int what, int extra) {
		stopPlay();
		setError(STORAGE_ACCESS_ERROR);
		return true;
	}

	public void onCompletion(MediaPlayer mp) {
		stopPlay();
	}

	private  void setState(int state) {
		if (state == mState)
			return;
		mState = state;
		signalStateChanged(mState);
	}

	private void signalStateChanged(int state) {
		if (mOnStateChangedListener != null)
			mOnStateChangedListener.onStateChanged(state);
	}

	private void setError(int error) {
		if (mOnStateChangedListener != null)
			mOnStateChangedListener.onError(error);
	}

}
