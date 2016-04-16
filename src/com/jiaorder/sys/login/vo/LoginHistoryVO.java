package com.jiaorder.sys.login.vo;

import com.pabula.fw.utility.VO;

import java.sql.Timestamp;

/**
 * 登录历史VO
 * @author pabula
 * 2015-07-12 00:57:50
 */
public class LoginHistoryVO implements VO {

	//ID
	int ID;

	//会员ID
	int MID;

	//登录帐号
	String LOGIN_ID;

	//登录时间
	Timestamp LAST_LOGIN_DATETIME;

	//登录IP
	String LAST_LOGIN_IP;

	//登录渠道
	String LAST_LOGIN_SOURCE;

	/**
	 * @return 返回 ID ID
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @param ID 设置 ID 的值
	 */
	public void setID(int ID) {
		this.ID = ID;
	}

	/**
	 * @return 返回 MID 会员ID
	 */
	public int getMID() {
		return MID;
	}

	/**
	 * @param MID 设置 MID 的值
	 */
	public void setMID(int MID) {
		this.MID = MID;
	}

	/**
	 * @return 返回 LOGIN_ID 登录帐号
	 */
	public String getLOGIN_ID() {
		return LOGIN_ID;
	}

	/**
	 * @param LOGIN_ID 设置 LOGIN_ID 的值
	 */
	public void setLOGIN_ID(String LOGIN_ID) {
		this.LOGIN_ID = LOGIN_ID;
	}

	/**
	 * @return 返回 LAST_LOGIN_DATETIME 登录时间
	 */
	public Timestamp getLAST_LOGIN_DATETIME() {
		return LAST_LOGIN_DATETIME;
	}

	/**
	 * @param LAST_LOGIN_DATETIME 设置 LAST_LOGIN_DATETIME 的值
	 */
	public void setLAST_LOGIN_DATETIME(Timestamp LAST_LOGIN_DATETIME) {
		this.LAST_LOGIN_DATETIME = LAST_LOGIN_DATETIME;
	}

	/**
	 * @return 返回 LAST_LOGIN_IP 登录IP
	 */
	public String getLAST_LOGIN_IP() {
		return LAST_LOGIN_IP;
	}

	/**
	 * @param LAST_LOGIN_IP 设置 LAST_LOGIN_IP 的值
	 */
	public void setLAST_LOGIN_IP(String LAST_LOGIN_IP) {
		this.LAST_LOGIN_IP = LAST_LOGIN_IP;
	}

	/**
	 * @return 返回 LAST_LOGIN_SOURCE 登录渠道
	 */
	public String getLAST_LOGIN_SOURCE() {
		return LAST_LOGIN_SOURCE;
	}

	/**
	 * @param LAST_LOGIN_SOURCE 设置 LAST_LOGIN_SOURCE 的值
	 */
	public void setLAST_LOGIN_SOURCE(String LAST_LOGIN_SOURCE) {
		this.LAST_LOGIN_SOURCE = LAST_LOGIN_SOURCE;
	}

	public String toString() {
		StringBuffer ret = new StringBuffer();
		ret.append("toString : ");
		ret.append(", ID='" + ID + "'");
		ret.append(", MID='" + MID + "'");
		ret.append(", LOGIN_ID='" + LOGIN_ID + "'");
		ret.append(", LAST_LOGIN_DATETIME='" + LAST_LOGIN_DATETIME + "'");
		ret.append(", LAST_LOGIN_IP='" + LAST_LOGIN_IP + "'");
		ret.append(", LAST_LOGIN_SOURCE='" + LAST_LOGIN_SOURCE + "'");
		return ret.toString();
	}
}