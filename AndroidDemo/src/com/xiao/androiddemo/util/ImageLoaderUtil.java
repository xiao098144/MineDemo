package com.xiao.androiddemo.util;

import com.xiao.androiddemo.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;


/**
 * 
 * @Description (图片异部下载公共类)
 * @author dsy
 * @date 2014-2-26 下午9:32:33
 */
public class ImageLoaderUtil {

	private AsyncImageLoader asyncImageLoader;
	private Context context;
	private AnimationDrawable anim;
	private Bitmap bmp;
	private Bitmap cachebmp;

	public ImageLoaderUtil(Context context , String path) {
		asyncImageLoader = new AsyncImageLoader(path);
		this.context = context;
	}

	/**
	 * 根据ImageView加载图片
	 * 
	 * @param url
	 * @param imageView
	 */
	public void loadImage(final String url, final ImageView imageView) {
		if (imageView.getTag() == null) {
			imageView.setTag(url);
		}
		loadImage(url, true, imageView);
	}

	public void loadImage(final String url, boolean isSaveImage,
			final ImageView imageView) {
		if (TextUtils.isEmpty(url)) {
			return;
		}

		// 如果缓存过就会从缓存中取出图像，ImageCallback接口中方法也不会被执行
		imageView.setImageDrawable(null);
		Drawable cacheImage = asyncImageLoader.loadDrawable(url, isSaveImage,
				new AsyncImageLoader.ImageCallback() {
					// 请参见实现：如果第一次加载url时下面方法会执行
					public void imageLoaded(Drawable imageDrawable) {
						if (imageView.getTag() != null
								&& imageView.getTag().equals(url)) {
							// anim.stop();
							imageView.setImageDrawable(imageDrawable);
						}
					}
				});
		if (cacheImage != null) {
			// anim.stop();
			imageView.setImageDrawable(cacheImage);
		}
	}

	public void loadImages(final String url, boolean isSaveImage,
			final ImageView imageView, final ImageView img_loading,
			final float screenWidth, final float screenHight) {
		if (TextUtils.isEmpty(url)) {
			return;
		}
		// if (context != null) {
		// TODO 开启动画
//		startLoading(img_loading);
		// }

		// 如果缓存过就会从缓存中取出图像，ImageCallback接口中方法也不会被执行
		Bitmap cacheImage = asyncImageLoader.loadDrawable1(url, isSaveImage,
				new AsyncImageLoader.ImageCallback1() {

					@Override
					public void imageLoaded(Bitmap bitmap) {
						if (imageView.getTag() != null
								&& imageView.getTag().equals(url)) {
							
							bmp = bitmap;
							
							
							
//							Matrix matrix = new Matrix();
//							int width = bitmap.getWidth();
//							int height = bitmap.getHeight();
//							if (width == 0 || height == 0) {
//								imageView
//										.setBackgroundResource(R.drawable.image1);
//							}
//							float scale = screenWidth / width;
//							float scaleHeight = screenHight / height;
//							if (scale < 1) {
//								scale = 1;
//							}
////							stopLoading(img_loading);
//							imageView.setImageBitmap(bitmap);
//							matrix.postScale(scale, scaleHeight);
//							imageView.setImageMatrix(matrix);
						}
					}

				});

		if (cacheImage != null) {
			Matrix matrix = new Matrix();
			int width = cacheImage.getWidth();
			int height = cacheImage.getHeight();
			if (width == 0 || height == 0) {
				imageView.setBackgroundResource(R.drawable.image1);
			}
			float scale = screenWidth / width;
			float scaleHeight = screenHight / height;
			if (scale < 1) {
				scale = 1;
			}
			if (scaleHeight < 1) {
				scaleHeight = 1;
			}
			cachebmp = cacheImage;
//			stopLoading(img_loading);
			imageView.setImageBitmap(cacheImage);
			matrix.postScale(scale, scaleHeight);
			imageView.setImageMatrix(matrix);
		}
	}

//	private void startLoading(ImageView img_loading) {
//		img_loading.setBackgroundResource(R.anim.imageloading);
//		anim = (AnimationDrawable) img_loading.getBackground();
//		anim.start();
//	}

//	private void stopLoading(ImageView img_loading) {
//		anim.stop();
//		img_loading.setVisibility(View.GONE);
//	}

	public void releaseRes(ImageView img) {
		img.setImageBitmap(null);
		if (bmp != null && !bmp.isRecycled()) {
			// 回收并且置为null
			bmp.recycle();
			bmp = null;
		}
		if (cachebmp != null && !cachebmp.isRecycled()) {
			// 回收并且置为null
			cachebmp.recycle();
			cachebmp = null;
		}
		System.gc();
	}

}
