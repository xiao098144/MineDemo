package com.xiao.demo.bean;

import java.io.Serializable;

/**
 *@filename ShopGoodsBean.java
 *@TODO
 *@date 2014-9-1上午10:08:42
 *@Administrator 萧
 *
 */
public class ShopGoodsBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String proName; // 商品名称
	private String proId; // 商品id
	private String proImgFirst;
	private String proInfo; // 商品信息
	private String proMoreInfo; // 商品详细信息
	private String discountPrice; // 商品折扣价
	private String proPrice; // 商品原价
	private String proQuantity; // 商品库存
	private String proBrand; // 商品品牌
	private String proModel; // 商品规格

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public void setProImgFirst(String proImgFirst) {
		this.proImgFirst = proImgFirst;
	}

	public String getProImgFirst() {
		return proImgFirst;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getProInfo() {
		return proInfo;
	}

	public void setProInfo(String proInfo) {
		this.proInfo = proInfo;
	}

	public String getProMoreInfo() {
		return proMoreInfo;
	}

	public void setProMoreInfo(String proMoreInfo) {
		this.proMoreInfo = proMoreInfo;
	}

	/** 折扣价 */
	public String getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(String discountPrice) {
		this.discountPrice = discountPrice;
	}

	/** 原价 */
	public String getProPrice() {
		return proPrice;
	}

	public void setProPrice(String proPrice) {
		this.proPrice = proPrice;
	}

	public void setProQuantity(String proQuantity) {
		this.proQuantity = proQuantity;
	}

	/**
	 * 商品库存
	 * 
	 * @return
	 */
	public String getProQuantity() {
		return proQuantity;
	}

	public void setProBrand(String proBrand) {
		this.proBrand = proBrand;
	}

	public String getProBrand() {
		return proBrand;
	}

	public void setProModel(String proModel) {
		this.proModel = proModel;
	}

	public String getProModel() {
		return proModel;
	}

	@Override
	public String toString() {
		return "ShopGoodsBean [proId=" + proId + ", proName=" + proName
				+ ", discountPrice=" + discountPrice + ", proPrice="
				+ proPrice + " proQuantity = " + proQuantity + "]";
	}

}