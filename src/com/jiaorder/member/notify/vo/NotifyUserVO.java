package com.jiaorder.member.notify.vo;

import com.pabula.fw.utility.VO;

/**
 * 通知-用户表VO
 * @author zdk
 * 2016-03-21 15:29:43
 */
public class NotifyUserVO implements VO {

	//NOTIFY_USER_ID
	int NOTIFY_USER_ID;

	//SERVICE_ID
	int SERVICE_ID;

	//NOTIFY_ID
	int NOTIFY_ID;

	//通知用户类型
	String TYPE;

	//通知目标id
	int TARGET_ID;

	//通知用户的名字
	String TARGET_NAME;

	public String getTARGET_NAME() {
		return TARGET_NAME;
	}

	public void setTARGET_NAME(String TARGET_NAME) {
		this.TARGET_NAME = TARGET_NAME;
	}

	/**
	 * @return 返回 NOTIFY_USER_ID NOTIFY_USER_ID
	 */
	public int getNOTIFY_USER_ID() {
		return NOTIFY_USER_ID;
	}

	/**
	 * @param NOTIFY_USER_ID 设置 NOTIFY_USER_ID 的值
	 */
	public void setNOTIFY_USER_ID(int NOTIFY_USER_ID) {
		this.NOTIFY_USER_ID = NOTIFY_USER_ID;
	}

	/**
	 * @return 返回 SERVICE_ID SERVICE_ID
	 */
	public int getSERVICE_ID() {
		return SERVICE_ID;
	}

	/**
	 * @param SERVICE_ID 设置 SERVICE_ID 的值
	 */
	public void setSERVICE_ID(int SERVICE_ID) {
		this.SERVICE_ID = SERVICE_ID;
	}

	/**
	 * @return 返回 NOTIFY_ID NOTIFY_ID
	 */
	public int getNOTIFY_ID() {
		return NOTIFY_ID;
	}

	/**
	 * @param NOTIFY_ID 设置 NOTIFY_ID 的值
	 */
	public void setNOTIFY_ID(int NOTIFY_ID) {
		this.NOTIFY_ID = NOTIFY_ID;
	}

	/**
	 * @return 返回 TYPE 通知用户类型
	 */
	public String getTYPE() {
		return TYPE;
	}

	/**
	 * @param TYPE 设置 TYPE 的值
	 */
	public void setTYPE(String TYPE) {
		this.TYPE = TYPE;
	}

	/**
	 * @return 返回 TARGET_ID 通知目标id
	 */
	public int getTARGET_ID() {
		return TARGET_ID;
	}

	/**
	 * @param TARGET_ID 设置 TARGET_ID 的值
	 */
	public void setTARGET_ID(int TARGET_ID) {
		this.TARGET_ID = TARGET_ID;
	}

	public String toString() {
		StringBuffer ret = new StringBuffer();
		ret.append("toString : ");
		ret.append(", NOTIFY_USER_ID='" + NOTIFY_USER_ID + "'");
		ret.append(", SERVICE_ID='" + SERVICE_ID + "'");
		ret.append(", NOTIFY_ID='" + NOTIFY_ID + "'");
		ret.append(", TYPE='" + TYPE + "'");
		ret.append(", TARGET_ID='" + TARGET_ID + "'");
		return ret.toString();
	}
}