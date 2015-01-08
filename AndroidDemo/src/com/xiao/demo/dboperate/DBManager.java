package com.xiao.demo.dboperate;

import java.io.File;
import java.util.ArrayList;

import java.util.List;

import com.xiao.demo.dboperate.PharmacyBean.Pharmacy;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @filename DBManager.java
 * @TODO 本地数据库管理
 * @date 2014-5-6上午1:04:43
 * @Administrator 萧
 * 
 */

public class DBManager {

	private DBHelper helper;
	private SQLiteDatabase db;

	/** 查找所有闹钟记录 */
	private String getAlarmList = "select * from " + DBConst.PHARMACY_RECORD_TABLENAME + " where "
			+ DBConst.USERID + " = ? and " + DBConst.PHARMACY_ADD_PAUSE + " = ?";

	/** 根据时间查找闹钟记录 HH:mm */
	private String getPharmacyByTime = "select * from " + DBConst.PHARMACY_RECORD_TABLENAME
			+ " where " + DBConst.PHARMACY_TIME + " = ?  and " + DBConst.USERID + " = ?;";

	/** 根据id修改 time -remind - pharmacy - state - id - userid */
	private String updateRecordById = "update " + DBConst.PHARMACY_RECORD_TABLENAME + " set "
			+ DBConst.PHARMACY_TIME + " = ? , " + DBConst.PHARMACY + " = ? , "
			+ DBConst.PHARMACY_STATE + " = ? , " + DBConst.PHARMACY_REMIND + " = ?  "
			+ "where id = ? and " + DBConst.USERID + " = ?";

	/** 根据id 关闭闹钟 state - id - userid */
	public String shutAlarm = "update " + DBConst.PHARMACY_RECORD_TABLENAME + " set "
			+ DBConst.PHARMACY_STATE + " = ? where id = ? and " + DBConst.USERID + " = ?";

	/** 添加用药提醒闹钟 userId - time - remind - pharmacy - state add_pause parentid */
	public String addAlarmRecord = "insert into " + DBConst.PHARMACY_RECORD_TABLENAME + "("
			+ DBConst.USERID + " , " + DBConst.PHARMACY_TIME + " , " + DBConst.PHARMACY + " , "
			+ DBConst.PHARMACY_STATE + " , " + DBConst.PHARMACY_ADD_PAUSE + " , "
			+ DBConst.PHARMACY_PARENTID + "," + DBConst.PHARMACY_REMIND
			+ "  ) values( ?, ? , ? , ? ,? , ?, ?) ";

	/** 根据Id删除用药提醒闹钟 id -userid */
	public String delRecordById = " delete from " + DBConst.PHARMACY_RECORD_TABLENAME
			+ " where id = ?  and " + DBConst.USERID + " = ?;";

	private Context context;
	public DBManager(Context context) {
		helper = new DBHelper(context);
		this.context = context;
	}

	/**
	 * 查找所有用药提醒闹钟记录 Userid
	 * 
	 * @return
	 */
	public List<PharmacyBean> getAlarmList(String userId) {

		List<PharmacyBean> list = new ArrayList<PharmacyBean>();
		openDB(false);
		Cursor cursor = db.rawQuery(getAlarmList, new String[] { userId, "0" });
		while (cursor.moveToNext()) {
			PharmacyBean pharmacyBean = new PharmacyBean();
			pharmacyBean.setId(cursor.getInt(cursor.getColumnIndex("id")));
			pharmacyBean.setMedicalTime(cursor.getString(cursor
					.getColumnIndex(DBConst.PHARMACY_TIME)));
			pharmacyBean.setMedicalRemind(cursor.getString(cursor
					.getColumnIndex(DBConst.PHARMACY_REMIND)));
			String pharmacy_str = cursor.getString(cursor.getColumnIndex(DBConst.PHARMACY));
			List<Pharmacy> pharmacy_list = ListJsonUtil.json2List(pharmacy_str);
			pharmacyBean.setMedicalList(pharmacy_list);
			list.add(pharmacyBean);
		}
		if (cursor != null) {
			cursor.close();
		}
		closeDB();
		return list;
	}

	/**
	 * 根据时间查询用药提醒记录
	 * 
	 * @param time
	 * @return
	 */
	public List<PharmacyBean> getPharmacyBeanByTime(String time, String userId) {
		openDB(false);
		List<PharmacyBean> list = new ArrayList<PharmacyBean>();
		Cursor cursor = db.rawQuery(getPharmacyByTime, new String[] { time, userId });
		PharmacyBean pharmacyBean = null;
		while(cursor.moveToNext()) {
			pharmacyBean = new PharmacyBean();
			pharmacyBean.setId(cursor.getInt(cursor.getColumnIndex("id")));
			pharmacyBean.setMedicalTime(cursor.getString(cursor
					.getColumnIndex(DBConst.PHARMACY_TIME)));
			pharmacyBean.setMedicalRemind(cursor.getString(cursor
					.getColumnIndex(DBConst.PHARMACY_REMIND)));
			String pharmacy_str = cursor.getString(cursor.getColumnIndex(DBConst.PHARMACY));
			List<Pharmacy> pharmacy_list = ListJsonUtil.json2List(pharmacy_str);
			pharmacyBean.setMedicalList(pharmacy_list);
			list.add(pharmacyBean);
		}
		if (cursor != null) {
			cursor.close();
		}
		closeDB();
		
		return list;
	}

	/**
	 * 添加用药提醒闹钟记录 初始添加 pharmacyBean.getId() 为 0
	 * 
	 * @param pharmacyBean
	 * @return
	 */
	public boolean addAlarmRecord(PharmacyBean pharmacyBean, String userId) {

		boolean falg = false;
		openDB(true);
		try {

			db.execSQL(
					addAlarmRecord,
					new Object[] { userId, pharmacyBean.getMedicalTime(),

					ListJsonUtil.list2Json(pharmacyBean.getMedicalList()), pharmacyBean.getState(),
							pharmacyBean.getAdd_pause(), pharmacyBean.getParentid(),
							pharmacyBean.getMedicalRemind() });
			falg = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return falg;

	}

	/**
	 * 根据id修改记录
	 * 
	 * @param pharmacyBean
	 * @return
	 */
	public boolean updateRecordById(PharmacyBean pharmacyBean, String userId) {
		boolean falg = false;
		openDB(true);
		try {
			db.execSQL(
					updateRecordById,
					new Object[] { pharmacyBean.getMedicalTime(),
							ListJsonUtil.list2Json(pharmacyBean.getMedicalList()),
							pharmacyBean.getState(), pharmacyBean.getMedicalRemind(),
							pharmacyBean.getId(), userId });
			falg = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return falg;
	}

	/**
	 * 根据id 删除记录
	 * 
	 * @param id
	 * @return
	 */
	public boolean delRecordById(int id, String userId) {
		boolean falg = false;
		openDB(true);
		try {
			db.execSQL(delRecordById, new Object[] { id, userId });
			falg = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return falg;
	}

	/**
	 * 如果 add_pause 为1 即临时新增 则删除相应记录并修改parentId的状态 为0直接修改状态
	 * 
	 * @param id
	 * @param userId
	 * @param add_pause
	 */
	public void shutAlarmById(PharmacyBean phaBean, String userId) {
		int id = phaBean.getId();
		if (phaBean.getAdd_pause() == 1) {
			id = phaBean.getParentid();
			delRecordById(id, userId);
		}
		openDB(true);
		db.execSQL(shutAlarm, new Object[] { 0, id, userId });
		closeDB();
	}

	/**
	 * 根据falg 值 决定 打开可读写或只读数据库
	 * @param falg
	 */
	public void  openDB(boolean falg) {
		closeDB();
		if (null == db) {
//			if (falg) {
//				db = helper.getWritableDatabase();
//			} else {
//				db = helper.getReadableDatabase();
//			}
			File dir = context.getDir("databases",0);
			db = SQLiteDatabase.openDatabase("", null, SQLiteDatabase.OPEN_READWRITE);
		}
	}

	public void closeDB() {
		if (db!= null && db.isOpen()) {
			db.close();
		}
		db = null;
	}

}