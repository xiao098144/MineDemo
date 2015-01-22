package com.xiao.demo.util;

import java.io.ByteArrayInputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.HashMap;

import java.util.Map;
import java.util.Map.Entry;

import android.content.Context;
import android.content.SharedPreferences;


/******************
 * 
 * @项目名:SugarDoctor_Clint
 * @类名:SharePreferenUtil.java
 * @类描述:SharePreferenss 工具类
 * @date:2014-4-3
 * @Version:1.0
 * 
 ***************************************** 
 */
public class SharePreferenUtil {
	private SharedPreferences sp;
	private SharedPreferences.Editor editor;

	private Context context;

	public SharePreferenUtil(Context context, String name) {
		this.context = context;
		sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
	}

	private static final String RECORD_TYPE = "pref_key_record_type";

	private static final String ENABLE_HIGH_QUALITY = "pref_key_enable_high_quality";

	private static final String ENABLE_SOUND_EFFECT = "pref_key_enable_sound_effect";

	public boolean isHighQuality() {
		return sp.getBoolean(ENABLE_HIGH_QUALITY, true);
	}

	public boolean isEnabledSoundEffect() {
		return sp.getBoolean(ENABLE_SOUND_EFFECT, true);
	}

	/**
	 * 默认的为false
	 * 
	 * @param str
	 * @return
	 */
	public boolean getBoolean(String str) {
		return sp.getBoolean(str, false);
	}

	/**
	 * 默认的为 0
	 * 
	 * @param str
	 * @return
	 */
	public int getInt(String str) {
		return sp.getInt(str, 0);
	}

	public int getInt(String str, int defaultValue) {
		return sp.getInt(str, defaultValue);
	}

	/**
	 * 默认的为 ""
	 * 
	 * @param str
	 * @return
	 */
	public String getString(String str) {
		return sp.getString(str, "");
	}

	/**
	 * 默认为0L
	 * 
	 * @param str
	 * @return
	 */
	public long getLong(String str) {
		return sp.getLong(str, 0L);
	}

	public float getFloat(String key, float defaultValue) {
		return sp.getFloat(key, defaultValue);
	}

	public void setListString(HashMap<String, String> map) {
		editor = sp.edit();
		for (String str : map.keySet()) {
			editor.putString(str, map.get(str));
		}
		editor.commit();
	}

	public void setInt(String name, int i) {
		editor = sp.edit();
		editor.putInt(name, i);
		editor.commit();
	}

	public void setBoolean(String name, boolean i) {
		editor = sp.edit();
		editor.putBoolean(name, i);
		editor.commit();
	}

	public void setFloat(String key, float value) {
		editor = sp.edit();
		editor.putFloat(key, value);
		editor.commit();
	}

	public void setString(String key, String value) {
		if (value == null) {
			value = "";
		}
		editor = sp.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public void setLong(String name, long i) {
		editor = sp.edit();
		editor.putLong(name, i);
		editor.commit();
	}

	/**
	 * 向配置文件中保存信息，根据value值类型进行保存
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public void save(Context context, String key, Object value) {
		// 先判断要保存什么样的数据
		if (value instanceof String) {
			sp.edit().putString(key, (String) value).commit();// 根据类型，强转，然后要commit
		} else if (value instanceof Boolean) {
			sp.edit().putBoolean(key, (Boolean) value).commit();
		} else if (value instanceof Integer) {
			sp.edit().putInt(key, (Integer) value).commit();
		}
	}

	/**
	 * 向配置文件中保存信息，根据Map中value的类型进行保存
	 * 
	 * @param context
	 * @param datas
	 *            参数集合
	 */
	public void save(Context context, Map<String, Object> datas) {
		for (Entry<String, Object> entry : datas.entrySet()) {
			Object value = entry.getValue();
			// 先判断要保存什么样的数据
			if (value instanceof String) {
				sp.edit().putString(entry.getKey(), (String) value).commit();// 根据类型，强转，然后要commit
			} else if (value instanceof Boolean) {
				sp.edit().putBoolean(entry.getKey(), (Boolean) value).commit();
			} else if (value instanceof Integer) {
				sp.edit().putInt(entry.getKey(), (Integer) value).commit();
			}
		}
	}

	/**
	 * 清除指定值
	 * @param key
	 */
	public void removeData(String key) {
		editor = sp.edit();
		editor.remove(key);
		editor.commit();
	}

	/*******
	 * 清除数据
	 */
	public void clearData() {
		editor = sp.edit();
		editor.clear();
		editor.commit();
	}

	public void serialize(Object obj, String key) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(
				byteArrayOutputStream);
		objectOutputStream.writeObject(obj);
		String serStr = byteArrayOutputStream.toString("ISO-8859-1");
		serStr = java.net.URLEncoder.encode(serStr, "UTF-8");
		objectOutputStream.close();
		byteArrayOutputStream.close();
		editor = sp.edit();
		editor.putString(key, serStr);
		editor.commit();
	}

	public Object deSerialize(String key ) throws StreamCorruptedException, IOException, ClassNotFoundException{
		String redStr = java.net.URLDecoder.decode(sp.getString(key, null), "UTF-8");
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
				redStr.getBytes("ISO-8859-1"));
		ObjectInputStream objectInputStream = new ObjectInputStream(
				byteArrayInputStream);
		Object object = objectInputStream.readObject();
		objectInputStream.close();
		byteArrayInputStream.close();
		return object;
	}
	
}
