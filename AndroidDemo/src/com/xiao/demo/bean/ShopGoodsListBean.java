package com.xiao.demo.bean;

import java.util.List;

/**
 *@filename ShopGoodsListBean.java
 *@TODO
 *@date 2014-9-1上午10:11:18
 *@Administrator 萧
 *
 */
public class ShopGoodsListBean {

	/**
     * *返回码
     */
    private String code;
    /**
     * *返回消息
     */
    private String errMsg;


    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    private String num;
	private List<ShopGoodsBean> recordList;

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public List<ShopGoodsBean> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<ShopGoodsBean> recordList) {
		this.recordList = recordList;
	}

	@Override
	public String toString() {
		return "ShopGoodsListBean [code=" + code + ", errMsg=" + errMsg
				+ ", num=" + num + ", recordList=" + recordList + "]";
	}
	
	
	
}
