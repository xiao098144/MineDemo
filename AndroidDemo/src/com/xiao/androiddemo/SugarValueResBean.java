package com.xiao.androiddemo;

import java.io.Serializable;

import java.util.List;

public class SugarValueResBean {

	public SugarValueResBean() {

	}

	private String code;
	private String errMsg;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	/** 餐前记录数 */
	private String beforeNum;
	/** 餐后记录数 */
	private String afterNum;
	/** 睡前纪录数 */
	private String sleepNum;
	private String midNightNum;
	private List<RecordBean> midNightRecordList;
	/** 餐前记录集合 */
	private List<RecordBean> beforeRecordList;
	/** 餐后记录集合 */
	private List<RecordBean> afterRecordList;
	/** 睡前纪录集合 */
	private List<RecordBean> sleepRecordList;

	public String getBeforeNum() {
		return beforeNum;
	}

	public void setBeforeNum(String beforeNum) {
		this.beforeNum = beforeNum;
	}

	public String getAfterNum() {
		return afterNum;
	}

	public void setAfterNum(String afterNum) {
		this.afterNum = afterNum;
	}

	public String getSleepNum() {
		return sleepNum;
	}

	public void setSleepNum(String sleepNum) {
		this.sleepNum = sleepNum;
	}

	public String getMidNightNum() {
		return midNightNum;
	}

	public void setMidNightNum(String midNightNum) {
		this.midNightNum = midNightNum;
	}

	public List<RecordBean> getMidNightRecordList() {
		return midNightRecordList;
	}

	public void setMidNightRecordList(List<RecordBean> midNightRecordList) {
		this.midNightRecordList = midNightRecordList;
	}

	public List<RecordBean> getBeforeRecordList() {
		return beforeRecordList;
	}

	public void setBeforeRecordList(List<RecordBean> beforeRecordList) {
		this.beforeRecordList = beforeRecordList;
	}

	public List<RecordBean> getAfterRecordList() {
		return afterRecordList;
	}

	public void setAfterRecordList(List<RecordBean> afterRecordList) {
		this.afterRecordList = afterRecordList;
	}

	public List<RecordBean> getSleepRecordList() {
		return sleepRecordList;
	}

	public void setSleepRecordList(List<RecordBean> sleepRecordList) {
		this.sleepRecordList = sleepRecordList;
	}

	public class RecordBean implements Serializable, Comparable<RecordBean> {

		public RecordBean() {

		}

		/**
			 * 
			 */
		private static final long serialVersionUID = 1L;
		/**
		 * 记录ID
		 */
		private String id;
		/**
		 * 血糖记录时间
		 */
		private String recordTime;
		/**
		 * 时间类型(餐前餐后)
		 */
		private String timeType;
		/**
		 * 血糖值
		 */
		private String sugarValue;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getRecordTime() {
			return recordTime;
		}

		public void setRecordTime(String recordTime) {
			this.recordTime = recordTime;
		}

		public String getTimeType() {
			return timeType;
		}

		public void setTimeType(String timeType) {
			this.timeType = timeType;
		}

		public String getSugarValue() {
			return sugarValue;
		}

		public void setSugarValue(String sugarValue) {
			this.sugarValue = sugarValue;
		}

		@Override
		public String toString() {
			return "RecordBean [id=" + id + ", recordTime=" + recordTime
					+ ", timeType=" + timeType + ", sugarValue=" + sugarValue
					+ "]";
		}

		/**
		 * @return 负数 if this instance is less than {@code another}; 正数 if this
		 *         instance is greater than 0 if this instance has the same
		 *         order as
		 */
		@Override
		public int compareTo(RecordBean another) {

			return getRecordTime().substring(0, 10).compareTo(
					another.getRecordTime().substring(0, 10));
		}
	}

	@Override
	public String toString() {
		return "SugarValueResBean [beforeNum=" + beforeNum + ", afterNum="
				+ afterNum + ", sleepNum=" + sleepNum + ", beforeRecordList="
				+ beforeRecordList + ", afterRecordList=" + afterRecordList
				+ ", sleepRecordList=" + sleepRecordList + "]";
	}

}