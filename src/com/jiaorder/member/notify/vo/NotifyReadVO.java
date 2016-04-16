package com.jiaorder.member.notify.vo;

import com.pabula.fw.utility.VO;

import java.sql.Timestamp;

/**
 * 通知已读表VO
 * @author zdk
 * 2016-03-21 15:31:16
 */
public class NotifyReadVO implements VO {

	//NOTIFY_READ_ID
	int NOTIFY_READ_ID;

	//SERVICE_ID
	int SERVICE_ID;

	//NOTIFY_ID
	int NOTIFY_ID;

	//读取用户类型
	String TYPE;

	//读取用户id
	int TARGET_ID;

	//读取用户的名字
	String TARGET_NAME;

	//读取时间
	Timestamp READ_DATETIME;

	//ip
	String IP;

	public String getTARGET_NAME() {
		return TARGET_NAME;
	}

	public void setTARGET_NAME(String TARGET_NAME) {
		this.TARGET_NAME = TARGET_NAME;
	}

	/**
	 * @return 返回 NOTIFY_READ_ID NOTIFY_READ_ID
	 */
	public int getNOTIFY_READ_ID() {
		return NOTIFY_READ_ID;
	}

	/**
	 * @param NOTIFY_READ_ID 设置 NOTIFY_READ_ID 的值
	 */
	public void setNOTIFY_READ_ID(int NOTIFY_READ_ID) {
		this.NOTIFY_READ_ID = NOTIFY_READ_ID;
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
	 * @return 返回 TYPE 读取用户类型
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
	 * @return 返回 TARGET_ID 读取用户id
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

	/**
	 * @return 返回 READ_DATETIME 读取时间
	 */
	public Timestamp getREAD_DATETIME() {
		return READ_DATETIME;
	}

	/**
	 * @param READ_DATETIME 设置 READ_DATETIME 的值
	 */
	public void setREAD_DATETIME(Timestamp READ_DATETIME) {
		this.READ_DATETIME = READ_DATETIME;
	}

	/**
	 * @return 返回 IP ip
	 */
	public String getIP() {
		return IP;
	}

	/**
	 * @param IP 设置 IP 的值
	 */
	public void setIP(String IP) {
		this.IP = IP;
	}

	public String toString() {
		StringBuffer ret = new StringBuffer();
		ret.append("toString : ");
		ret.append(", NOTIFY_READ_ID='" + NOTIFY_READ_ID + "'");
		ret.append(", SERVICE_ID='" + SERVICE_ID + "'");
		ret.append(", NOTIFY_ID='" + NOTIFY_ID + "'");
		ret.append(", TYPE='" + TYPE + "'");
		ret.append(", TARGET_ID='" + TARGET_ID + "'");
		ret.append(", READ_DATETIME='" + READ_DATETIME + "'");
		ret.append(", IP='" + IP + "'");
		return ret.toString();
	}
}