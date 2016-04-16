package com.jiaorder.sys.manager.vo;

import java.sql.Timestamp;

/**
 * 管理员VO
 * @author pabula
 * 2016-03-11 00:55:08
 */
public class ManagerVO {

	//MANAGER_ID
	int MANAGER_ID;

	//用户ID
	int UID;

	//SERVICE_ID
	int SERVICE_ID;

	//所属角色
	String ROLE_ID_LIST;

	//所属地区
	String AREA_ID_LIST;
//
//	//姓名
//	String USER_NAME;
//
//	//编码
//	String USER_CDOE;
//
//	//职位
//	String JOB;
//
//	//手机
//	String MOBILE;
//
//	//邮箱
//	String EMAIL;
//
//	//QQ
//	String QQ;

	//添加时间
	Timestamp ADD_DATETIME;

	//更新时间
	Timestamp MODIFY_DATETIME;

	/**
	 * @return 返回 MANAGER_ID MANAGER_ID
	 */
	public int getMANAGER_ID() {
		return MANAGER_ID;
	}

	/**
	 * @param MANAGER_ID 设置 MANAGER_ID 的值
	 */
	public void setMANAGER_ID(int MANAGER_ID) {
		this.MANAGER_ID = MANAGER_ID;
	}

	/**
	 * @return 返回 UID 用户ID
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
	 * @return 返回 ROLE_ID_LIST 所属角色
	 */
	public String getROLE_ID_LIST() {
		return ROLE_ID_LIST;
	}

	/**
	 * @param ROLE_ID_LIST 设置 ROLE_ID_LIST 的值
	 */
	public void setROLE_ID_LIST(String ROLE_ID_LIST) {
		this.ROLE_ID_LIST = ROLE_ID_LIST;
	}

	/**
	 * @return 返回 AREA_ID_LIST 所属地区
	 */
	public String getAREA_ID_LIST() {
		return AREA_ID_LIST;
	}

	/**
	 * @param AREA_ID_LIST 设置 AREA_ID_LIST 的值
	 */
	public void setAREA_ID_LIST(String AREA_ID_LIST) {
		this.AREA_ID_LIST = AREA_ID_LIST;
	}


	public Timestamp getADD_DATETIME() {
		return ADD_DATETIME;
	}

	public void setADD_DATETIME(Timestamp ADD_DATETIME) {
		this.ADD_DATETIME = ADD_DATETIME;
	}

	public Timestamp getMODIFY_DATETIME() {
		return MODIFY_DATETIME;
	}

	public void setMODIFY_DATETIME(Timestamp MODIFY_DATETIME) {
		this.MODIFY_DATETIME = MODIFY_DATETIME;
	}

	public String toString() {
		StringBuffer ret = new StringBuffer();
		ret.append("toString : ");
		ret.append(", MANAGER_ID='" + MANAGER_ID + "'");
		ret.append(", UID='" + UID + "'");
		ret.append(", SERVICE_ID='" + SERVICE_ID + "'");
		ret.append(", ROLE_ID_LIST='" + ROLE_ID_LIST + "'");
		ret.append(", AREA_ID_LIST='" + AREA_ID_LIST + "'");
		return ret.toString();
	}
}