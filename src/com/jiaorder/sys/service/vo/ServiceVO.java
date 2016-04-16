package com.jiaorder.sys.service.vo;

import java.sql.Timestamp;

/**
 * 服务VO
 * @author pabula
 * 2016-03-11 00:50:35
 */
public class ServiceVO {

	//SERVICE_ID
	int SERVICE_ID;

	//COMPANY_ID
	int COMPANY_ID;

	//状态
	int STATE;

	//注册时间
	Timestamp REG_DATETIME;

	//版本
	String VER;

	//版本开始时间
	Timestamp BEGIN_DATETIME;

	//版本结束时间
	Timestamp END_DATETIME;

	//是否为默认服务
	int IS_DEF;

	//创建人 UID ADD_UID
	int ADD_UID;

	//系统参数
	String SYS_PARAM;

	//系统参数-描述
	String SYS_PARAM_REMARK;

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
	 * @return 返回 COMPANY_ID COMPANY_ID
	 */
	public int getCOMPANY_ID() {
		return COMPANY_ID;
	}

	/**
	 * @param COMPANY_ID 设置 COMPANY_ID 的值
	 */
	public void setCOMPANY_ID(int COMPANY_ID) {
		this.COMPANY_ID = COMPANY_ID;
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
	 * @return 返回 REG_DATETIME 注册时间
	 */
	public Timestamp getREG_DATETIME() {
		return REG_DATETIME;
	}

	/**
	 * @param REG_DATETIME 设置 REG_DATETIME 的值
	 */
	public void setREG_DATETIME(Timestamp REG_DATETIME) {
		this.REG_DATETIME = REG_DATETIME;
	}

	/**
	 * @return 返回 VER 版本
	 */
	public String getVER() {
		return VER;
	}

	/**
	 * @param VER 设置 VER 的值
	 */
	public void setVER(String VER) {
		this.VER = VER;
	}

	/**
	 * @return 返回 BEGIN_DATETIME 版本开始时间
	 */
	public Timestamp getBEGIN_DATETIME() {
		return BEGIN_DATETIME;
	}

	/**
	 * @param BEGIN_DATETIME 设置 BEGIN_DATETIME 的值
	 */
	public void setBEGIN_DATETIME(Timestamp BEGIN_DATETIME) {
		this.BEGIN_DATETIME = BEGIN_DATETIME;
	}

	/**
	 * @return 返回 END_DATETIME 版本结束时间
	 */
	public Timestamp getEND_DATETIME() {
		return END_DATETIME;
	}

	/**
	 * @param END_DATETIME 设置 END_DATETIME 的值
	 */
	public void setEND_DATETIME(Timestamp END_DATETIME) {
		this.END_DATETIME = END_DATETIME;
	}

	/**
	 * @return 返回 IS_DEF 是否为默认服务
	 */
	public int getIS_DEF() {
		return IS_DEF;
	}

	/**
	 * @param IS_DEF 设置 IS_DEF 的值
	 */
	public void setIS_DEF(int IS_DEF) {
		this.IS_DEF = IS_DEF;
	}

	/**
	 * @return 返回 ADD_UID 创建人 UID ADD_UID
	 */
	public int getADD_UID() {
		return ADD_UID;
	}

	/**
	 * @param ADD_UID 设置 ADD_UID 的值
	 */
	public void setADD_UID(int ADD_UID) {
		this.ADD_UID = ADD_UID;
	}

	public String getSYS_PARAM() {
		return SYS_PARAM;
	}

	public void setSYS_PARAM(String SYS_PARAM) {
		this.SYS_PARAM = SYS_PARAM;
	}

	public String getSYS_PARAM_REMARK() {
		return SYS_PARAM_REMARK;
	}

	public void setSYS_PARAM_REMARK(String SYS_PARAM_REMARK) {
		this.SYS_PARAM_REMARK = SYS_PARAM_REMARK;
	}

	public String toString() {
		StringBuffer ret = new StringBuffer();
		ret.append("toString : ");
		ret.append(", SERVICE_ID='" + SERVICE_ID + "'");
		ret.append(", COMPANY_ID='" + COMPANY_ID + "'");
		ret.append(", STATE='" + STATE + "'");
		ret.append(", REG_DATETIME='" + REG_DATETIME + "'");
		ret.append(", VER='" + VER + "'");
		ret.append(", BEGIN_DATETIME='" + BEGIN_DATETIME + "'");
		ret.append(", END_DATETIME='" + END_DATETIME + "'");
		ret.append(", IS_DEF='" + IS_DEF + "'");
		ret.append(", ADD_UID='" + ADD_UID + "'");
		return ret.toString();
	}
}