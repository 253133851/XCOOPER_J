package com.xcooper.comment.vo;

import com.pabula.fw.utility.VO;
import java.sql.Timestamp;

/**
 * 业务审核流程VO
 * @author zdk
 * 2016-04-28 11:33:28
 */
public class TopicVO implements VO {

	//TOPPIC_ID
	int TOPPIC_ID;

	//SERVICE_ID
	int SERVICE_ID;

	//PROJECT_ID
	int PROJECT_ID;

	//TITILE
	String TITILE;

	//DESCRIBES
	String DESCRIBES;

	//IS_DONE
	int IS_DONE;

	//REMARK
	String REMARK;

	//ADD_DATETIME
	Timestamp ADD_DATETIME;

	//UPDATE_DATETIME
	Timestamp UPDATE_DATETIME;

	public int getPROJECT_ID() {
		return PROJECT_ID;
	}

	public void setPROJECT_ID(int PROJECT_ID) {
		this.PROJECT_ID = PROJECT_ID;
	}

	/**
	 * @return 返回 TOPPIC_ID TOPPIC_ID
	 */
	public int getTOPPIC_ID() {
		return TOPPIC_ID;
	}

	/**
	 * @param TOPPIC_ID 设置 TOPPIC_ID 的值
	 */
	public void setTOPPIC_ID(int TOPPIC_ID) {
		this.TOPPIC_ID = TOPPIC_ID;
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
	 * @return 返回 TITILE TITILE
	 */
	public String getTITILE() {
		return TITILE;
	}

	/**
	 * @param TITILE 设置 TITILE 的值
	 */
	public void setTITILE(String TITILE) {
		this.TITILE = TITILE;
	}

	/**
	 * @return 返回 DESCRIBES DESCRIBES
	 */
	public String getDESCRIBES() {
		return DESCRIBES;
	}

	/**
	 * @param DESCRIBES 设置 DESCRIBES 的值
	 */
	public void setDESCRIBES(String DESCRIBES) {
		this.DESCRIBES = DESCRIBES;
	}

	/**
	 * @return 返回 IS_DONE IS_DONE
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
		ret.append(", TOPPIC_ID='" + TOPPIC_ID + "'");
		ret.append(", SERVICE_ID='" + SERVICE_ID + "'");
		ret.append(", TITILE='" + TITILE + "'");
		ret.append(", DESCRIBES='" + DESCRIBES + "'");
		ret.append(", IS_DONE='" + IS_DONE + "'");
		ret.append(", REMARK='" + REMARK + "'");
		ret.append(", ADD_DATETIME='" + ADD_DATETIME + "'");
		ret.append(", UPDATE_DATETIME='" + UPDATE_DATETIME + "'");
		return ret.toString();
	}
}