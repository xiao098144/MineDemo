package com.example.voicedown;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.RemoteViews;
import android.widget.Toast;


/**
 * @filename UpdateDownLoadService.java
 * @TODO 版本更新服务
 * @date 2014-6-7下午10:58:56
 * @Administrator 萧
 * 
 */
public class UpdateDownLoadService extends Service {

	public static final String UPDATEACTION = "com.healthdata.updatedownload";

	public static final String Install_Apk = "Install_Apk";
	/******** download progress step *********/
	private static final int down_step_custom = 3;

	private static final int TIMEOUT = 10 * 1000;// 超时
	private static String down_url;
	private static final int DOWN_OK = 1;
	private static final int DOWN_ERROR = 0;

	private String app_name = "ddoctoruser";

	private NotificationManager notificationManager;
	private Notification notification;
	private RemoteViews contentView;

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	/**
	 * 方法描述：onStartCommand方法
	 * 
	 * @param Intent
	 *            intent, int flags, int startId
	 * @return int
	 * @see UpdateService
	 */
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		try {
			down_url = intent.getStringExtra("url");
			// create file,应该在这个地方加一个返回值的判断SD卡是否准备好，文件是否创建成功，等等！
			createFile(app_name);
			if (isCreateFileSucess == true) {
//				createNotification();
				createThread();
			} else {
				Toast.makeText(this, " 未找到SD卡 ", Toast.LENGTH_SHORT).show();
				stopSelf();
			}
		} catch (Exception e) {
			
		}
		return super.onStartCommand(intent, flags, startId);
	}

	/********* update UI ******/
	private final Handler handler = new Handler(new Handler.Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what) {
			case DOWN_OK:

				notification.flags = Notification.FLAG_AUTO_CANCEL;
				notification.defaults = Notification.DEFAULT_SOUND;
				Uri uri = Uri.fromFile(updateFile);
				Intent intent = new Intent(Intent.ACTION_VIEW);

				/********** 加这个属性是因为使用Context的startActivity方法的话，就需要开启一个新的task **********/
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

				intent.setDataAndType(uri,
						"application/vnd.android.package-archive");

				PendingIntent pi = PendingIntent.getActivity(
						getApplicationContext(), 0, intent, 0);
				notification.contentIntent = pi;
				notificationManager.notify(R.layout.activity_main,
						notification);
				notificationManager.cancel(R.layout.activity_main);
				Toast.makeText(getBaseContext(), " 下载完成，开始安装 ",
						Toast.LENGTH_SHORT).show();

				/***** 安装APK ******/
				installApk();
				stopSelf();
				break;

			case DOWN_ERROR:
				notification.flags = Notification.FLAG_AUTO_CANCEL;
				notification.defaults = Notification.DEFAULT_SOUND;
				notificationManager.notify(R.layout.activity_main,
						notification);
				notificationManager.cancel(R.layout.activity_main);
				Toast.makeText(getBaseContext(), " 下载失败 ", Toast.LENGTH_SHORT)
						.show();

				stopSelf();
				break;

			}
			return true;
		}
	});

	private void installApk() {
		// TODO Auto-generated method stub
		/********* 下载完成，点击安装 ***********/
		Uri uri = Uri.fromFile(updateFile);
		Intent intent = new Intent(Intent.ACTION_VIEW);

		/********** 加这个属性是因为使用Context的startActivity方法的话，就需要开启一个新的task **********/
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		intent.setDataAndType(uri, "application/vnd.android.package-archive");
		UpdateDownLoadService.this.startActivity(intent);
	}

	/**
	 * 方法描述：createThread方法, 开线程下载
	 * 
	 * @param
	 * @return
	 * @see UpdateService
	 */
	public void createThread() {
		new DownLoadThread().start();
	}

	private class DownLoadThread extends Thread {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message message = new Message();
			try {
				long downloadSize = downloadUpdateFile(down_url,
						updateFile.toString());
				if (downloadSize > 0) {
					// down success
					message.what = DOWN_OK;
					handler.sendMessage(message);
				}
			} catch (Exception e) {
				e.printStackTrace();
				message.what = DOWN_ERROR;
				handler.sendMessage(message);
			}
		}
	}

	/**
	 * 方法描述：createNotification方法
	 * 
	 * @param
	 * @return
	 * @see UpdateService
	 */
//	public void createNotification() {
//		notification = new Notification();
//		notification.icon = R.drawable.ic_launcher;
//		notification.tickerText = "版本更新下载";
//		notification.when = System.currentTimeMillis();
//
//		notification.flags = Notification.FLAG_ONGOING_EVENT;
//
//		/*** 自定义 Notification 的显示 ****/
//		contentView = new RemoteViews(getPackageName(),
//				R.layout.activity_main);
//		contentView.setTextViewText(R.id.action_settings, "下载已完成 ");
//		contentView.setTextViewText(R.id.notificationPercent, "0%");
//		contentView.setProgressBar(R.id.notificationProgress, 100, 0, false);
//		notification.contentView = contentView;
//		Intent intent = new Intent();
//		intent.setClass(this, PollingBroadcastReceiver.class);
//		PendingIntent pi = PendingIntent.getBroadcast(this, 0, intent,
//				Intent.FLAG_ACTIVITY_NEW_TASK);
//		notification.contentIntent = pi;
//		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//		notificationManager.notify(R.layout.layout_notification_item,
//				notification);
//	}

	/***
	 * down file
	 * 
	 * @return
	 * @throws MalformedURLException
	 */
	public long downloadUpdateFile(String down_url, String file)
			throws Exception {

		int down_step = down_step_custom;// 提示step
		int totalSize;// 文件总大小
		int downloadCount = 0;// 已经下载好的大小
		int updateCount = 0;// 已经上传的文件大小

		InputStream inputStream;
		OutputStream outputStream;

		URL url = new URL(down_url);
		HttpURLConnection httpURLConnection = (HttpURLConnection) url
				.openConnection();
		httpURLConnection.setConnectTimeout(TIMEOUT);
		httpURLConnection.setReadTimeout(TIMEOUT);
		// 获取下载文件的size
		totalSize = httpURLConnection.getContentLength();

		if (httpURLConnection.getResponseCode() == 404) {
			throw new Exception("fail!");
		}

		inputStream = httpURLConnection.getInputStream();
		outputStream = new FileOutputStream(file, false);// 文件存在则覆盖掉

		byte buffer[] = new byte[1024];
		int readsize = 0;

//		while ((readsize = inputStream.read(buffer)) != -1) {
//
//			outputStream.write(buffer, 0, readsize);
//			downloadCount += readsize;// 时时获取下载到的大小
//			/*** 每次增张3% **/
//			if (updateCount == 0
//					|| (downloadCount * 100 / totalSize - down_step) >= updateCount) {
//				updateCount += down_step;
//				// 改变通知栏
//				contentView.setTextViewText(R.id.notificationPercent,
//						updateCount + "%");
//				contentView.setProgressBar(R.id.notificationProgress, 100,
//						updateCount, false);
//				notification.contentView = contentView;
//				notificationManager.notify(R.layout.layout_notification_item,
//						notification);
//			}
//		}
		if (httpURLConnection != null) {
			httpURLConnection.disconnect();
		}
		inputStream.close();
		outputStream.close();

		return downloadCount;
	}

	public static File updateDir = null;
	public static File updateFile = null;

	public static boolean isCreateFileSucess;

	public static void createFile(String app_name) {

		if (android.os.Environment.MEDIA_MOUNTED.equals(android.os.Environment
				.getExternalStorageState())) {
			isCreateFileSucess = true;

			updateDir = new File(FilePathUtil.getDownloadPath());
			updateFile = new File(updateDir + "/" + app_name + ".apk");

			if (!updateDir.exists()) {
				updateDir.mkdirs();
			}
			if (!updateFile.exists()) {
				try {
					updateFile.createNewFile();
				} catch (IOException e) {
					isCreateFileSucess = false;
					e.printStackTrace();
				}
			}

		} else {
			isCreateFileSucess = false;
		}
	}

}
