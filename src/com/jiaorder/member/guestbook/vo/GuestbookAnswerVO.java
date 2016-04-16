package com.jiaorder.member.guestbook.vo;

import com.pabula.fw.utility.VO;

import java.sql.Timestamp;

/**
 * 客户反馈回复VO
 * @author zdk
 * 2016-03-21 15:21:06
 */
public class GuestbookAnswerVO implements VO {

	//GUESTBOOK_ANSWER_ID
	int GUESTBOOK_ANSWER_ID;

	//SERVICE_ID
	int SERVICE_ID;

	//GUESTBOOK_ID
	int GUESTBOOK_ID;

	//回复内容
	String CONTENT;

	//回复人
	int USER_ID;

	//添加时间
	Timestamp ADD_DATETIME;

	//回复时间
	Timestamp ANSWER_DATETIME;

	//是否删除
	int IS_DEL;

	//ip
	String IP;


	/**
	 * @return 返回 GUESTBOOK_ANSWER_ID GUESTBOOK_ANSWER_ID
	 */
	public int getGUESTBOOK_ANSWER_ID() {
		return GUESTBOOK_ANSWER_ID;
	}

	/**
	 * @param GUESTBOOK_ANSWER_ID 设置 GUESTBOOK_ANSWER_ID 的值
	 */
	public void setGUESTBOOK_ANSWER_ID(int GUESTBOOK_ANSWER_ID) {
		this.GUESTBOOK_ANSWER_ID = GUESTBOOK_ANSWER_ID;
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
	 * @return 返回 GUESTBOOK_ID GUESTBOOK_ID
	 */
	public int getGUESTBOOK_ID() {
		return GUESTBOOK_ID;
	}

	/**
	 * @param GUESTBOOK_ID 设置 GUESTBOOK_ID 的值
	 */
	public void setGUESTBOOK_ID(int GUESTBOOK_ID) {
		this.GUESTBOOK_ID = GUESTBOOK_ID;
	}

	/**
	 * @return 返回 CONTENT 回复内容
	 */
	public String getCONTENT() {
		return CONTENT;
	}

	/**
	 * @param CONTENT 设置 CONTENT 的值
	 */
	public void setCONTENT(String CONTENT) {
		this.CONTENT = CONTENT;
	}

	/**
	 * @return 返回 USER_ID 回复人
	 */
	public int getUSER_ID() {
		return USER_ID;
	}

	/**
	 * @param USER_ID 设置 USER_ID 的值
	 */
	public void setUSER_ID(int USER_ID) {
		this.USER_ID = USER_ID;
	}

	/**
	 * @return 返回 ADD_DATETIME 添加时间
	 */
	public Timestamp getADD_DATETIME() {
		return ADD_DATETIME;
	}

	/**
	 * @param ADD_DATETIME 设置 ADD_DATETIME 的值
	 */
	public void setADD_DATETIME(Timestamp ADD_DATETIME) {
		this.ADD_DATETIME = ADD_DATETIME;
	}

	/**
	 * @return 返回 ANSWER_DATETIME 回复时间
	 */
	public Timestamp getANSWER_DATETIME() {
		return ANSWER_DATETIME;
	}

	/**
	 * @param ANSWER_DATETIME 设置 ANSWER_DATETIME 的值
	 */
	public void setANSWER_DATETIME(Timestamp ANSWER_DATETIME) {
		this.ANSWER_DATETIME = ANSWER_DATETIME;
	}

	/**
	 * @return 返回 IS_DEL 是否删除
	 */
	public int getIS_DEL() {
		return IS_DEL;
	}

	/**
	 * @param IS_DEL 设置 IS_DEL 的值
	 */
	public void setIS_DEL(int IS_DEL) {
		this.IS_DEL = IS_DEL;
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
		ret.append(", GUESTBOOK_ANSWER_ID='" + GUESTBOOK_ANSWER_ID + "'");
		ret.append(", SERVICE_ID='" + SERVICE_ID + "'");
		ret.append(", GUESTBOOK_ID='" + GUESTBOOK_ID + "'");
		ret.append(", CONTENT='" + CONTENT + "'");
		ret.append(", USER_ID='" + USER_ID + "'");
		ret.append(", ADD_DATETIME='" + ADD_DATETIME + "'");
		ret.append(", ANSWER_DATETIME='" + ANSWER_DATETIME + "'");
		ret.append(", IS_DEL='" + IS_DEL + "'");
		ret.append(", IP='" + IP + "'");
		return ret.toString();
	}
}