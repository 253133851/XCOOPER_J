package com.xcooper.sys.log.vo;

import com.pabula.fw.utility.VO;
import java.sql.Timestamp;

/**
 * 用户通知VO
 * @author zdk
 * 2016-05-03 11:59:41
 */
public class LogMemberVO implements VO {

	//LOG_MEMBER_ID
	int LOG_MEMBER_ID;

	//SERVICE_ID
	int SERVICE_ID;

	//LOG_ID
	int LOG_ID;

	//MEMBER_ID
	int MEMBER_ID;

	//IS_READ
	int IS_READ;

	//REMARK1
	String REMARK1;

	//REMARK2
	String REMARK2;

	//REMARK3
	String REMARK3;

	//ADD_DATETIME
	Timestamp ADD_DATETIME;

	/**
	 * @return 返回 LOG_MEMBER_ID LOG_MEMBER_ID
	 */
	public int getLOG_MEMBER_ID() {
		return LOG_MEMBER_ID;
	}

	/**
	 * @param LOG_MEMBER_ID 设置 LOG_MEMBER_ID 的值
	 */
	public void setLOG_MEMBER_ID(int LOG_MEMBER_ID) {
		this.LOG_MEMBER_ID = LOG_MEMBER_ID;
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
	 * @return 返回 LOG_ID LOG_ID
	 */
	public int getLOG_ID() {
		return LOG_ID;
	}

	/**
	 * @param LOG_ID 设置 LOG_ID 的值
	 */
	public void setLOG_ID(int LOG_ID) {
		this.LOG_ID = LOG_ID;
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
	 * @return 返回 IS_READ IS_READ
	 */
	public int getIS_READ() {
		return IS_READ;
	}

	/**
	 * @param IS_READ 设置 IS_READ 的值
	 */
	public void setIS_READ(int IS_READ) {
		this.IS_READ = IS_READ;
	}

	/**
	 * @return 返回 REMARK1 REMARK1
	 */
	public String getREMARK1() {
		return REMARK1;
	}

	/**
	 * @param REMARK1 设置 REMARK1 的值
	 */
	public void setREMARK1(String REMARK1) {
		this.REMARK1 = REMARK1;
	}

	/**
	 * @return 返回 REMARK2 REMARK2
	 */
	public String getREMARK2() {
		return REMARK2;
	}

	/**
	 * @param REMARK2 设置 REMARK2 的值
	 */
	public void setREMARK2(String REMARK2) {
		this.REMARK2 = REMARK2;
	}

	/**
	 * @return 返回 REMARK3 REMARK3
	 */
	public String getREMARK3() {
		return REMARK3;
	}

	/**
	 * @param REMARK3 设置 REMARK3 的值
	 */
	public void setREMARK3(String REMARK3) {
		this.REMARK3 = REMARK3;
	}

	/**
	 * @return 返回 ADD_DATETIME ADD_DATETIME
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

	public String toString() {
		StringBuffer ret = new StringBuffer();
		ret.append("toString : ");
		ret.append(", LOG_MEMBER_ID='" + LOG_MEMBER_ID + "'");
		ret.append(", SERVICE_ID='" + SERVICE_ID + "'");
		ret.append(", LOG_ID='" + LOG_ID + "'");
		ret.append(", MEMBER_ID='" + MEMBER_ID + "'");
		ret.append(", IS_READ='" + IS_READ + "'");
		ret.append(", REMARK1='" + REMARK1 + "'");
		ret.append(", REMARK2='" + REMARK2 + "'");
		ret.append(", REMARK3='" + REMARK3 + "'");
		ret.append(", ADD_DATETIME='" + ADD_DATETIME + "'");
		return ret.toString();
	}
}