package com.xiao.demo.dboperate;

import java.util.ArrayList;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.xiao.demo.dboperate.PharmacyBean.Pharmacy;

/**
 *@filename ListJsonUtil.java
 *@TODO List<Pharmacy> 与 JSON 互相转换
 *@date 2014-5-6下午2:46:09
 *@Administrator 萧
 *
 */
public class ListJsonUtil {

	/**
	 * 将List集合转换为JSONArray 然后封装在JSONObject中
	 * @param list
	 * @return
	 */
	public static  String list2Json(List<Pharmacy>list){
		
		JSONArray array = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			Gson gson = new Gson();
			array.put(gson.toJson(list.get(i)));
		}
		JSONObject json = new JSONObject();
		try {
			json.put("array", array);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json.toString();
	}
	
	/**
	 * 将字符串转换为JSONObject 再取出其中封装的JSONArray 最后解析成List集合
	 * @param json_str
	 * @param kind
	 * @return
	 */
	public static List<Pharmacy> json2List(String json_str){
		try {
			JSONObject json = new JSONObject(json_str);
			JSONArray jsonArray = json.optJSONArray("array");
			List<Pharmacy>list = new ArrayList<PharmacyBean.Pharmacy>();
			for (int i = 0; i < jsonArray.length(); i++) {
				Pharmacy pharmacy = new PharmacyBean().new Pharmacy();
				Gson gson = new Gson();
				pharmacy = gson.fromJson(jsonArray.getString(i), Pharmacy.class);
				list.add(pharmacy);
			}
			return list;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	
}
