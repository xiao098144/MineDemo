package com.xiao.demo.bean;

import java.util.List;

/**
 * @filename A.java
 * @TODO
 * @date 2014-10-31下午5:04:19
 * @Administrator 萧
 * 
 */
public class A {

	public A() {

	}

	private int id;

	private List<B> recordList;

	public void setRecordList(List<B> recordList) {
		this.recordList = recordList;
	}

	public List<B> getRecordList() {
		return recordList;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "A [id=" + id + ", recordList=" + recordList + "]";
	}

	public class B {

		public B() {

		}

		private String name;

		public void setName(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		@Override
		public String toString() {
			return "name = " + name;
		}

	}

}
