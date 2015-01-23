package com.xiao.demo;

import java.io.File;

import android.os.Environment;

/**
 * 各种路径获取类
 * 
 * @File_name: FilePathUtil.java
 */
public class FilePathUtil {
	/**
	 * 获取工程包名(com.ab.cde)
	 * 
	 * @return
	 */
	private static String getProjectPackage() {
//		String packagename = FilePathUtil.class.getPackage().toString();
//		String[] split2 = packagename.split(" ");
//		String[] split = split2[1].split("[.]");
//		return split[0] + "." + split[1] + "." + split[2];
		// TODO 每一个项目 
		return "com.xiao.demo";
	}

	/**
	 * 获取工程包名第三个字段（一般为项目名称）(com.ab.cde:cde为项目名称)
	 * 
	 * @return
	 */
	public  static String getProjectName() {
		/*String packageName = FilePathUtil.class.getPackage().toString();
		String[] split = packageName.split("[.]");
		return split[2];*/
		return "xiaodemo";
	}

	/**
	 * 判断sd卡是否存在
	 * 
	 * @return
	 */
	public static boolean isSDCardExist() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return true;
		}
		return false;
	}

	/**
	 * 文件夹路径的根目录
	 * 
	 * @return 如果sd卡存在就返回sd目录，如果不存在就返回内存包名路径
	 */
	public static String getRootPath() {
		if (isSDCardExist()) {
			
			File sdDir = Environment.getExternalStorageDirectory();
			return sdDir.getPath();
		} else {
			return "/data/data/" + getProjectPackage();
		}
	}

	/**
	 * 获取项目主文件夹路径，并创建该文件夹
	 * 
	 * @return 主文件夹路径
	 */
	public static String getMainRootPath() {
		String projectName = "xiaodemo";
		String mainRootPath = getRootPath() + "/" + projectName;
		File file = new File(mainRootPath); 
		if (!file.exists()) {
			file.mkdirs();
		}
		return mainRootPath;
	}

	/**
	 * 头像本地地址
	 * @return
	 */
	public static String getHeadPicPath(){
		String headPath = getMainRootPath()+"/headpic";
		File file = new File(headPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		return headPath;
	}
	
	/**
	 * 获取项目主文件夹下图片存放的路径，并创建该文件夹
	 * 
	 * @return
	 */
	public static String getImageCachePath() {
		String imagePath = getMainRootPath() + "/imagecache/";
		File file = new File(imagePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		return imagePath;
	}

	/**
	 * 获取项目主文件夹下下载文件存放的路径，并创建该文件夹
	 * 
	 * @return
	 */
	public static String getDownloadPath() {
		String downloadPath = getMainRootPath() + "/download/";
		File file = new File(downloadPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		return downloadPath;
	}

	/**
	 * 获取项目主文件夹下log日志存放的路径，并创建该文件夹
	 * 
	 * @return
	 */
	public static String getLogPath() {
		String logPath = getMainRootPath() + "/log/";
		File file = new File(logPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		return logPath;
	}

	/**
	 * 获取语音文件夹 
	 * @return File
	 */
	public static File getVoicePath(){
		String voicePath = getMainRootPath()+File.separator+"voice"+File.separator;
		File file = new File(voicePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		return file;
	}
	
	/**
	 * 获取项目主文件夹下log日志存放的路径，并创建该文件夹
	 * 
	 * @return
	 */
	public static String getDBFile() {
		String logPath = getMainRootPath() + "/database/";
		File file = new File(logPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		return logPath;
	}

	/**
	 * 获取cache路径
	 * 
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String getCachePath() {
		String cachePath = getMainRootPath() + "/cache/";
		File file = new File(cachePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		return cachePath;
	}

	/**
	 * 获取项目主文件夹下log日志存放的路径，并创建该文件夹
	 * 
	 * @return
	 */
	public static String getCrashLogPath() {
		String logPath = getMainRootPath() + "/crashlog/";
		File file = new File(logPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		return logPath;
	}

	/**
	 * 获取文件名
	 * 
	 * @param path
	 * @return
	 */
	public static String getFileName(String path) {
		int start = path.lastIndexOf("/") + 1;
		return path.substring(start);
	}

	/**
	 * 获取文件名
	 * 
	 * @param file
	 * @return
	 */
	public static String getFileName(File file) {
		String filePath = file.getAbsolutePath();
		return getFileName(filePath);
	}

}