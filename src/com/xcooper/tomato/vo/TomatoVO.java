package com.xcooper.tomato.vo;

import com.pabula.fw.utility.VO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.Timestamp;

/**
 * 番茄钟VO
 * @author zdk
 * 2016-03-28 19:42:34
 */
public class TomatoVO implements VO {

	//TOMATO_ID
	int TOMATO_ID;

	//SERVICE_ID
	int SERVICE_ID;

	//CREATE_ID
	int CREATE_ID;

	//对应任务
	int TASK_ID;

	//BEGIN_DATETIME
	Timestamp BEGIN_DATETIME;

	//END_DATETIME
	Timestamp END_DATETIME;

	//铃声
	String RING;

	//REMARK
	String REMARK;

	//ADD_DATETIME
	Timestamp ADD_DATETIME;

	//UPDATE_DATETIME
	Timestamp UPDATE_DATETIME;

	/**
	 * @return 返回 TOMATO_ID TOMATO_ID
	 */
	public int getTOMATO_ID() {
		return TOMATO_ID;
	}

	/**
	 * @param TOMATO_ID 设置 TOMATO_ID 的值
	 */
	public void setTOMATO_ID(int TOMATO_ID) {
		this.TOMATO_ID = TOMATO_ID;
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
	 * @return 返回 CREATE_ID CREATE_ID
	 */
	public int getCREATE_ID() {
		return CREATE_ID;
	}

	/**
	 * @param CREATE_ID 设置 CREATE_ID 的值
	 */
	public void setCREATE_ID(int CREATE_ID) {
		this.CREATE_ID = CREATE_ID;
	}

	/**
	 * @return 返回 TASK_ID 对应任务
	 */
	public int getTASK_ID() {
		return TASK_ID;
	}

	/**
	 * @param TASK_ID 设置 TASK_ID 的值
	 */
	public void setTASK_ID(int TASK_ID) {
		this.TASK_ID = TASK_ID;
	}

	/**
	 * @return 返回 BEGIN_DATETIME BEGIN_DATETIME
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
	 * @return 返回 END_DATETIME END_DATETIME
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
	 * @return 返回 RING 铃声
	 */
	public String getRING() {
		return RING;
	}

	/**
	 * @param RING 设置 RING 的值
	 */
	public void setRING(String RING) {
		this.RING = RING;
	}

	/**
	 * @return 返回 REMARK REMARK
	 */
	public String getREMARK() {
		return REMARK;
	}

	/**
	 * @param REMARK 设置 REMARK 的值
	 */
	public void setREMARK(String REMARK) {
		this.REMARK = REMARK;
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

	/**
	 * @return 返回 UPDATE_DATETIME UPDATE_DATETIME
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
		ret.append(", TOMATO_ID='" + TOMATO_ID + "'");
		ret.append(", SERVICE_ID='" + SERVICE_ID + "'");
		ret.append(", CREATE_ID='" + CREATE_ID + "'");
		ret.append(", TASK_ID='" + TASK_ID + "'");
		ret.append(", BEGIN_DATETIME='" + BEGIN_DATETIME + "'");
		ret.append(", END_DATETIME='" + END_DATETIME + "'");
		ret.append(", RING='" + RING + "'");
		ret.append(", REMARK='" + REMARK + "'");
		ret.append(", ADD_DATETIME='" + ADD_DATETIME + "'");
		ret.append(", UPDATE_DATETIME='" + UPDATE_DATETIME + "'");
		return ret.toString();
	}

}