package com.jiaorder.member.guestbook.vo;

import com.pabula.fw.utility.VO;

import java.sql.Timestamp;

/**
 * 客户反馈VO
 * @author zdk
 * 2016-03-21 15:15:28
 */
public class GuestbookVO implements VO {

	public static final int HAS_DELETED = 1; // 已经删除
	public static final int NOT_DELETE = -1; // 没有删除
	public static final int HAS_ANSWER = 1; // 已回复
	public static final int WAIT_ANSWER = -1; // 没有回复
	public static final int HAS_DONE = 1; // 已完结
	public static final int NOT_DONE = -1; // 没有完结

	//GUESTBOOK_ID
	int GUESTBOOK_ID;

	//SERVICE_ID
	int SERVICE_ID;

	//MEMBER_ID
	int MEMBER_ID;

	//反馈内容
	String NOTIFY_BACK;

	//状态
	int STATE;

	//是否删除
	int IS_DEL;

	//是否完结
	int IS_DONE;

	//完结内容
	String DONE;

	//ip
	String IP;

	//添加时间
	Timestamp ADD_DATETIME;

	//完结人
	int USER_ID;

	//完结时间
	Timestamp DONE_TIME;

	//反馈人
	String MEMBER_NAME;

	//回复列表
	String ANSWER_LIST;

	//每页大小
	int PageSize;

	//当前页码
	int PageIndex;

	public void setPageInfo(int pageSize, int PageIndex) {
		this.PageSize = pageSize;
		this.PageIndex = PageIndex;
	}

	public GuestbookVO() {
		this.STATE = WAIT_ANSWER;
		this.IS_DEL = NOT_DELETE;
		this.IS_DONE = NOT_DONE;
	}

	public int getPageSize() {
		return PageSize;
	}

	public void setPageSize(int pageSize) {
		PageSize = pageSize;
	}

	public int getPageIndex() {
		return PageIndex;
	}

	public void setPageIndex(int pageIndex) {
		PageIndex = pageIndex;
	}

	public Timestamp getDONE_TIME() {
		return DONE_TIME;
	}

	public void setDONE_TIME(Timestamp DONE_TIME) {
		this.DONE_TIME = DONE_TIME;
	}

	public int getUSER_ID() {
		return USER_ID;
	}

	public void setUSER_ID(int USER_ID) {
		this.USER_ID = USER_ID;
	}

	public String getANSWER_LIST() {
		return ANSWER_LIST;
	}

	public void setANSWER_LIST(String ANSWER_LIST) {
		this.ANSWER_LIST = ANSWER_LIST;
	}

	public String getMEMBER_NAME() {
		return MEMBER_NAME;
	}

	public void setMEMBER_NAME(String MEMBER_NAME) {
		this.MEMBER_NAME = MEMBER_NAME;
	}

	public Timestamp getADD_DATETIME() {
		return ADD_DATETIME;
	}

	public void setADD_DATETIME(Timestamp ADD_DATETIME) {
		this.ADD_DATETIME = ADD_DATETIME;
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
	 * @return 返回 MEMBER_ID MEMBER_ID
	 */
	public int getMEMBER_ID() {
		return MEMBER_ID;
	}

	/**
	 * @param MEMBER_ID 设置 MEMBER_ID 的值
	 */
	public void setMEMBER_ID(int MEMBER_ID) {
		this.MEMBER_ID = MEMBER_ID;
	}

	/**
	 * @return 返回 NOTIFY_BACK 反馈内容
	 */
	public String getNOTIFY_BACK() {
		return NOTIFY_BACK;
	}

	/**
	 * @param NOTIFY_BACK 设置 NOTIFY_BACK 的值
	 */
	public void setNOTIFY_BACK(String NOTIFY_BACK) {
		this.NOTIFY_BACK = NOTIFY_BACK;
	}

	/**
	 * @return 返回 STATE 状态
	 */
	public int getSTATE() {
		return STATE;
	}

	/**
	 * @param STATE 设置 STATE 的值
	 */
	public void setSTATE(int STATE) {
		this.STATE = STATE;
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
	 * @return 返回 IS_DONE 是否完结
	 */
	public int getIS_DONE() {
		return IS_DONE;
	}

	/**
	 * @param IS_DONE 设置 IS_DONE 的值
	 */
	public void setIS_DONE(int IS_DONE) {
		this.IS_DONE = IS_DONE;
	}

	/**
	 * @return 返回 DONE 完结内容
	 */
	public String getDONE() {
		return DONE;
	}

	/**
	 * @param DONE 设置 DONE 的值
	 */
	public void setDONE(String DONE) {
		this.DONE = DONE;
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
		ret.append(", GUESTBOOK_ID='" + GUESTBOOK_ID + "'");
		ret.append(", SERVICE_ID='" + SERVICE_ID + "'");
		ret.append(", MEMBER_ID='" + MEMBER_ID + "'");
		ret.append(", NOTIFY_BACK='" + NOTIFY_BACK + "'");
		ret.append(", STATE='" + STATE + "'");
		ret.append(", IS_DEL='" + IS_DEL + "'");
		ret.append(", IS_DONE='" + IS_DONE + "'");
		ret.append(", DONE='" + DONE + "'");
		ret.append(", IP='" + IP + "'");
		return ret.toString();
	}
}