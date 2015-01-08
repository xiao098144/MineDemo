package com.xiao.androiddemo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.xiao.demo.bean.ShopGoodsBean;

/**
 * @filename SecondAct.java
 * @TODO
 * @date 2014-12-8上午11:42:01
 * @Administrator 萧
 * 
 */
public class SecondAct extends Activity {

	TextView tv;
	private double latitude;
	private double longitude;

	private ShopGoodsBean deSerialization(String str) throws IOException,
			ClassNotFoundException {
		String redStr = java.net.URLDecoder.decode(str, "UTF-8");
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
				redStr.getBytes("ISO-8859-1"));
		ObjectInputStream objectInputStream = new ObjectInputStream(
				byteArrayInputStream);
		ShopGoodsBean person = (ShopGoodsBean) objectInputStream.readObject();
		objectInputStream.close();
		byteArrayInputStream.close();
		return person;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 230 && resultCode == 201) {
			Intent intent = new Intent();
			intent.putExtra("test", data.getStringExtra("test"));
			setResult(200, intent);
			finish();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		tv = (TextView) findViewById(R.id.sec_tv);
		try {
			SharedPreferences sp = getSharedPreferences("test", MODE_PRIVATE);
			tv.setText(deSerialization(sp.getString("obj", null)).toString());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// tv.append("Second  onCreate -- " + System.currentTimeMillis() +
		// "\n");
		// tv.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View arg0) {
		// Intent intent = new Intent();
		// intent.setClass(SecondAct.this, ThirdAct.class);
		// startActivityForResult(intent, 230);
		// }
		// });
		// Log.e("执行顺序", "Second  onCreate -- " + System.currentTimeMillis()
		// + "\n");

		// String s =
		// "{\"Result\":\"0\",\"Number\":2,\"Group\":[\"26\",\"3155\"]}";
		// try {
		// JSONObject json = new JSONObject(s);
		// tv.setText(json.optString("Result") + "\n");
		// tv.append(json.optInt("Number") + "\n");
		// JSONArray optJSONArray = json.optJSONArray("Group");
		// tv.append(optJSONArray.optString(0) + "\n");
		// tv.append(optJSONArray.optString(1) + "\n");
		// } catch (JSONException e) {
		//
		// e.printStackTrace();
		// }
		getLoc();

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// tv.append("Second  onDestroy -- " + System.currentTimeMillis() +
		// "\n");
		Log.e("执行顺序", "Second  onDestroy -- " + System.currentTimeMillis()
				+ "\n");
	}

	@Override
	protected void onPause() {
		super.onPause();
		// tv.append("Second  onPause -- " + System.currentTimeMillis() + "\n");
		// Log.e("执行顺序", "Second  onPause -- " + System.currentTimeMillis() +
		// "\n");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		// tv.append("Second  onRestart -- " + System.currentTimeMillis() +
		// "\n");
		// Log.e("执行顺序", "Second  onRestart -- " + System.currentTimeMillis()
		// + "\n");
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
		// tv.append("Second  onRestoreInstanceState -- "
		// + System.currentTimeMillis() + "\n");
		Log.e("执行顺序",
				"Second  onRestoreInstanceState -- "
						+ System.currentTimeMillis() + "\n");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		// tv.append("Second  onResume -- " + System.currentTimeMillis() +
		// "\n");
		Log.e("执行顺序", "Second  onResume -- " + System.currentTimeMillis()
				+ "\n");
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		// tv.append("Second  onSaveInstanceState -- "
		// + System.currentTimeMillis() + "\n");
		Log.e("执行顺序",
				"Second  onSaveInstanceState -- " + System.currentTimeMillis()
						+ "\n");
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
//		if (tv == null) {
//			tv = (TextView) findViewById(R.id.sec_tv);
//		}
		// tv.append("Second  onStart -- " + System.currentTimeMillis() + "\n");
		Log.e("执行顺序", "Second  onStart -- " + System.currentTimeMillis() + "\n");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		// tv.append("Second  onStop -- " + System.currentTimeMillis() + "\n");
		Log.e("执行顺序", "Second  onStop -- " + System.currentTimeMillis() + "\n");
	}

	private void getLoc() {
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			Location location = locationManager
					.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			if (location != null) {
				latitude = location.getLatitude();
				longitude = location.getLongitude();
			}
		} else {
			LocationListener locationListener = new LocationListener() {

				// Provider的状态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
				@Override
				public void onStatusChanged(String provider, int status,
						Bundle extras) {

				}

				// Provider被enable时触发此函数，比如GPS被打开
				@Override
				public void onProviderEnabled(String provider) {

				}

				// Provider被disable时触发此函数，比如GPS被关闭
				@Override
				public void onProviderDisabled(String provider) {

				}

				// 当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
				@Override
				public void onLocationChanged(Location location) {
					if (location != null) {
						Log.e("Map",
								"Location changed : Lat: "
										+ location.getLatitude() + " Lng: "
										+ location.getLongitude());
					}
				}
			};
			locationManager
					.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
							1000, 0, locationListener);
			Location location = locationManager
					.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			if (location != null) {
				latitude = location.getLatitude(); // 经度
				longitude = location.getLongitude(); // 纬度
//				tv.setText(" 纬度： " + latitude + "\n" + " 经度： " + longitude);

			}
		}
	}

}
