package com.xiao.customwidget.adapter;

import java.util.List;

import com.xiao.customwidget.widget.MyImageView;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

/**
 *@filename MyViewPagerAdapter.java
 *@TODO
 *@date 2015-1-19下午5:48:31
 *@Administrator 萧
 *
 */
public class MyViewPagerAdapter extends PagerAdapter{

	List<Integer> dataList;
	
	public MyViewPagerAdapter(List<Integer> dataList){
		this.dataList = dataList;
	}
	
	@Override
	public int getCount() {
		return dataList.size();
	}

	@Override
	public int getItemPosition(Object object) {
		// TODO Auto-generated method stub
		return super.getItemPosition(object);
	}

	@Override
	public float getPageWidth(int position) {
		// TODO Auto-generated method stub
		return super.getPageWidth(position);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		MyImageView imageView  = new MyImageView(container.getContext());
		imageView.setBackgroundResource(dataList.get(position));
		container.addView(imageView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		return imageView;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}
	
	@Override
	public boolean isViewFromObject(View view, Object obj) {
		
		return view == obj;
	}

}
