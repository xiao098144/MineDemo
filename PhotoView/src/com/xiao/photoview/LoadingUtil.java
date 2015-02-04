package com.xiao.photoview;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;

/**
 * @filename LoadingUtil.java
 * @TODO
 * @date 2015-2-4上午10:53:03
 * @Administrator 萧
 * 
 */
public class LoadingUtil {

	private LoadingUtil() {

	}

	private static LoadingUtil loadingUtil;

	public static LoadingUtil getInstance() {
		if (loadingUtil == null) {
			loadingUtil = new LoadingUtil();
		}
		return loadingUtil;
	}

	private Dialog dialog;
	private AnimationDrawable anim;

	public void startLoading(Context context) {
		finishLoading();
		dialog = new Dialog(context , R.style.NoTitleDialog);
		View view = LayoutInflater.from(context)
				.inflate(R.layout.loading, null);
		ImageView img = (ImageView) view.findViewById(R.id.loading_img);
		img.setBackgroundResource(R.anim.imageloading);
		anim = (AnimationDrawable) img.getBackground();
		anim.start();
		WindowManager.LayoutParams lp=dialog.getWindow().getAttributes();
		lp.alpha=0f;
		Window window = dialog.getWindow();
		window.setAttributes(lp);
		window.setGravity(Gravity.CENTER);
		window.setLayout(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		dialog.setCancelable(false);
		dialog.setCanceledOnTouchOutside(false);
		dialog.setContentView(view);
		dialog.show();
	}

	public void finishLoading() {
		if (dialog == null) {
			Log.e("", "dialog == null");
			if (anim == null) {
				Log.e("", "anim == null");
			}else {
				Log.e("", "anim != null "+(anim.isRunning()));
			}
		}else {
			Log.e("", "dialog != null "+dialog.isShowing());
		}
		
		if (dialog != null && dialog.isShowing()) {
			if (anim != null && anim.isRunning()) {
				anim.stop();
				anim = null;
			}
			dialog.dismiss();
			dialog = null;
			System.gc();
		}else {
			Log.e("", "(dialog != null && dialog.isShowing()) = "+(dialog != null && dialog.isShowing()));
		}
	}

}
