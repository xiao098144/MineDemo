package com.xiao.androiddemo;

import java.io.IOException;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

import com.xiao.androiddemo.util.BitmapHandlerUtil;
import com.xiao.androiddemo.util.ImageLoaderUtil;

/**
 * @filename BitmapAct.java
 * @TODO   
 * @date 2015-1-7下午3:54:14
 * @Administrator 萧
 * 
 */
public class BitmapAct extends Activity implements OnTouchListener{

	ImageView mView;    
	private BitmapRegionDecoder bmpDecoder;
	private Rect mRect = new Rect();
	private float screenWidth;
	private float screenHight;

	@TargetApi(Build.VERSION_CODES.GINGERBREAD_MR1)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Display display = getWindowManager().getDefaultDisplay();
		DisplayMetrics outMetrics = new DisplayMetrics();
		display.getMetrics(outMetrics);
		screenWidth = outMetrics.widthPixels;
		screenHight = outMetrics.heightPixels;
		setContentView(R.layout.act_bitmap);
		mView = (ImageView) findViewById(R.id.img);
		
//		mView.setOnTouchListener(this);
		
		try {
			bmpDecoder = BitmapRegionDecoder.newInstance(
					BitmapHandlerUtil.bmp2IS(BitmapHandlerUtil
							.drawable2Bitmap(getResources().getDrawable(
									R.drawable.image1))), true);
			setImageRegion(mView.getLeft(), mView.getTop());
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		 String url = "www.ddoctor.cn/product/9/9_4.jpg";
//		new ImageLoaderUtil(this, getCacheDir().getPath()).loadImages(url, true,
//		 mView, null, screenWidth, screenHight);
	}

	 @Override
	    public boolean onTouch(View v, MotionEvent event) {
	        final int action = event.getAction() & MotionEvent.ACTION_MASK;
	        final int x = (int) event.getX();
	        final int y = (int) event.getY();
	        switch (action) {
	        case MotionEvent.ACTION_DOWN:
	        case MotionEvent.ACTION_MOVE:
	            setImageRegion(x, y);
	            break;
	        }
	        return true;
	    }
	   
	    @TargetApi(Build.VERSION_CODES.GINGERBREAD_MR1)
		private void setImageRegion(int left, int top) {
//	        BitmapFactory.Options opts = new BitmapFactory.Options();
	        final int width = mView.getWidth();
	        final int height = mView.getHeight();
	       
	        final int imgWidth = bmpDecoder.getWidth();
	        final int imgHeight = bmpDecoder.getHeight();
	       int scale = (int) (imgHeight/screenHight);
	        int right = (int) (left + Math.max(imgWidth, screenWidth));
	        int bottom = (int) (top + Math.max(imgHeight, screenHight));
//	        if(right > imgWidth) right = imgWidth;
//	        if(bottom > imgHeight) bottom = imgHeight;
	        if(left < 0) left = 0;
	        if(top < 0) top = 0;
	       Log.e("", "左 "+left+"  上  "+top+"  右   "+right+"  下  "+bottom+"\n"+" 图片宽  "+imgWidth+"  图片高 "+imgHeight+"  \n空间宽  "+width+"  高  "+height );
	        mRect.set(left, top, right, bottom);
	        Bitmap bm = bmpDecoder.decodeRegion(mRect, null);
	        mView.setImageBitmap(bm);
	    }
	
}
