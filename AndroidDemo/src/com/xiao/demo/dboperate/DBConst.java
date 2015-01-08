package com.xiao.demo.dboperate;
/**
 *@filename DBConst.java
 *@TODO 数据库字段说明
 *@date 2014-5-7下午12:55:42
 *@Administrator 萧
 *
 */
public interface DBConst {

	/**	数据库字段说明*/
	/** 	数据库名 */
	public static final String DATABASESNAME = "client.db";
	/**	数据库版本初始为1		*/
	public static final int DATABASES_VERSION = 4;
	
	
	/** 用药记录表名	*/
	public static final String PHARMACY_RECORD_TABLENAME = "pharecord ";
	/** 提醒用药时间 HH:mm	*/
	public static final String PHARMACY_TIME = "time";
	
	/**
	 * 提醒频率
	 */
	public static final String PHARMACY_REMIND = "remind";
	/**	服用药物： 名称 加 剂量			*/
	public static final String PHARMACY = "pharmacy";
	/** 服用药物名称	*/
	public static final String PHARMACY_NAME = "pharmacy_name"; 
	/** 服用药物剂量  	*/
	public static final String PHARMACY_NUM = "pharmacy_num";
	/** 服用药物剂量	单位  即 片 、克 、ml等等 */
	public static final String PHARMACY_UNIT = "pharmacy_unit";
	/** 闹钟状态标志位 0闭 1开	*/
	public static final String PHARMACY_STATE = "state";  
	/** 是否初始添加记录或者暂停增加记录  0是   1否	暂停增加的记录  闹钟关闭时 需删除	*/
	public static final String PHARMACY_ADD_PAUSE = "add_pause"; 
	/** parentId 父Id 即如果是暂停临时新增记录  该记录由哪条原纪录而来   默认为0即非临时数据 	*/
	public static final String PHARMACY_PARENTID = "parentid";

	
	/** 购物车表名 id - userid - proid - proprice - pronum - prosum - propic - prostate	*/
	public static final String SHOPCART_TABLENAME = "shopcart";
	
	/** 用户id	*/
	public static final String USERID = "userId";
	/** 商品id	*/
	public static final String PRO_ID = "proId";
	/** 商品名称	*/
	public static final String PRO_NAME = "proName";
	/** 商品图片 存储图片本地相对路劲	 */
	public static final String PROPIC = "proPic"; 
	/** 购买数量	*/
	public static final String PRO_NUM = "proNum";
	/**	商品库存	*/
	public static final String PRO_QUANTITY = "proQuantity";
	/** 商品单价  服务器返回的折扣价	*/
	public static final String PRO_PRICE = "proPrice";			
	/** 商品总价	*/
	public static final String PRO_SUM_PRICE = "proSum";
	/** 商品状态 1正常  0删除	*/
	public static final String PRO_STATE = "proState";
	/** 添加到购物车时间	*/
	public static final String PRO_TIME = "proTime";
	/**  退出程序前 是否提交订单  默认为1 代表正常  退出前 购物车有数据置为-1  代表不正常   		*/
	public static final String PRO_ORDER_STATE = "proOrderState";
	
	/**	用户BMI指数记录	*/
	public static final String BMI_TABLENAME = "bmi";
	
	public static final String HEIGHT = "height";
	public static final String WEIGHT = "weight";
	/** BMI值	*/
	public static final String BMIVALUE = "bmivalue";
	public static final String RECORD_TIME = "record_time";
	
	
}
