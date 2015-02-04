/*******************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.xiao.photoview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.xiao.photoview.widget.PhotoView;

/**
 * Lock/Unlock button is added to the ActionBar. Use it to temporarily disable
 * ViewPager navigation in order to correctly interact with ImageView by
 * gestures. Lock/Unlock state of ViewPager is saved and restored on
 * configuration changes.
 * 
 * Julia Zudikova
 */

public class ViewPagerActivity extends Activity {

	private static final String ISLOCKED_ARG = "isLocked";

	private ViewPager mViewPager;
	private TextView tv;
	public DisplayImageOptions options;

	private int cur_position;

	private SamplePagerAdapter adapter;

	private LoadingUtil loadingUtil;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_pager);
		mViewPager = (HackyViewPager) findViewById(R.id.view_pager);
		tv = (TextView) findViewById(R.id.tv_index);
		// setContentView(mViewPager);
		loadingUtil = LoadingUtil.getInstance();
		cur_position = getIntent().getIntExtra("position", 0);
		options = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.ic_launcher)
				.showImageOnFail(R.drawable.ic_launcher).cacheOnDisk(true)
				.imageScaleType(ImageScaleType.EXACTLY)
				.bitmapConfig(Bitmap.Config.RGB_565).build();
		adapter = new SamplePagerAdapter();
		mViewPager.setAdapter(adapter);
//		Animation animation = AnimationUtils.loadAnimation(this, R.anim.imageloading);
//		mViewPager.setAnimation(animation);
		mViewPager.setCurrentItem(cur_position);
		tv.setText((cur_position + 1) + "/" + adapter.getCount());
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				Log.e("",
						"onPageSelected " + arg0
								+ " mViewPager.getCurrentItem() = "
								+ mViewPager.getCurrentItem());
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				Log.e("", "onPageScrolled arg0 = " + arg0 + " arg1 = " + arg1
						+ " arg2 = " + arg2 + " mViewPager.getCurrentItem() = "
						+ mViewPager.getCurrentItem());

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				Log.e("",
						"onPageScrollStateChanged  arg0 = " + arg0
								+ " mViewPager.getCurrentItem() = "
								+ mViewPager.getCurrentItem());
				tv.setText((mViewPager.getCurrentItem() + 1) + "/"
						+ adapter.getCount());
			}
		});
	}

	class SamplePagerAdapter extends PagerAdapter {

		// private static final int[] sDrawables = {
		// R.drawable.p1,R.drawable.p2,R.drawable.p3,R.drawable.p4,R.drawable.p5,R.drawable.p6,R.drawable.p7,R.drawable.p8,R.drawable.p9,R.drawable.p10};

		private String url = "http://219.232.246.189:8080/upload/image/";
		private String[] sDrawables = { "1.png", "2.png", "3.png", "4.png",
				"5.png", "6.png", "7.png", "8.png", "9.png", "10.png", "11.png" };

		@Override
		public int getCount() {
			return sDrawables.length;
		}

		@Override
		public View instantiateItem(ViewGroup container, int position) {
			PhotoView photoView = new PhotoView(container.getContext());

			ImageLoader.getInstance().displayImage(url + sDrawables[position],
					photoView, options, new ImageLoadingListener() {

						@Override
						public void onLoadingStarted(String arg0, View arg1) {
							loadingUtil.startLoading(ViewPagerActivity.this);
							Log.e("ImageLoader", "onLoadingStarted  ");
						}

						@Override
						public void onLoadingFailed(String arg0, View arg1,
								FailReason arg2) {
							loadingUtil.finishLoading();
							Log.e("ImageLoader", "onLoadingFailed");
						}

						@Override
						public void onLoadingComplete(String arg0, View arg1,
								Bitmap arg2) {
							loadingUtil.finishLoading();
							Log.e("ImageLoader", "onLoadingComplete");
						}

						@Override
						public void onLoadingCancelled(String arg0, View arg1) {
							loadingUtil.finishLoading();
							Log.e("ImageLoader", "onLoadingCancelled");
						}
					});

			// Now just add PhotoView to ViewPager and return it
			container.addView(photoView, LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT);

			return photoView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

	}

}
