package com.xiao.androiddemo.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import android.util.Log;
import android.widget.ImageView;

import com.xiao.androiddemo.R;
import com.xiao.demo.bean.FileBean;

/**
 * @filename VoiceDownloadUtil.java
 * @TODO
 * @date 2015-1-8下午3:28:34
 * @Administrator 萧
 * 
 */
public class VoiceDownloadUtil {

	private static ExecutorService es;

	public static FileBean startDown(FileBean file) {
		if (isExists(file.getName(), file.getDownUrl().substring(file.getDownUrl().lastIndexOf(".")+1))) {
			file.setPath(updateFile.getAbsolutePath());
		} else {
			createFile(file.getName());
			if (isCreateFileSucess) {
				es = Executors.newFixedThreadPool(10);
				try {
					return downResult(file);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					es.shutdown();
				}
			}
		}
		
		return file;
	}
	
	public static void startDown1(FileBean file, ImageView img) {
		Log.e("", " 开始下载 ");
		if (isExists(file.getName() , file.getDownUrl().substring(file.getDownUrl().lastIndexOf(".")+1))) {
			img.setTag(R.id.tag_path, updateFile.getAbsolutePath());
//			file.setPath(updateFile.getAbsolutePath());
		} else {
			createFile(file.getName());
			if (isCreateFileSucess) {
				es = Executors.newFixedThreadPool(10);
				try {
					img.setTag(R.id.tag_path, downResult(file).getPath());
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					es.shutdown();
				}
			}
		}
	}

	public static FileBean downResult(FileBean file) throws Exception {
		Future<FileBean> submit = es
				.submit(new VoiceDownloadUtil().new DownCallable(file));
		return submit.get();
	}

	public class DownCallable implements Callable<FileBean> {

		private FileBean file;

		public DownCallable(FileBean file) {
			this.file = file;
			Log.e("", " DownCallable  ");
		}

		@Override
		public FileBean call() throws Exception {
			try {
				URL url = new URL(file.getDownUrl());
				InputStream inputStream;
				OutputStream outputStream;

				HttpURLConnection httpURLConnection = (HttpURLConnection) url
						.openConnection();
				httpURLConnection.setConnectTimeout(15 * 1000);
				httpURLConnection.setReadTimeout(15 * 1000);

				file.setFileSize(httpURLConnection.getContentLength());
				if (httpURLConnection.getResponseCode() == 404) {
					throw new Exception("fail!");
				}

				inputStream = httpURLConnection.getInputStream();
				outputStream = new FileOutputStream(
						updateFile.getAbsoluteFile(), false);// 文件存在则覆盖掉

				byte buffer[] = new byte[1024];
				int readsize = 0;

				while ((readsize = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, readsize);
					outputStream.flush();
				}
				if (httpURLConnection != null) {
					httpURLConnection.disconnect();
				}
				inputStream.close();
				outputStream.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			file.setPath(updateFile.getAbsolutePath());
			return file;
		}

	}

	public static File updateDir = null;
	public static File updateFile = null;

	public static boolean isCreateFileSucess;

	private static boolean isExists(String name , String suffix) {
		if (android.os.Environment.MEDIA_MOUNTED.equals(android.os.Environment
				.getExternalStorageState())) {
			updateDir = new File(FilePathUtil.getCachePath());
			if (!updateDir.exists()) {
				updateDir.mkdirs();
			}
			updateFile = new File(updateDir + "/" + name + suffix);
			return updateFile.exists();
		}
		return false;
	}

	public static void createFile(String name) {
		if (android.os.Environment.MEDIA_MOUNTED.equals(android.os.Environment
				.getExternalStorageState())) {
			isCreateFileSucess = true;
			Log.e("", " 创建文件 ");
			try {
				updateFile.createNewFile();
			} catch (IOException e) {
				isCreateFileSucess = false;
				e.printStackTrace();
			}
		} else {
			isCreateFileSucess = false;
		}
	}

}
