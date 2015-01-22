package com.xiao.customwidget;

import java.util.ArrayList;
import java.util.List;

import com.xiao.customwidget.adapter.MyViewPagerAdapter;
import com.xiao.customwidget.widget.MyViewPager;

import android.app.Activity;
import android.os.Bundle;

/**
 *@filename ViewPagerAct.java
 *@TODO
 *@date 2015-1-19下午5:53:46
 *@Administrator 萧
 *
 */
public class ViewPagerAct extends Activity{

	MyViewPagerAdapter adapter;
	MyViewPager viewPager;
	List<Integer> dataList = new ArrayList<Integer>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		viewPager = new MyViewPager(this);
		setContentView(viewPager);
		dataList.add(R.drawable.p1);
		dataList.add(R.drawable.p2);
		dataList.add(R.drawable.p3);
		dataList.add(R.drawable.p4);
		dataList.add(R.drawable.p5);
		dataList.add(R.drawable.p6);
		adapter = new MyViewPagerAdapter(dataList);
		viewPager.setAdapter(adapter);
		
		
	}
	
}
