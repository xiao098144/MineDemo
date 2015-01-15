package com.xiao.androiddemo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiao.demo.bean.A;
import com.xiao.demo.bean.A.B;
import com.xiao.demo.bean.CollectShops;
import com.xiao.demo.bean.ShopGoodsBean;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class MainActivity extends Activity {

	TextView tv;
//	ImageView tv;

//	String  sa = "{\"count\":\"1\",\"store_array\":[{\"store_id\":\"3\",\"store_name\":\"\u661f\u661f\u7684\u5e97\u94fa\",\"owner_name\":\"\u7533\u6167\u4e3d\"}]}";
	
	// EditText et;
	// ImageButton btn;
	// LinearLayout layout;
	// private Tencent tencent;

	// WebView wv;
	// protected String APP_CACHE = "APPCACHE";

	// CyclicBarrier cyclicBarrier;
	// CountDownLatch countDownLatch;
	// CountDownTimer countDownTimer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv = (TextView) findViewById(R.id.tv);
//		tv = (ImageView) findViewById(R.id.tv);
//		ShopGoodsBean shopGoodsBean = new ShopGoodsBean();
//		shopGoodsBean.setDiscountPrice("123");
//		shopGoodsBean.setProBrand("proBrand");
//		shopGoodsBean.setProId("proId");
//		shopGoodsBean.setProImgFirst("imgFirst");
//		shopGoodsBean.setProInfo("proInfo");
//		shopGoodsBean.setProModel("proModel");
//		shopGoodsBean.setProMoreInfo("proMoreInfo");
//		shopGoodsBean.setProName("proName");
//		shopGoodsBean.setProPrice("proPrice");
//		shopGoodsBean.setProQuantity("proQuantity");
//		SharedPreferences sharedPreferences = getSharedPreferences("test", MODE_APPEND);
//		Editor edit = sharedPreferences.edit();
//		
//		try {
//			edit.putString("obj",serilize(shopGoodsBean));
//			edit.commit();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		tv.setText(shopGoodsBean.toString());
//		tv.setText(getFilesDir().getAbsolutePath());
//		tv.append("\n"+getCacheDir().getAbsolutePath());
//		tv.append("\n"+getApplicationContext().getPackageName());
//		tv.append("\n"+getFilesDir().getParentFile().getAbsolutePath());
//		tv.append("\n"+getFilesDir().getPath());
//		tv.append("\n");
	
//		try {
//			JSONObject jsonObject = new JSONObject(sa);
//			tv.append("count = "+jsonObject.optInt("count"));
//			JSONArray jsonArray = jsonObject.optJSONArray("store_array");
//			
//			for (int i = 0; i < jsonArray.length(); i++) {
//				JSONObject jsonObject2 = jsonArray.getJSONObject(i);
//				CollectShops collectShops = new CollectShops();
//				collectShops.setStore_id(jsonObject2.optLong("store_id"));
//				collectShops.setStore_name(jsonObject2.optString("store_name"));
//				collectShops.setOwner_name(jsonObject2.optString("owner_name"));
//				resultList.add(collectShops);
//			}
//			for (int i = 0; i < resultList.size(); i++) {
//				tv.append("\n "+resultList.get(i).toString());
//			}
//			
//			
//		} catch (Exception e) {
//			tv.setText("解析异常");
//		}
		
		
		
//		for (int i = 0; i < 10; i++) {
//			publicList.add("test"+i);
//		}
		
//		anim = (AnimationDrawable) tv.getBackground();
//		tv.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				if (anim.isRunning()) {
//					anim.stop();
//				} else {
//					anim.start();
//				}
//			}
//		});
		
//		new Handler().postDelayed(new Runnable() {
//			
//			@Override
//			public void run() {
//				if (anim != null && anim.isRunning()) {
//					anim.stop();
//				}
//			}
//		}, 3*1000);
//		
		
		
//		tv.setText(md5("",false)+" \n ");
//		tv.setTextIsSelectable(true);
//		tv.append("");
//		tv.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public boolean onClick(View arg0) {
//				Intent intent = new Intent();
//				intent.setClass(MainActivity.this, SecondAct.class);
//				startActivityForResult(intent, 220);
//				return false;
//			}
//		});
		
//		EditText eText = new EditText(this);
//		eText.setOnLongClickListener(new OnLongClickListener() {
//			
//			@Override
//			public boolean onLongClick(View arg0) {
//				// TODO Auto-generated method stub
//				return false;
//			}
//		});
		
		
//		tv.append("oncreate -- " + System.currentTimeMillis() + "\n");
//		if (null != savedInstanceState) {
//			tv.append("onCreate " + savedInstanceState.getString("test")
//					+ " -- " + System.currentTimeMillis() + "\n");
//		}
		
		
		tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, SecondAct.class);
				startActivityForResult(intent, 220);
			}
		});
//		tv.setText(tv.getTag()+"");
		// for (int i = 0; i < 10; i++) {
		// resultList.add("i"+i);
		// }
		// publicList.addAll(resultList);
		// tv.append(publicList.size()+"  "+publicList.toString());
		// resultList.clear();
		// tv.append("\n"+publicList.size()+"  "+publicList.toString());

		// tv.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View arg0) {
		// Intent intent = new Intent(Intent.ACTION_MAIN);
		// intent.addCategory(Intent.CATEGORY_HOME);
		// startActivity(intent);
		// }
		// });
		// List<A> generateData = generateData();
		// A generateData = generateData();
		// Gson gson = new Gson();
		// String json = gson.toJson(generateData);
		// A a = gson.fromJson(json, A.class);
		// tv.setText(a.toString());
		//
		// publicList.clear();

		// fun();
		// et = (EditText) findViewById(R.id.et);
		// et.addTextChangedListener(new Watcher(et));
		// layout = (LinearLayout) findViewById(R.id.layout);
		// layout.removeAllViews();
		//
		// PieChart pieChart = new PieChart(0,0, 0, 0);
		// final CategorySeries series = pieChart.getDataSet();
		// final DefaultRenderer renderer = pieChart.getPieRenderer();
		// final GraphicalView pieChartView = pieChart.getPieChartView(this ,
		// series , renderer);
		// pieChartView.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// SeriesSelection seriesAndPoint = pieChartView
		// .getCurrentSeriesAndPoint();
		// if (seriesAndPoint != null) {
		// for (int i = 0; i < series.getItemCount(); i++) {
		// renderer.getSeriesRendererAt(i).setHighlighted(i ==
		// seriesAndPoint.getSeriesIndex());
		// }
		// pieChartView.repaint();
		// Toast.makeText(MainActivity.this,
		// series.getCategory(seriesAndPoint.getSeriesIndex()),
		// Toast.LENGTH_SHORT).show();
		// }
		// }
		// });
		// ImageView img = new ImageView(this);
		// Bitmap sourcebitmap = Bitmap.createBitmap(300,
		// layout.getHeight(), Bitmap.Config.ARGB_4444);
		// pieChartView.draw(new Canvas(sourcebitmap));
		// img.setImageBitmap(sourcebitmap);
		// layout.addView(img, LayoutParams.MATCH_PARENT,
		// LayoutParams.MATCH_PARENT);
		// // layout.addView(new com.xiao.demo.view.PieChart(0, 0, 0, 0,
		// // this).getPieChartView());
		//
		// btn = (ImageButton) findViewById(R.id.btn);
		//
		// final String url =
		// "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxf64b757b1920559b&secret=6c9c4a14b5201138a2c68f275e53bf3c&code=0017935b25e65c55db1d4f948bac1473&grant_type=authorization_code";
		//
		// tencent = Tencent.createInstance("1101709937",
		// getApplicationContext());
		//
		// btn.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // Log.e("", tencent.isSessionValid()+"");
		// // if (!tencent.isSessionValid()) {
		// // tencent.login(MainActivity.this, "1101709937", new
		// // IUiListener() {
		// //
		// // @Override
		// // public void onError(UiError e) {
		// // Toast.makeText(
		// // MainActivity.this,
		// // "code:" + e.errorCode + ", msg:"
		// //
		// // + e.errorMessage + ", detail:"
		// // + e.errorDetail, Toast.LENGTH_SHORT)
		// // .show();
		// // }
		// //
		// // @Override
		// // public void onComplete(Object arg0) {
		// // Log.e("", arg0.toString());
		// // }
		// //
		// // @Override
		// // public void onCancel() {
		// // Toast.makeText(MainActivity.this, " onCancel ",
		// // Toast.LENGTH_SHORT).show();
		// // }
		// // });
		// // }
		// new Thread(new Runnable() {
		//
		// @Override
		// public void run() {
		// tv.setText(get(url));
		// }
		// }).start();
		//
		// }
		// });

		// Gson gson = new Gson();
		// try {
		// JSONObject jsonObject = new
		// JSONObject("{\"num\":11,\"recordList\":[{\"proName\":\"怡成5秒血糖试纸\",\"discountPrice\":60.0,\"proInfo\":\"怡成5秒血糖试纸50条桶装\",\"proMoreInfo\":\"5秒钟快速测量反应，得到测量结果更快、更准。超强防潮能力，便于保存 用血微量，仅需0.5微升，用血量更少 单条独立包装，方便个人使用采用虹吸进样方式，比滴血进样方式更科学，更简便 ，可抗多种药物元素对数据结果的干扰。\",\"proPrice\":145.0,\"proId\":6,\"proImgFirst\":\"http://www.ddoctor.cn/product/6/6_1.png\",\"proQuantity\":99,\"proModel\":\"50支/桶\",\"proBrand\":\"怡成\"},{\"proName\":\"怡成血糖仪（买试纸免费送）\",\"discountPrice\":50.0,\"proInfo\":\"怡成血糖仪5D-1\r\n?测试如光速，用血如“芝麻”\r\n?5秒显示结果，无需等待\r\n?用血量最少，几乎无痛\r\n【糖医生】新注册用户购买一盒血糖试纸，免费送血糖仪一台，每人限一台，数量有限，送完为止。\",\"proMoreInfo\":\"5秒钟快速测量反应，得到测量结果更快、更准。超强防潮能力，便于保存 用血微量，仅需0.5微升，用血量更少 单条独立包装，方便个人使用采用虹吸进样方式，比滴血进样方式更科学，更简便 ，可抗多种药物元素对数据结果的干扰。\",\"proPrice\":288.0,\"proId\":7,\"proImgFirst\":\"http://www.ddoctor.cn/product/7/7_4.png\",\"proQuantity\":467,\"proModel\":\"怡成血糖仪5D-1\",\"proBrand\":\"怡成\"},{\"proName\":\"安稳免条码血糖试条\",\"discountPrice\":78.0,\"proInfo\":\"三诺血糖仪是长沙三诺生物传感技术有限公司的国家创新基金项目支持的优质产品，“三诺公司”是湖南省由归国留学生人员组成研发团队的高新技术企业，三诺血糖仪上市以来，以其测试准确，操作简便试纸价廉，性能优越等\",\"proMoreInfo\":\"1、自动虹吸加样，使用方便； 2、自动开关机，操作简单； 3、采血量少、无痛感：每次仅需3μl，相当于1/10滴血； 4、独创的三电极试条，精确度高； 5、试条价格低廉，经济实惠； 试条单片包装，保存方便； 6、自动温度补偿，在各种气候条件下均能保证测试结果准确。\",\"proPrice\":98.0,\"proId\":8,\"proImgFirst\":\"http://www.ddoctor.cn/product/8/8_1.png\",\"proQuantity\":197,\"proModel\":\"50支/盒\",\"proBrand\":\"三诺\"},{\"proName\":\"安准血糖试条\",\"discountPrice\":69.0,\"proInfo\":\"血糖试条与采血针配套使用，本产品适用于安准型血糖仪\",\"proMoreInfo\":\"用于新鲜毛细血管全血、静脉全血葡萄糖测试，仅供体外检测，适用于安准血糖仪，商品详细信息请参考血糖试条说明书\",\"proPrice\":150.0,\"proId\":9,\"proImgFirst\":\"http://www.ddoctor.cn/product/9/9_1.png\",\"proQuantity\":99,\"proModel\":\"50支/盒\",\"proBrand\":\"三诺\"},{\"proName\":\"强生血糖仪稳豪倍易型送50试纸条\",\"discountPrice\":356.0,\"proInfo\":\"稳豪倍易型血糖仪\",\"proMoreInfo\":\"图像操作显示，易于读取屏幕结果，仅需1UL采血量，微痛，只要五秒即知结果\",\"proPrice\":386.0,\"proId\":11,\"proImgFirst\":\"http://www.ddoctor.cn/product/11/11_1.jpg\",\"proQuantity\":100,\"proModel\":\"稳豪倍易型血糖仪\",\"proBrand\":\"强生\"},{\"proName\":\"强生稳豪型血糖试纸\",\"discountPrice\":166.0,\"proInfo\":\"强生稳豪血糖试纸家用50片\",\"proMoreInfo\":\"本款试纸适用于稳豪血糖仪，稳豪倍优血糖仪，稳豪倍易血糖仪。 葡萄糖氧化酶技术，防干扰，瞬吸亲水膜，采样少又快，双工作电极，双保险。\",\"proPrice\":251.0,\"proId\":12,\"proImgFirst\":\"http://www.ddoctor.cn/product/12/12_5.jpg\",\"proQuantity\":98,\"proModel\":\"50片/盒\",\"proBrand\":\"强生\"},{\"proName\":\"罗氏全活力II型血糖仪加50条试纸\",\"discountPrice\":269.0,\"proInfo\":\"罗氏血糖仪活力型Ⅱ型\",\"proMoreInfo\":\"先进技术，测量结果准确可靠，餐前餐后标识，简单控制血糖，独有乐采采血笔，无痛好轻松。\",\"proPrice\":489.0,\"proId\":13,\"proImgFirst\":\"http://www.ddoctor.cn/product/13/13_1.jpg\",\"proQuantity\":100,\"proModel\":\"罗氏血糖仪活力型Ⅱ型\",\"proBrand\":\"罗氏\"},{\"proName\":\"罗氏全活力型血糖试纸50条\",\"discountPrice\":149.0,\"proInfo\":\"罗氏全活力型血糖试纸家用50片\",\"proMoreInfo\":\"罗氏罗康全活力型试纸适用于Accu-CHEK Active罗康全活力型血糖仪和所有乐康全系列血糖仪，独创“真测”技术，超强抗干扰，独有试纸单片铝箔包装，有效防止氧化和污染，令检测结果更加精准。\",\"proPrice\":398.0,\"proId\":14,\"proImgFirst\":\"http://www.ddoctor.cn/product/14/14_1.jpg\",\"proQuantity\":100,\"proModel\":\"50片/盒\",\"proBrand\":\"罗氏\"},{\"proName\":\"罗氏卓越型金锐血糖试纸\",\"discountPrice\":145.6,\"proInfo\":\"罗氏卓越型金锐血糖试纸50片\",\"proMoreInfo\":\"卓越金锐血糖试纸与卓越型血糖仪/罗氏卓越纤巧型血糖仪/罗氏卓越组合型血糖仪/罗氏智能型血糖仪检测系统配套使用，智能密码牌自动校正不同批号试纸，避免人为失误，自动开关机。取出试纸后立即盖严盒盖，不要污染试纸，防止受潮，以免影响测试精度。\",\"proPrice\":255.6,\"proId\":15,\"proImgFirst\":\"http://www.ddoctor.cn/product/15/15_1.jpg\",\"proQuantity\":199,\"proModel\":\"50片/盒\",\"proBrand\":\"罗氏\"},{\"proName\":\"强生稳豪型血糖试纸\",\"discountPrice\":86.0,\"proInfo\":\"强生稳豪血糖试纸家用25片\",\"proMoreInfo\":\"本款试纸适用于稳豪血糖仪，稳豪倍优血糖仪，稳豪倍易血糖仪。 葡萄糖氧化酶技术，防干扰，瞬吸亲水膜，采样少又快，双工作电极，双保险。\",\"proPrice\":138.0,\"proId\":16,\"proImgFirst\":\"http://www.ddoctor.cn/product/16/16_1.jpg\",\"proQuantity\":100,\"proModel\":\"25片/盒\",\"proBrand\":\"强生\"}],\"errMsg\":\"yes\",\"code\":1}");
		// tv.setText(gson.fromJson(jsonObject.toString(),
		// ShopGoodsListBean.class).toString());
		// } catch (JSONException e) {
		// e.printStackTrace();
		// }

		// wv = (WebView) findViewById(R.id.tv);
		// wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		//
		// WebSettings settings = wv.getSettings();
		// settings.setAppCachePath(getFilesDir().getAbsolutePath());
		// settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		// settings.setLoadWithOverviewMode(true);
		// settings.setAppCacheEnabled(true);
		// settings.setBuiltInZoomControls(true);
		// settings.setNeedInitialFocus(true);
		// settings.setCacheMode(WebSettings.LOAD_DEFAULT);
		// settings.setBuiltInZoomControls(true);
		// settings.setDatabaseEnabled(true);
		// settings.setUseWideViewPort(true);
		// settings.setLoadsImagesAutomatically(true);
		// Class classType;
		// Field field;
		// try {
		// classType = WebView.class;
		// field = classType.getDeclaredField("mZoomButtonsController");
		// field.setAccessible(true);
		// ZoomButtonsController mZoomButtonsController = new
		// ZoomButtonsController(
		// wv);
		// mZoomButtonsController.getZoomControls().setVisibility(View.GONE);
		// try {
		// field.set(wv, mZoomButtonsController);
		// } catch (IllegalArgumentException e) {
		// e.printStackTrace();
		// } catch (IllegalAccessException e) {
		// e.printStackTrace();
		// }
		// } catch (SecurityException e) {
		// e.printStackTrace();
		// } catch (NoSuchFieldException e) {
		// e.printStackTrace();
		// }
		//
		// settings.setAppCacheMaxSize(10);
		// settings.setJavaScriptEnabled(true);
		// settings.setJavaScriptCanOpenWindowsAutomatically(true);
		//
		// wv.setWebChromeClient(new AppCacheWebChromeClient());
		//
		// wv.setWebViewClient(new WebViewClient(){
		// @Override
		// public void onLoadResource(WebView pWebView, String pUrl) {
		// Log.e("APPCACHE", "onLoadResource: " + pUrl);
		// }
		//
		// @Override
		// public void onPageStarted(WebView pView, String pUrl, Bitmap pBit) {
		// Log.e(APP_CACHE,"onPageStarted: " + pUrl);
		//
		// // OK, we're on the correct thread here for callbacks
		// // If not (such as in various parts of the Activity lifecycle) these
		// WebStorage.* methods will never work
		// WebStorage.getInstance().getOrigins(new ValueCallback<Map> () {
		// @SuppressLint("NewApi")
		// @Override
		// public void onReceiveValue(Map map) {
		// for(Object key : map.keySet()) {
		//
		// if(Build.VERSION.SDK_INT >= 11) {
		// WebStorage.Origin origin = (WebStorage.Origin) map.get(key);
		// Log.e(APP_CACHE, String.format("Origin: %s Quota: %s Usage: %s",
		// origin.getOrigin(), origin.getQuota(), origin.getUsage()));
		//
		// } else {
		// Log.e(APP_CACHE, "Key: " + key + " Value: " + map.get(key));
		// }
		// }
		// }
		// });
		//
		// // This should blow away this individual origin's manifests
		// WebStorage.getInstance().deleteOrigin("http://mp.weixin.qq.com");
		//
		// // This should blow away everything, but does not on disk
		// WebStorage.getInstance().deleteAllData();
		//
		// }
		// });
		//
		//
		// // wv.loadData(data, mimeType, EncodingUtils.);
		// wv.loadUrl("http://mp.weixin.qq.com/s?__biz=MzA5NjM0NzYyMQ==&mid=200602733&idx=3&sn=b7a75527aa2f40889acfc9b759d66de4#rd");

		// countDownTimer = new CountDownTimer(60*1000, 990) {
		//
		// @Override
		// public void onTick(long millisUntilFinished) {
		//
		// }
		//
		// @Override
		// public void onFinish() {
		//
		// }
		// };

		// countDownLatch = new CountDownLatch(15);
		// // countDownLatch.countDown();
		// try {
		// countDownLatch.await();
		// tv.setText(countDownLatch.getCount()+"");
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }

	}
	
	
public static String getDeviceInfo(Context context) {
    try{
      org.json.JSONObject json = new org.json.JSONObject();
      android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context
          .getSystemService(Context.TELEPHONY_SERVICE);
  
      String device_id = tm.getDeviceId();
      
      android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager) context.getSystemService(Context.WIFI_SERVICE);
          
      String mac = wifi.getConnectionInfo().getMacAddress();
      json.put("mac", mac);
      
      if( TextUtils.isEmpty(device_id) ){
        device_id = mac;
      }
      
      if( TextUtils.isEmpty(device_id) ){
        device_id = android.provider.Settings.Secure.getString(context.getContentResolver(),android.provider.Settings.Secure.ANDROID_ID);
      }
      
      json.put("device_id", device_id);
      
      return json.toString();
    }catch(Exception e){
      e.printStackTrace();
    }
  return null;
}
                  
	
	private class Watcher implements TextWatcher {

		private EditText et;

		public Watcher(EditText et) {
			this.et = et;
		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub

		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			String editable = et.getText().toString();
			String str = stringFilter(editable.toString());
			if (!editable.equals(str)) {
				et.setText(str);
				et.setSelection(str.length());
			}
		}

		
		
		private String stringFilter(String str) {
			// 只允许字母和数字
			String regEx1 = "[^[1-9]d{1}+[0-9]d{13}+[0-9]||[x]d{1}]";
			String regEx2 = "[^[1-9]d{1}+[0-9]d{16}+[0-9]||[x]d{1}]";

			Pattern p1 = Pattern.compile(regEx1);
			// Pattern p2 = Pattern.compile(regEx2);
			Matcher m1 = p1.matcher(str);
			// if (m1.matches()) {
			//
			// }
			// Matcher m2 = p2.matcher(str);
			return m1.replaceAll("").trim();
		}

	}

	private final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5',
		'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public  String toHexString(byte[] b) { // String to byte
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
			sb.append(HEX_DIGITS[b[i] & 0x0f]);
		}
		return sb.toString();
	}
	
	public String md5(String s,boolean flag) {
		if(s==null)return "";
		try {
			MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
			digest.update(s.getBytes("utf-8"));
			byte messageDigest[] = digest.digest();

			String str=toHexString(messageDigest);
			if(flag==true){
				str=str.toUpperCase();
			}
			return str;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}
	
	// private void fun() {
	// if (Build.VERSION.SDK_INT <Build.VERSION_CODES.JELLY_BEAN) {
	//
	// } else {
	// tv.setBackground(null);
	// }
	//
	//
	// String a =
	// "[{\"id\":0,\"timeType\":\"早餐后\",\"recordTime\":\"2014-10-15 00:00:00\",\"sugarValue\":0.0},{\"id\":0,\"timeType\":\"午餐后\",\"recordTime\":\"2014-10-15 00:00:00\",\"sugarValue\":0.0},{\"id\":0,\"timeType\":\"晚餐后\",\"recordTime\":\"2014-10-15 00:00:00\",\"sugarValue\":0.0},{\"id\":0,\"timeType\":\"早餐后\",\"recordTime\":\"2014-10-14 00:00:00\",\"sugarValue\":0.0},{\"id\":0,\"timeType\":\"午餐后\",\"recordTime\":\"2014-10-14 00:00:00\",\"sugarValue\":0.0},{\"id\":0,\"timeType\":\"晚餐后\",\"recordTime\":\"2014-10-14 00:00:00\",\"sugarValue\":0.0},{\"id\":0,\"timeType\":\"早餐后\",\"recordTime\":\"2014-10-13 00:00:00\",\"sugarValue\":0.0},{\"id\":0,\"timeType\":\"午餐后\",\"recordTime\":\"2014-10-13 00:00:00\",\"sugarValue\":0.0},{\"id\":0,\"timeType\":\"晚餐后\",\"recordTime\":\"2014-10-13 00:00:00\",\"sugarValue\":0.0},{\"id\":0,\"timeType\":\"早餐后\",\"recordTime\":\"2014-10-12 00:00:00\",\"sugarValue\":0.0},{\"id\":0,\"timeType\":\"午餐后\",\"recordTime\":\"2014-10-12 00:00:00\",\"sugarValue\":0.0},{\"id\":0,\"timeType\":\"晚餐后\",\"recordTime\":\"2014-10-12 00:00:00\",\"sugarValue\":0.0},{\"id\":0,\"timeType\":\"早餐后\",\"recordTime\":\"2014-10-11 00:00:00\",\"sugarValue\":0.0},{\"id\":0,\"timeType\":\"午餐后\",\"recordTime\":\"2014-10-11 00:00:00\",\"sugarValue\":0.0},{\"id\":0,\"timeType\":\"晚餐后\",\"recordTime\":\"2014-10-11 00:00:00\",\"sugarValue\":0.0},{\"id\":0,\"timeType\":\"早餐后\",\"recordTime\":\"2014-10-10 00:00:00\",\"sugarValue\":0.0},{\"id\":0,\"timeType\":\"午餐后\",\"recordTime\":\"2014-10-10 00:00:00\",\"sugarValue\":0.0},{\"id\":0,\"timeType\":\"晚餐后\",\"recordTime\":\"2014-10-10 00:00:00\",\"sugarValue\":0.0},{\"id\":0,\"timeType\":\"早餐后\",\"recordTime\":\"2014-10-09 00:00:00\",\"sugarValue\":0.0},{\"id\":0,\"timeType\":\"午餐后\",\"recordTime\":\"2014-10-09 00:00:00\",\"sugarValue\":0.0},{\"id\":0,\"timeType\":\"晚餐后\",\"recordTime\":\"2014-10-09 00:00:00\",\"sugarValue\":0.0}]";
	// JSONArray jsonArray;
	// try {
	// jsonArray = new JSONArray(a);
	// List<RecordBean> list = new ArrayList<SugarValueResBean.RecordBean>();
	// for (int i = 0; i < jsonArray.length(); i++) {
	// RecordBean recordBean = new SugarValueResBean(). new RecordBean();
	// JSONObject jsonObject = jsonArray.getJSONObject(i);
	// recordBean.setId(jsonObject.optString("id"));
	// recordBean.setRecordTime(jsonObject.optString("timeType"));
	// recordBean.setTimeType(jsonObject.optString("recordTime"));
	// recordBean.setSugarValue(jsonObject.optString("sugarValue"));
	// list.add(recordBean);
	// }
	// SugarValueResBean sugarValueResBean = new SugarValueResBean();
	// sugarValueResBean.setAfterRecordList(list);
	// for (int i = 0; i < list.size(); i++) {
	// tv.append(list.get(i).toString());
	// }
	//
	// } catch (JSONException e) {
	// e.printStackTrace();
	// }
	//
	// }

	private A generateData() {
		// List<A> list = new ArrayList<A>();
		// for (int i = 0; i < 10; i++) {
		A a = new A();
		a.setId(0);
		List<B> recordList = new ArrayList<B>();
		for (int j = 0; j < 10; j++) {
			B b = a.new B();
			b.setName(" b - " + j);
			recordList.add(b);
		}
		a.setRecordList(recordList);
		// list.add(a);
		// }
		// return list;
		return a;
	}

	@Override
	protected void onResume() {
		super.onResume();
//		tv.append("onresume -- " + System.currentTimeMillis() + "\n");
	}

	@Override
	protected void onStop() {
		super.onStop();
//		tv.append("onstop -- " + System.currentTimeMillis() + "\n");
	}

	@Override
	protected void onPause() {
		super.onPause();
//		tv.append("onpause -- " + System.currentTimeMillis() + "\n");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
//		tv.append("ondestory -- " + System.currentTimeMillis() + "\n");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
//		tv.append("onrestart -- " + System.currentTimeMillis() + "\n");
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
//		tv.append("onRestoreInstanceState -- " + System.currentTimeMillis()
//				+ "\n");
//		tv.append(savedInstanceState.getString("test")
//				+ System.currentTimeMillis() + "\n");
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString("test", " 保存信息 " + System.currentTimeMillis());
//		tv.append("onSaveInstanceState -- " + System.currentTimeMillis() + "\n");
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
//		if (tv == null) {
//			tv = (TextView) findViewById(R.id.tv);
//		}
//		tv.append("onStart -- " + System.currentTimeMillis() + "\n");
	}

	AnimationDrawable anim;
	
	private List<String> publicList = new ArrayList<String>();
//	private List<String> resultList = new ArrayList<String>();
	private List<CollectShops> resultList = new ArrayList<CollectShops>();

	private String serilize(Object object) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(
				byteArrayOutputStream);
		objectOutputStream.writeObject(object);
		String serStr = byteArrayOutputStream.toString("ISO-8859-1");
		serStr = java.net.URLEncoder.encode(serStr, "UTF-8");
		objectOutputStream.close();
		byteArrayOutputStream.close();
		return serStr;
	}

	// private class AppCacheWebChromeClient extends WebChromeClient {
	//
	// @Override
	// public void onReachedMaxAppCacheSize(long spaceNeeded, long
	// totalUsedQuota, WebStorage.QuotaUpdater quotaUpdater) {
	// Log.e("APPCACHE", "onReachedMaxAppCacheSize reached, increasing space: "
	// + spaceNeeded);
	// quotaUpdater.updateQuota(spaceNeeded * 2);
	// }
	// }

	
	
	public static String get(String url) {
		StringBuffer sBuffer = new StringBuffer();

		try {
			URL u = new URL(url);
			InputStream in = null;
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type",
					"application/json; charset=utf-8");
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				byte[] buf = new byte[1024];
				in = conn.getInputStream();   
				for (int n; (n = in.read(buf)) != -1;) {
					sBuffer.append(new String(buf, 0, n, "UTF-8"));
				}
			}
			in.close();
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sBuffer.toString();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
//		Log.e("test", "requestCode = "+requestCode+"  resultCode = "+resultCode);
//		if (resultCode == 200) {
//			tv.setText(data.getStringExtra("test"));
//			Log.e("test", data.getStringExtra("test"));
//		}else {
//			tv.setText("resultCode = "+resultCode);
//		}
		
	}

}
