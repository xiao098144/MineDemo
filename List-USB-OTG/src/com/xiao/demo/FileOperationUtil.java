package com.xiao.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 文件操作
 * 
 * @Package_Name : com.eastedge.safeinhand.utils
 * @ClassName: FileOperationUtil
 * @author lumeng
 * @date 2013-10-31 下午2:59:02
 * @version V1.0
 */
public class FileOperationUtil {
	/**
	 * 读取文件转为string
	 * 
	 * @param @param fa
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static  String getJsonInfo(File fa){
		try {
			StringBuilder sb = new StringBuilder();
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fa)));
			String temp = null;
			while((temp = br.readLine())!= null){
				sb.append(temp);
			}
			br.close();
			return sb.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取文件名
	 * 
	 * @param @param filepaht
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String getFileName(String filepaht) {
		return filepaht.substring(filepaht.lastIndexOf("/") + 1,
				filepaht.length());
	}

	/**
	 * 根据文件路径与文件名删除文件
	 * @param path
	 * @param fileName
	 */
	public static void deleteFile(String path, String fileName) {
		File file = new File(path, fileName);
		if (file.exists() && file.isFile()) {
			file.delete();
		}
	}
	
	/**
	 * 根据文件路径删除文件
	 * @param path
	 */
	public static void deleteFile(String path) {
		File file = new File(path);
		if (file.exists() && file.isFile()) {
			file.delete();
		}
	}
	
	/**
	 * 将文件数组排序，目录放在上面，文件在下面
	 * 
	 * @param file
	 * @return
	 */
	public static File[] sort(File[] file) {
		ArrayList<File> list = new ArrayList<File>();
		// 放入所有目录
		for (File f : file) {
			if (f.isDirectory()) {
				list.add(f);
			}
		}
		// 放入所有文件
		for (File f : file) {
			if (f.isFile()) {
				list.add(f);
			}
		}
		return list.toArray(new File[file.length]);
	}
	
	/**
	 * 删除文件夹
	 * 
	 * @param @param filepath
	 * @return void
	 * @throws
	 */
	public static void deleteFolder(String filepath) {
		File file = new File(filepath);
		if (file.exists()) {
			if (file.isDirectory()) {
				File[] listFiles = file.listFiles();
				for (File f : listFiles) {
					deleteFolder(f.getAbsolutePath());
				}
				file.delete();
			} else {
				file.delete();
			}
		}
	}
	
	public static class FileSortUtil {
		/**
		 * 按大小
		 * 
		 * @param @param fliePath
		 * @return void
		 * @throws
		 */
		public static void orderByLength( List<File> files) {  
	        Collections.sort(files, new Comparator<File>() {  
	            public int compare(File f1, File f2) {  
	                long diff = f1.length() - f2.length();  
	                if (diff > 0)  
	                    return 1;  
	                else if (diff == 0)  
	                    return 0;  
	                else  
	                    return -1;  
	            }  
	            public boolean equals(Object obj) {  
	                return true;  
	            }  
	        });  
	    }  
		
		/**
		 * 按name
		 * 
		 * @param @param fliePath
		 * @return void
		 * @throws
		 */
		public static void orderByName( List<File> files) {  
//		    List<File> files = Arrays.asList(new File(fliePath).listFiles());  
		    Collections.sort(files, new Comparator<File>() {  
		        public int compare(File o1, File o2) {  
		            if (o1.isDirectory() && o2.isFile())  
		                return -1;  
		            if (o1.isFile() && o2.isDirectory())  
		                return 1;  
		            return o1.getName().compareTo(o2.getName());  
		        }  
		    });  
		} 
		
		/**
		 * 按日期
		 * 
		 * @param @param fliePath
		 * @return void
		 * @throws
		 */
		public static void orderByDate(List<File> files) {  
			Collections.sort(files,new Comparator<File>(){  
		        public int compare(File f1, File f2) {  
		            long diff = f1.lastModified() - f2.lastModified();  
		            if (diff > 0)  
		                return 1;  
		            else if (diff == 0)  
		                return 0;  
		            else  
		                return -1;  
		        }  
		        public boolean equals(Object obj) {  
		            return true;  
		        }  
		    });  
		}  
	}
}
