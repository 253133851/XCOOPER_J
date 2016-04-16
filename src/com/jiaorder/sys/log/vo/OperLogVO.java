package com.jiaorder.sys.log.vo;

import com.pabula.fw.utility.VO;

import java.sql.Timestamp;

/**
 * 操作日志VO
 * @author Pabula
 * 2016-03-17 13:37:25
 */
public class OperLogVO implements VO {

	//ID
	int ID;

	//SERVICE_ID
	int SERVICE_ID;

	//UID
	int UID;

	//操作目标ID
	String TARGET_ID;

	//操作公司
	String OPER_COMPANY;

	//操作人
	String OPER_USER;

	//OPER_DATETIME
	Timestamp OPER_DATETIME;

	//TYPE
	String TYPE;

	//OPER_LOG
	String OPER_LOG;

	//OPER_IP
	String OPER_IP;

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
	 * @return 返回 UID UID
	 */
	public int getUID() {
		return UID;
	}

	/**
	 * @param UID 设置 UID 的值
	 */
	public void setUID(int UID) {
		this.UID = UID;
	}

	/**
	 * @return 返回 TARGET_ID 操作目标ID
	 */
	public String getTARGET_ID() {
		return TARGET_ID;
	}

	/**
	 * @param TARGET_ID 设置 TARGET_ID 的值
	 */
	public void setTARGET_ID(String TARGET_ID) {
		this.TARGET_ID = TARGET_ID;
	}

	/**
	 * @return 返回 OPER_COMPANY 操作公司
	 */
	public String getOPER_COMPANY() {
		return OPER_COMPANY;
	}

	/**
	 * @param OPER_COMPANY 设置 OPER_COMPANY 的值
	 */
	public void setOPER_COMPANY(String OPER_COMPANY) {
		this.OPER_COMPANY = OPER_COMPANY;
	}

	/**
	 * @return 返回 OPER_USER 操作人
	 */
	public String getOPER_USER() {
		return OPER_USER;
	}

	/**
	 * @param OPER_USER 设置 OPER_USER 的值
	 */
	public void setOPER_USER(String OPER_USER) {
		this.OPER_USER = OPER_USER;
	}

	/**
	 * @return 返回 OPER_DATETIME OPER_DATETIME
	 */
	public Timestamp getOPER_DATETIME() {
		return OPER_DATETIME;
	}

	/**
	 * @param OPER_DATETIME 设置 OPER_DATETIME 的值
	 */
	public void setOPER_DATETIME(Timestamp OPER_DATETIME) {
		this.OPER_DATETIME = OPER_DATETIME;
	}

	/**
	 * @return 返回 TYPE TYPE
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
	 * @return 返回 OPER_LOG OPER_LOG
	 */
	public String getOPER_LOG() {
		return OPER_LOG;
	}

	/**
	 * @param OPER_LOG 设置 OPER_LOG 的值
	 */
	public void setOPER_LOG(String OPER_LOG) {
		this.OPER_LOG = OPER_LOG;
	}

	/**
	 * @return 返回 OPER_IP OPER_IP
	 */
	public String getOPER_IP() {
		return OPER_IP;
	}

	/**
	 * @param OPER_IP 设置 OPER_IP 的值
	 */
	public void setOPER_IP(String OPER_IP) {
		this.OPER_IP = OPER_IP;
	}

	public String toString() {
		StringBuffer ret = new StringBuffer();
		ret.append("toString : ");
		ret.append(", ID='" + ID + "'");
		ret.append(", SERVICE_ID='" + SERVICE_ID + "'");
		ret.append(", UID='" + UID + "'");
		ret.append(", TARGET_ID='" + TARGET_ID + "'");
		ret.append(", OPER_COMPANY='" + OPER_COMPANY + "'");
		ret.append(", OPER_USER='" + OPER_USER + "'");
		ret.append(", OPER_DATETIME='" + OPER_DATETIME + "'");
		ret.append(", TYPE='" + TYPE + "'");
		ret.append(", OPER_LOG='" + OPER_LOG + "'");
		ret.append(", OPER_IP='" + OPER_IP + "'");
		return ret.toString();
	}
}