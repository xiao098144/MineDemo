package com.xiao.voicedown;

import java.io.File;

import android.os.Environment;

/**
 * 鍚勭璺緞鑾峰彇绫�
 * 
 * @File_name: FilePathUtil.java
 * @Package_name: com.eastedge.safeinhand.utils
 * @Author lumeng
 * @Date : 2013-04-10涓嬪崍12:08:07
 * @Version 1.0
 */
public class FilePathUtil {
	/**
	 * 鑾峰彇宸ョ▼鍖呭悕(com.ab.cde)
	 *    
	 * @return
	 */
	private static String getProjectPackage() {
//		String packagename = FilePathUtil.class.getPackage().toString();
//		String[] split2 = packagename.split(" ");
//		String[] split = split2[1].split("[.]");
//		return split[0] + "." + split[1] + "." + split[2];
		// TODO 姣忎竴涓」鐩�
		return "com.xiao.voicedown";
	}

	/**
	 * 鑾峰彇宸ョ▼鍖呭悕绗笁涓瓧娈碉紙涓�埇涓洪」鐩悕绉帮級(com.ab.cde:cde涓洪」鐩悕绉�
	 * 
	 * @return
	 */
	public  static String getProjectName() {
		/*String packageName = FilePathUtil.class.getPackage().toString();
		String[] split = packageName.split("[.]");
		return split[2];*/
		return "voicedown";
	}

	/**
	 * 鍒ゆ柇sd鍗℃槸鍚﹀瓨鍦�
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
	 * 鏂囦欢澶硅矾寰勭殑鏍圭洰褰�
	 * 
	 * @return 濡傛灉sd鍗″瓨鍦ㄥ氨杩斿洖sd鐩綍锛屽鏋滀笉瀛樺湪灏辫繑鍥炲唴瀛樺寘鍚嶈矾寰�
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
	 * 鑾峰彇椤圭洰涓绘枃浠跺す璺緞锛屽苟鍒涘缓璇ユ枃浠跺す
	 * 
	 * @return 涓绘枃浠跺す璺緞
	 */
	public static String getMainRootPath() {
		String projectName = "voicedown";
		String mainRootPath = getRootPath() + "/" + projectName;
		File file = new File(mainRootPath); 
		if (!file.exists()) {
			file.mkdirs();
		}
		return mainRootPath;
	}

	/**
	 * 澶村儚鏈湴鍦板潃
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
	 * 鑾峰彇椤圭洰涓绘枃浠跺す涓嬪浘鐗囧瓨鏀剧殑璺緞锛屽苟鍒涘缓璇ユ枃浠跺す
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
	 * 鑾峰彇椤圭洰涓绘枃浠跺す涓嬩笅杞芥枃浠跺瓨鏀剧殑璺緞锛屽苟鍒涘缓璇ユ枃浠跺す
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
	 * 鑾峰彇椤圭洰涓绘枃浠跺す涓媗og鏃ュ織瀛樻斁鐨勮矾寰勶紝骞跺垱寤鸿鏂囦欢澶�
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
	 * 鑾峰彇椤圭洰涓绘枃浠跺す涓媗og鏃ュ織瀛樻斁鐨勮矾寰勶紝骞跺垱寤鸿鏂囦欢澶�
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
	 * 鑾峰彇cache璺緞
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
	 * 鑾峰彇椤圭洰涓绘枃浠跺す涓媗og鏃ュ織瀛樻斁鐨勮矾寰勶紝骞跺垱寤鸿鏂囦欢澶�
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
	 * 鑾峰彇鏂囦欢鍚�
	 * 
	 * @param path
	 * @return
	 */
	public static String getFileName(String path) {
		int start = path.lastIndexOf("/") + 1;
		return path.substring(start);
	}

	/**
	 * 鑾峰彇鏂囦欢鍚�
	 * 
	 * @param file
	 * @return
	 */
	public static String getFileName(File file) {
		String filePath = file.getAbsolutePath();
		return getFileName(filePath);
	}

}