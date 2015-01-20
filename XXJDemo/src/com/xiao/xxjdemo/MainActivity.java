package com.xiao.xxjdemo;

import java.util.ArrayList;
import java.util.List;

import com.testin.agent.TestinAgent;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends Activity {

	ListView lv;
	private List<Test> dataList = new ArrayList<Test>();

	private void initData() {
		dataList.add(new Test("11", "荀晓敬的店1", "1，1", "12", 12, 1));
		dataList.add(new Test("11", "荀晓敬的店1", "1，2", "12", 13, 1));
		dataList.add(new Test("12", "荀晓敬的店2", "2，1", "12", 14, 2));
		dataList.add(new Test("11", "荀晓敬的店1", "1，3", "12", 15, 4));
		dataList.add(new Test("12", "荀晓敬的店2", "2，2", "12", 16, 4));
		dataList.add(new Test("15", "荀晓敬的店5", "5，1", "12", 1, 3));
		dataList.add(new Test("13", "荀晓敬的店3", "3，1", "12", 2, 3));
		dataList.add(new Test("14", "荀晓敬的店4", "4，1", "12", 3, 2));
		dataList.add(new Test("13", "荀晓敬的店3", "3，2", "12", 4, 2));
		dataList.add(new Test("11", "荀晓敬的店1", "1，4", "12", 5, 2));
		dataList.add(new Test("11", "荀晓敬的店1", "1，5", "12", 6, 1));
		dataList.add(new Test("19", "荀晓敬的店9", "9，1", "12", 7, 1));
		dataList.add(new Test("19", "荀晓敬的店9", "9，2", "12", 8, 6));
		dataList.add(new Test("11", "荀晓敬的店1", "1，6", "12", 9, 5));
		dataList.add(new Test("12", "荀晓敬的店2", "2，3", "12", 10, 3));
		dataList.add(new Test("11", "荀晓敬的店1", "1，7", "12", 1, 2));
	}

	ShopCartAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TestinAgent.init(this);
		lv = (ListView) findViewById(R.id.lv_shopcart);
		initData();
		adapter = new ShopCartAdapter(this);
		adapter.setData(dataList);
		lv.setAdapter(adapter);
	}

}
