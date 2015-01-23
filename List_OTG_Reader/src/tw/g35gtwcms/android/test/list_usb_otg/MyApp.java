package tw.g35gtwcms.android.test.list_usb_otg;

import android.app.Application;

import com.xiao.demo.CrashHandler;

/**
 *Date:2015-1-22下午11:16:16
 *Author:Administrator
 *TODO:TODO
 */
public class MyApp extends Application{

	@Override
	public void onCreate() {
		super.onCreate();
		CrashHandler crashHandler = CrashHandler.getInstance();
		crashHandler.init(getApplicationContext());
	}

	
	
}

		