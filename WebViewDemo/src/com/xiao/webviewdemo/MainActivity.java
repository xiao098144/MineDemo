package com.xiao.webviewdemo;

import java.util.Map;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView tv;
	private WebView wv;
//	String url = "http://mp.weixin.qq.com/s?__biz=MzA4ODc5OTEyNA==&mid=200362987&idx=2&sn=e714c54dbae62d56e273f44578a065ff#rd";
	String url = null;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		wv = (WebView) findViewById(R.id.wv);
		tv = (TextView) findViewById(R.id.tv);
		url = getIntent().getStringExtra("url");
		tv.setText("链接地址 "+url);
		wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

		WebSettings settings = wv.getSettings();
		settings.setAppCachePath(getFilesDir().getAbsolutePath());
		settings.setAppCacheEnabled(true);

		// settings.setAppCacheMaxSize(10);

		// settings.setBuiltInZoomControls(true);

		settings.setCacheMode(WebSettings.LOAD_DEFAULT);

		settings.setDatabaseEnabled(true);
		settings.setDefaultTextEncodingName("UTF-8");
		/**		使用缓存 则 必须打开*/
		settings.setDomStorageEnabled(true);

		// settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		// settings.setLayoutAlgorithm(LayoutAlgorithm.NORMAL);

		settings.setLoadsImagesAutomatically(true);

		settings.setNeedInitialFocus(true);
		/**  将图片加载放到最后再加载 提高整体渲染速度		*/
		settings.setBlockNetworkImage(false);

//		 settings.setUseWideViewPort(true);
		
		 settings.setLoadWithOverviewMode(true);

//		 settings.setSupportZoom(false);
		
		settings.setJavaScriptEnabled(true);  // 只是JS 关系图片加载
		settings.setJavaScriptCanOpenWindowsAutomatically(false);

		wv.setWebChromeClient(new AppCacheWebChromeClient());

		wv.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);

			}

			@Override
			public void onLoadResource(WebView pWebView, String pUrl) {
				Log.e("APPCACHE", "onLoadResource: " + pUrl);
//				pWebView.loadUrl(pUrl);
			}

			@SuppressWarnings("rawtypes")
			@Override
			public void onPageStarted(WebView pView, String pUrl, Bitmap pBit) {
				Log.e(" ��Ѷ���� ", "onPageStarted: " + pUrl);

				// OK, we're on the correct thread here for callbacks
				// If not (such as in various parts of the Activity lifecycle)
				// these WebStorage.* methods will never work
				WebStorage.getInstance().getOrigins(new ValueCallback<Map>() {
					@TargetApi(Build.VERSION_CODES.HONEYCOMB)
					@Override
					public void onReceiveValue(Map map) {
						for (Object key : map.keySet()) {

							if (Build.VERSION.SDK_INT >= 11) {
								WebStorage.Origin origin = (WebStorage.Origin) map
										.get(key);
								Log.e("Webview",String.format(
										"Origin: %s Quota: %s Usage: %s",
										origin.getOrigin(), origin.getQuota(),
										origin.getUsage()));

							} else {
								Log.e("Webview", "Key: " + key + " Value: "
										+ map.get(key));
							}
						}
					}
				});

				// This should blow away this individual origin's manifests
				// WebStorage.getInstance().deleteOrigin("http://mp.weixin.qq.com");

				// This should blow away everything, but does not on disk
				WebStorage.getInstance().deleteAllData();

			}
		});
		wv.loadUrl(url);

	}

	private class AppCacheWebChromeClient extends WebChromeClient {

		@SuppressWarnings("deprecation")
		@Override
		public void onReachedMaxAppCacheSize(long spaceNeeded,
				long totalUsedQuota, WebStorage.QuotaUpdater quotaUpdater) {
			Log.e("APPCACHE",
					"onReachedMaxAppCacheSize reached, increasing space: "
							+ spaceNeeded);
			quotaUpdater.updateQuota(spaceNeeded * 2);
		}

		// @Override
		// public void onProgressChanged(WebView view, int newProgress) {
		// super.onProgressChanged(view, newProgress);
		// setTitle("���ڼ����С�����");
		// setProgress(newProgress*100);
		// }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
