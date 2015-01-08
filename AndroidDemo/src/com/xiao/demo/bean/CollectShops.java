package com.xiao.demo.bean;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

public class CollectShops implements Parcelable{
	private long store_id;
	private String store_name,address,store_logo, fans_count;
	private String owner_name;
	
	
	public CollectShops() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CollectShops(long store_id, String store_name, String address,
			String store_logo, String fans_count, String owner_name) {
		super();
		this.store_id = store_id;
		this.store_name = store_name;
		this.address = address;
		this.store_logo = store_logo;
		this.fans_count = fans_count;
		this.owner_name = owner_name;
	}


	
	public long getStore_id() {
		return store_id;
	}

	public void setStore_id(long store_id) {
		this.store_id = store_id;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStore_logo() {
		return store_logo;
	}

	public void setStore_logo(String store_logo) {
		this.store_logo = store_logo;
	}

	public String getFans_count() {
		return fans_count;
	}

	public void setFans_count(String fans_count) {
		this.fans_count = fans_count;
	}

	public String getOwner_name() {
		return owner_name;
	}

	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}

	@Override
	public String toString() {
		return "CollectShops [store_id=" + store_id + ", store_name="
				+ store_name + ", owner_name="
				+ owner_name + "]";
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
	
	

}
