package com.xiao.xxjdemo;

/**
 * Date:2015-1-20下午10:08:17 Author:Administrator TODO:TODO
 */
public class Test {

	private String shop_Id;
	private String shop_Name;
	private String goods_Id;
	private String goods_Name;
	private float goods_Price;
	private int goods_Num;

	public Test() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Test(String shop_Id, String shop_Name,String goods_Id, String goods_Name,
			float goods_Price, int goods_Num) {
		super();
		this.shop_Id = shop_Id;
		this.shop_Name = shop_Name;
		this.goods_Id = goods_Id;
		this.goods_Name = goods_Name;
		this.goods_Price = goods_Price;
		this.goods_Num = goods_Num;
	}

	public String getShop_Id() {
		return shop_Id;
	}

	public void setShop_Id(String shop_Id) {
		this.shop_Id = shop_Id;
	}

	public void setShop_Name(String shop_Name) {
		this.shop_Name = shop_Name;
	}
	public String getShop_Name() {
		return shop_Name;
	}
	
	public String getGoods_Id() {
		return goods_Id;
	}

	public void setGoods_Id(String goods_Id) {
		this.goods_Id = goods_Id;
	}

	public String getGoods_Name() {
		return goods_Name;
	}

	public void setGoods_Name(String goods_Name) {
		this.goods_Name = goods_Name;
	}

	public float getGoods_Price() {
		return goods_Price;
	}

	public void setGoods_Price(float goods_Price) {
		this.goods_Price = goods_Price;
	}

	public int getGoods_Num() {
		return goods_Num;
	}

	public void setGoods_Num(int goods_Num) {
		this.goods_Num = goods_Num;
	}

	@Override
	public String toString() {
		return "Test [shop_Id=" + shop_Id + ", shop_Name=" + shop_Name
				+ ", goods_Id=" + goods_Id + ", goods_Name=" + goods_Name
				+ ", goods_Price=" + goods_Price + ", goods_Num=" + goods_Num
				+ "]";
	}

}
