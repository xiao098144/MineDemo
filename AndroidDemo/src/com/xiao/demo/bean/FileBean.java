package com.xiao.demo.bean;

import java.io.Serializable;

/**
 * @filename FileBean.java
 * @TODO 线程池下载反馈结果
 * @date 2015-1-8下午3:28:46
 * @Administrator 萧
 * 
 */
public class FileBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	private int id;
	private String downUrl;
	private int fileSize;
	private String name;
	private String path;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public String getDownUrl() {
		return downUrl;
	}

	public void setDownUrl(String downUrl) {
		this.downUrl = downUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "FileBean [id=" + id + ", downUrl=" + downUrl + ", name=" + name
				+ ", path=" + path + "]";
	}

}
