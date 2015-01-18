package com.xiao.customwidget.widget;

import android.content.Context;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Gallery;

/**
 * Date:2015-1-18下午2:50:53 Author:Administrator TODO:TODO
 */
public class PicGallery extends Gallery {

	private GestureDetector gestureDetector;
	private MyImageView imageView;

	public PicGallery(Context context) {
		super(context);
	}

	public PicGallery(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setOnTouchListener(new OnTouchListener() {

			float baseValue;
			float originalscale;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				View view = PicGallery.this.getSelectedView();
				if (view instanceof MyImageView) {
					imageView = (MyImageView) view;
					if (event.getAction() == MotionEvent.ACTION_DOWN) {
						baseValue = 0;
						originalscale = imageView.getScale();
					} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
						if (event.getPointerCount() == 2) {
							float x = event.getX(0) - event.getX(1);
							float y = event.getY(0) - event.getY(1);
							float distance = (float) Math.sqrt(Math.pow(x, 2)
									+ Math.pow(y, 2));
							if (baseValue == 0) {
								baseValue = distance;
							} else {
								float scale = distance / baseValue;
								imageView.zoomTo(scale * originalscale, x
										+ event.getX(1), y + event.getY(1));
							}
						}
					}
				}

				return false;
			}
		});
	}

	public PicGallery(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

	}

	public void setDetector(GestureDetector gestureDetector) {
		this.gestureDetector = gestureDetector;
	}

	float v[] = new float[9];
	/** 屏幕宽度 */
	private float screen_width;
	private float screen_height;

	int kEvent = KEY_INVALID; // invalid
	public static final int KEY_INVALID = -1;

	/**
	 * 
	 * @param e1
	 *            先按下
	 * @param e2
	 *            后按下
	 * @param distanceX
	 * @param distanceY
	 * @return
	 */
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		View view = PicGallery.this.getSelectedView();
		if (view instanceof MyImageView) {
			float xdistance = calXdistance(e1, e2);
			float minDistance = screen_width / 4f;
			if (xdistance > minDistance) { // 满足足够的滑动距离  DPAD_LEFT 左滑  DPAD_RIGHT 右滑
				kEvent = isScrollingLeft(e1, e2) ? KeyEvent.KEYCODE_DPAD_LEFT  
						: KeyEvent.KEYCODE_DPAD_RIGHT;
			}
			imageView = (MyImageView) view;
			Matrix matrix = imageView.getImageMatrix();
			matrix.getValues(v);
			
			float left , right;
			
			float width = imageView.getScale() * imageView.getImageWidth();
			float height = imageView.getScale() * imageView.getImageHeight();
			
			if ((int)width <= screen_width &&((int)height <= screen_height)) {
				super.onScroll(e1, e2, distanceX, distanceY);
			} else {
//				matrix = {MSCALE_X, MSKEW_X, MTRANS_X,		//  scale 缩放   
//		                MSKEW_Y, MSCALE_Y, MTRANS_Y,  		//	skew 倾斜
//		                MPERSP_0, MPERSP_1, MPERSP_2  		//  trans 位移
//		                }; // persp 暂时使用不多
				
				left = v[Matrix.MTRANS_X];
			}
			
		}
		return super.onScroll(e1, e2, distanceX, distanceY);
	}

	private boolean isScrollingLeft(MotionEvent e1, MotionEvent e2) {
		return e2.getX() > e1.getX();
	}

	private float calXdistance(MotionEvent e1, MotionEvent e2) {
		return Math.abs(e2.getX() - e1.getX());
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		return false; // false 事件继续执行 true 事件终止 不再执行
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		return super.onTouchEvent(event);
	}

}
