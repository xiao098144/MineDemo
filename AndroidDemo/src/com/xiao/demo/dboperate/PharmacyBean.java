package com.xiao.demo.dboperate;

import java.io.Serializable;
import java.util.List;

/**
 * @package com.eastedge.sugardoctor_clint.data.model.respons
 * @filename PharmacyBean.java
 * @TODO 服药记录
 * @date 2014-4-28上午10:41:44
 * @Administrator 萧
 */
public class PharmacyBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private List<Pharmacy> medicalList; // 服用药物列表
	private String medicalTime; // 服药时间
	private String medicalRemind;// 提醒频率
	private int state; // 闹钟关闭标志位 0闭 1开
	/** 是否初始添加记录或者暂停增加记录 0是 1否 暂停增加的记录 闹钟关闭时 需删除 */
	private int add_pause; // 初始新增或是暂停闹钟临时增加
	/** parentId 父Id 即如果是暂停临时新增记录 该记录由哪条原纪录而来 默认为0即非临时数据 */
	private int parentid;

	public PharmacyBean() {
		super();

	}

	public String getMedicalRemind() {
		return medicalRemind;
	}

	public void setMedicalRemind(String medicalRemind) {
		this.medicalRemind = medicalRemind;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Pharmacy> getMedicalList() {
		return medicalList;
	}

	public void setMedicalList(List<Pharmacy> medicalList) {
		this.medicalList = medicalList;
	}

	/**
	 * 将MedicalList 转换成字符串信息
	 * 
	 * @return
	 */
	public String getMedicalListInfo() {
		List<Pharmacy> medicalList = getMedicalList();
		StringBuilder tmp = new StringBuilder();
		for (int i = 0; i < medicalList.size(); i++) {
			Pharmacy pharmacy = medicalList.get(i);
			System.out.println(pharmacy.toString() + "!!!!!");
			tmp.append(pharmacy.getMedicalName() + pharmacy.getMedicalNum()
					+ pharmacy.getMediacalUnit() + ", ");
		}
		return tmp.substring(0, tmp.length() - 2);
	}

	public String getMedicalTime() {
		return medicalTime;
	}

	public void setMedicalTime(String medicalTime) {
		this.medicalTime = medicalTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	/** 是否初始添加记录或者暂停增加记录 0是 1否 */
	public void setAdd_pause(int add_pause) {
		this.add_pause = add_pause;
	}

	/** 是否初始添加记录或者暂停增加记录 0是 1否 */
	public int getAdd_pause() {
		return add_pause;
	}

	/** parentId 父Id 即如果是暂停临时新增记录 该记录由哪条原纪录而来 默认为0即非临时数据 */
	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

	/** parentId 父Id 即如果是暂停临时新增记录 该记录由哪条原纪录而来 默认为0即非临时数据 */
	public int getParentid() {
		return parentid;
	}

	@Override
	public String toString() {
		return "PharmacyBean [id=" + id + ", medicalList=" + medicalList
				+ ", medicalTime=" + medicalTime + ", state=" + state
				+ ", add_pause=" + add_pause + ", parentid=" + parentid + "]";
	}

	public class Pharmacy implements Serializable { // 药品类
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String medicalId;
		private String medicalName;
		private String medicalNum; // 使用剂量、片数
		private String mediacalUnit; // 剂量单位 片、克、ml等

		public Pharmacy() {
			super();

		}

		/**
		 * 药品类
		 * 
		 * @param medicalId
		 * @param medicalName
		 * @param medicalNum
		 */
		public Pharmacy(String medicalId, String medicalName,
				String medicalNum, String medicalUnit) {
			super();

			this.medicalId = medicalId;
			this.medicalName = medicalName;
			this.medicalNum = medicalNum;
			this.mediacalUnit = medicalUnit;
		}

		public String getMedicalId() {
			return medicalId;
		}

		public void setMedicalId(String medicalId) {
			this.medicalId = medicalId;
		}

		public String getMedicalName() {
			return medicalName;
		}

		public void setMedicalName(String medicalName) {
			this.medicalName = medicalName;
		}

		public String getMedicalNum() {
			return medicalNum;
		}

		public void setMedicalNum(String medicalNum) {
			this.medicalNum = medicalNum;
		}

		public String getMediacalUnit() {
			return mediacalUnit;
		}

		public void setMediacalUnit(String mediacalUnit) {
			this.mediacalUnit = mediacalUnit;
		}

		@Override
		public String toString() {
			return "Pharmacy [medicalId=" + medicalId + ", medicalName="
					+ medicalName + ", medicalNum=" + medicalNum
					+ ", mediacalUnit=" + mediacalUnit + "]";
		}
	}
}