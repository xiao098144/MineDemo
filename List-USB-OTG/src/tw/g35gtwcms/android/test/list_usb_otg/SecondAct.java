package tw.g35gtwcms.android.test.list_usb_otg;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

public class SecondAct extends Activity {

	TextView tv;

	String path = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		tv = (TextView) findViewById(R.id.tv);

		File sdcard = Environment.getExternalStorageDirectory().getParentFile()
				.getParentFile();
		File[] files = sdcard.listFiles();
		long start = System.currentTimeMillis();
		int search_count = 0;
		int out_count = 0;
		int inner_count = 0;
		int end_count = 0;
		int result_count = 0;
		outer: for (int i = 0; i < files.length; i++) {
			out_count++;
			tv.append("文件名 " + files[i].getName() + " 路径 "
					+ files[i].getAbsolutePath() + "\n");
			if (files[i].getName().startsWith("u")) {
				inner_count++;
				tv.append("文件名 " + files[i].getName() + " 路径 "
						+ files[i].getAbsolutePath() + "\n");
				File[] files2 = files[i].listFiles();
				if (files2 != null) {
					for (int j = 0; j < files2.length; j++) {
						end_count++;
						if (files2[j].getName().equals("Test")) {
							result_count++;
							search_count++;
							File file = files2[j].getAbsoluteFile();
							if (file != null) {

								if (file.isDirectory()) {
									File[] files3 = file.listFiles();
									if (files3 != null) {
										for (int k = 0; k < files3.length; k++) {
											tv.append("文件名 "
													+ files3[k].getName()
													+ " 路径 "
													+ files3[k]
															.getAbsolutePath()
													+ "\n");
											if (files3[k].getName().endsWith("xls")) {
												path = files3[k].getAbsolutePath();
											}
										}
										break outer;
									}
								} else {
									tv.append("\n 搜索结束 结果 "
											+ file.getAbsolutePath());
									break outer;
								}
							}
						} else {
							continue;
						}
					}
				}
			} else {
				continue;
			}
		}
		if (path != null) {
			readExcel(path);
		}else {
			tv.append("指定目录下没有excel文件\n");
		}
		
		long end = System.currentTimeMillis();
		long duration = end - start;
		tv.append(" 搜索持续时间 " + duration + "毫秒" + " " + duration / 1000 + "秒\n");
		tv.append("search_count = " + search_count + " out_count = "
				+ out_count + " inner_count = " + inner_count + " end_count = "
				+ end_count + " result_count = " + result_count);
		// File root = Environment.getRootDirectory();
		// tv.append("SD卡存储路径" + sdcard.getAbsolutePath() + "\n");
		// tv.append("SD卡父目录 " + sdcard.getParentFile().getAbsolutePath() +
		// "\n");
		// tv.append("SD卡祖目录 "
		// + sdcard.getParentFile().getParentFile().getAbsolutePath()
		// + "\n");
		// tv.append("根目录 " + root.getAbsolutePath() + "\n");
		// File[] root_child = root.listFiles();
		// tv.append("根目录下文件数目 " + root_child.length + "\n");
		// tv.append("根目录下文件：\n");
		// for (int i = 0; i < root_child.length; i++) {
		// tv.append("文件名 " + root_child[i].getName() + " 路径 "
		// + root_child[i].getAbsolutePath() + "\n");
		// }
		//
		// File storage = new File("/storage");
		// tv.append(" 存储空间路径 " + storage.getAbsolutePath() + "\n");
		// File[] storage_child = storage.listFiles();
		// tv.append(" 存储空间子目录数" + storage_child.length + "\n");
		// tv.append("存储空间子目录： " + "\n");
		// for (int i = 0; i < storage_child.length; i++) {
		// tv.append("文件名" + storage_child[i].getName() + "路径"
		// + storage_child[i].getAbsolutePath() + "\n");
		// }

	}

	public void readExcel(String path) {
		try {

			// /**
			// * 后续考虑问题,比如Excel里面的图片以及其他数据类型的读取
			// **/
			// InputStream is = new FileInputStream(path);

			Workbook book = Workbook.getWorkbook(new File(path));
			book.getNumberOfSheets();
			// 获得第一个工作表对象
			Sheet sheet = book.getSheet(0);
			int Rows = sheet.getRows();
			int Cols = sheet.getColumns();
			tv.append("当前工作表的名字:" + sheet.getName()+"\n");
			tv.append("总行数:" + Rows+"\n");
			tv.append("总列数:" + Cols+"\n");
			
			for (int i = 0; i < Cols; ++i) {
				for (int j = 0; j < Rows; ++j) {
					// getCell(Col,Row)获得单元格的值
					tv.append(j+"行"+i+"列的数据"+(sheet.getCell(i, j)).getContents() + "\t\n");
				}
			}
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
