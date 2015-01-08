package com.xiao.demo.dboperate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @filename DBHelper.java
 * @TODO
 * @date 2014-5-6上午12:40:05
 * @Administrator 萧
 * 
 */
public class DBHelper extends SQLiteOpenHelper {

	// /** 修改原表名 */
	// private String CREATE_TEMP_PHARMACY = "alter table " +
	// DBConst.PHARMACY_RECORD_TABLENAME
	// + " rename to _temp_" + DBConst.PHARMACY_RECORD_TABLENAME;
	//
	// /** 转移数据 */
	// private String INSERT_DATA = "insert into " +
	// DBConst.PHARMACY_RECORD_TABLENAME
	// + " select *,'' from  _temp_" + DBConst.PHARMACY_RECORD_TABLENAME;
	//
	// /** 删除临时表 */
	// private String DROP_PHARMACY = "drop table _temp_" +
	// DBConst.PHARMACY_RECORD_TABLENAME;
	//
	// private String DROP_PHARMACYTEMP = "drop table " +
	// DBConst.PHARMACY_RECORD_TABLENAME;

	/** 创建用药提醒表 */
	public String createpharmacy = "create table if not exists  "
			+ DBConst.PHARMACY_RECORD_TABLENAME
			+ "( id integer primary key autoincrement , " + DBConst.USERID
			+ "  varchar not null,  " + DBConst.PHARMACY_TIME
			+ " char(6) not null, " + DBConst.PHARMACY + " text , "
			+ DBConst.PHARMACY_STATE + " integer default 1 ,"
			+ DBConst.PHARMACY_ADD_PAUSE + " integer default 0 ,"
			+ DBConst.PHARMACY_PARENTID + " integer default 0 ,"
			+ DBConst.PHARMACY_REMIND + " text not null);";

	/** 创建购物车表 */
	public String createSC = "create table if not exists  " + DBConst.SHOPCART_TABLENAME
			+ "( id integer primary key autoincrement , " + DBConst.USERID
			+ " varchar not null ,  " + DBConst.PRO_ID
			+ " varchar not null ,  " + DBConst.PRO_NAME + " varchar ,"
			+ DBConst.PRO_PRICE + " char(6) not null , " + DBConst.PRO_NUM
			+ " integer default 1 check(" + DBConst.PRO_NUM + " >0) ,  "
			+ DBConst.PRO_QUANTITY + " integer default 1 check("
			+ DBConst.PRO_QUANTITY + " >0)," + DBConst.PRO_SUM_PRICE
			+ " char(8) , " + DBConst.PROPIC + " varchar ,  "
			+ DBConst.PRO_STATE + " integer  default 1 , " + DBConst.PRO_TIME
			+ " char(20) ,  " + DBConst.PRO_ORDER_STATE
			+ " integer default 1);";

	/** 创建BMI记录表 */
	public String createBMI = " create table if not exists " + DBConst.BMI_TABLENAME
			+ " ( id integer primary key autoincrement , " + DBConst.USERID
			+ " varchar not null ,  " + DBConst.HEIGHT
			+ " varchar not null ,  " + DBConst.WEIGHT
			+ " varchar not null ,  " + DBConst.BMIVALUE
			+ " varchar not null ,  " + DBConst.RECORD_TIME
			+ " varchar not null);";

	public DBHelper(Context context) {
		this(context, DBConst.DATABASESNAME, null, DBConst.DATABASES_VERSION);
	}

	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);

	}
  
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(createpharmacy);
		db.execSQL(createSC);
		db.execSQL(createBMI);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		int updateVersion = oldVersion;
		if (2 == updateVersion || 3 == updateVersion) {
			db.beginTransaction();
			db.execSQL(createBMI);
			db.setTransactionSuccessful();
			db.endTransaction();
			updateVersion = 4;
		}
		if (updateVersion != newVersion) {
//			onCreate(db);
		}
	}
}