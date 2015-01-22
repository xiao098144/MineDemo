package com.xiao.customwidget.widget;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Gallery;

/**
 * @filename PicGallery.java
 * @TODO
 * @date 2015-1-19上午10:32:42
 * @Administrator 萧
 * 
 */
public class PicGallery extends Gallery {

	private GestureDetector gestureDetector;
	private MyImageView imageView;
	private int screen_width;
	private int screen_height;

	public PicGallery(Context context) {
		super(context);

	}

	public PicGallery(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setOnTouchListener(new OnTouchListener() {

			float baseDistance;
			float originalScale;

			@Override
			public boolean onTouch(View arg0, MotionEvent event) {
				View view = PicGallery.this.getSelectedView();
				if (view instanceof MyImageView) {
					imageView = (MyImageView) view;
					if (event.getAction() == MotionEvent.ACTION_DOWN) {
						baseDistance = 0;
						originalScale = imageView.getScale();
					} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
						if (event.getPointerCount() == 2) {
							float x = event.getX(0) - event.getX(1);
							float y = event.getY(0) - event.getY(1);
							float value = (float) Math.sqrt(Math.pow(x, 2)
									+ Math.pow(y, 2));
							if (baseDistance == 0) {
								baseDistance = value;
							} else {
								Log.e("手指触控 onTouch ", " baseDistance = "
										+ baseDistance + "  value = " + value);
								float scale = value / baseDistance;
								imageView.zoomTo(originalScale * scale, x
										+ event.getX(1), x + event.getY(1));
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

	public void setGestureDetector(GestureDetector gestureDetector) {
		this.gestureDetector = gestureDetector;
	}

	private int keyEvent = KEY_INVALID;
	public static final int KEY_INVALID = -1;
	private float[] values = new float[9];

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		View view = PicGallery.this.getSelectedView();
		if (view instanceof MyImageView) {
			float xdisntance = calXdisntance(e1.getX(), e2.getX());
			float minDistance = screen_width / 6;
			if (xdisntance > minDistance) {
				keyEvent = isScrollLeft(e1, e2) ? KeyEvent.KEYCODE_DPAD_LEFT
						: KeyEvent.KEYCODE_DPAD_RIGHT;
			}
			imageView = (MyImageView) view;

			Matrix matrix = imageView.getImageMatrix();
			matrix.getValues(values);
			float left, right;
			float width = imageView.getScale() * imageView.getImageWidth();
			float height = imageView.getScale() * imageView.getImageHeight();
			if (((int) width <= screen_width)
					|| ((int) height <= screen_height)) {
				super.onScroll(e1, e2, distanceX, distanceY);
			} else {
				left = values[Matrix.MTRANS_X];
				right = left + width;
				Rect rect = new Rect();
				imageView.getGlobalVisibleRect(rect);
				if (distanceX > 0) { // 向左滑动
					if (rect.left > 0 || right < screen_width) {
						super.onScroll(e1, e2, distanceX, distanceY);
					} else {
						imageView.postTranslate(-distanceX, -distanceY);
					}
				} else {
					if (rect.right < screen_width || left > 0) {
						super.onScroll(e1, e2, distanceX, distanceY);
					} else {
						imageView.postTranslate(-distanceX, -distanceY);
					}
				}
			}
		} else {
			super.onScroll(e1, e2, distanceX, distanceY);
		}
		return false;
	}

	private boolean isScrollLeft(MotionEvent e1, MotionEvent e2) {
		return e2.getX() > e1.getX();
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (gestureDetector != null) {
			gestureDetector.onTouchEvent(event);
		}
		switch (event.getAction()) {
		case MotionEvent.ACTION_UP: {
			View view = PicGallery.this.getSelectedView();
			if (view instanceof MyImageView) {

				if (keyEvent != KEY_INVALID) {
					onKeyDown(keyEvent, null);
					keyEvent = KEY_INVALID;
				}
				
				imageView = (MyImageView) view;
				float width = imageView.getScale() * imageView.getImageWidth();
				float height = imageView.getScale() * imageView.getImageHeight();
				
				if ((int) width <= screen_width
						&& (int) height <= screen_height)// 如果图片当前大小<屏幕大小，判断边界
				{
					break;
				}
				
			}
		}
			break;
		default:
			break;
		}
		return super.onTouchEvent(event);
	}

	private float calXdisntance(float e1X, float e2X) {
		return Math.abs(e1X - e2X);
	}

}
