package com.jiaorder.sys.role.vo;

import java.sql.Timestamp;

/**
 * 角色VO
 * @author pabula
 * 2016-03-11 00:57:19
 */
public class RoleVO {

	//ROLE_ID
	String ROLE_ID;

	//名称
	String ROLE_NAME;

	//描述
	String INTRO;

	//SERVICE_ID
	int SERVICE_ID;

	//是否系统
	int IS_SYS;

	//角色应用范围
	String ROLE_SCOPE;

	//状态
	int STATE;

	//添加日期
	Timestamp ADD_DATETIME;

	//修改日期
	Timestamp UPDATE_DATETIME;

	/**
	 * @return 返回 ROLE_ID ROLE_ID
	 */
	public String getROLE_ID() {
		return ROLE_ID;
	}

	/**
	 * @param ROLE_ID 设置 ROLE_ID 的值
	 */
	public void setROLE_ID(String ROLE_ID) {
		this.ROLE_ID = ROLE_ID;
	}

	/**
	 * @return 返回 ROLE_NAME 名称
	 */
	public String getROLE_NAME() {
		return ROLE_NAME;
	}

	/**
	 * @param ROLE_NAME 设置 ROLE_NAME 的值
	 */
	public void setROLE_NAME(String ROLE_NAME) {
		this.ROLE_NAME = ROLE_NAME;
	}

	/**
	 * @return 返回 INTRO 描述
	 */
	public String getINTRO() {
		return INTRO;
	}

	/**
	 * @param INTRO 设置 INTRO 的值
	 */
	public void setINTRO(String INTRO) {
		this.INTRO = INTRO;
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
	 * @return 返回 IS_SYS 是否系统
	 */
	public int getIS_SYS() {
		return IS_SYS;
	}

	/**
	 * @param IS_SYS 设置 IS_SYS 的值
	 */
	public void setIS_SYS(int IS_SYS) {
		this.IS_SYS = IS_SYS;
	}

	/**
	 * @return 返回 ROLE_SCOPE 角色应用范围
	 */
	public String getROLE_SCOPE() {
		return ROLE_SCOPE;
	}

	/**
	 * @param ROLE_SCOPE 设置 ROLE_SCOPE 的值
	 */
	public void setROLE_SCOPE(String ROLE_SCOPE) {
		this.ROLE_SCOPE = ROLE_SCOPE;
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
	 * @return 返回 ADD_DATETIME 添加日期
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
	 * @return 返回 UPDATE_DATETIME 修改日期
	 */
	public Timestamp getUPDATE_DATETIME() {
		return UPDATE_DATETIME;
	}

	/**
	 * @param UPDATE_DATETIME 设置 UPDATE_DATETIME 的值
	 */
	public void setUPDATE_DATETIME(Timestamp UPDATE_DATETIME) {
		this.UPDATE_DATETIME = UPDATE_DATETIME;
	}

	public String toString() {
		StringBuffer ret = new StringBuffer();
		ret.append("toString : ");
		ret.append(", ROLE_ID='" + ROLE_ID + "'");
		ret.append(", ROLE_NAME='" + ROLE_NAME + "'");
		ret.append(", INTRO='" + INTRO + "'");
		ret.append(", SERVICE_ID='" + SERVICE_ID + "'");
		ret.append(", IS_SYS='" + IS_SYS + "'");
		ret.append(", ROLE_SCOPE='" + ROLE_SCOPE + "'");
		ret.append(", STATE='" + STATE + "'");
		ret.append(", ADD_DATETIME='" + ADD_DATETIME + "'");
		ret.append(", UPDATE_DATETIME='" + UPDATE_DATETIME + "'");
		return ret.toString();
	}
}