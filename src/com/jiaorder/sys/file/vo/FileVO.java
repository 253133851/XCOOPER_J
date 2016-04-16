package com.jiaorder.sys.file.vo;

import com.pabula.fw.utility.VO;

/**
 * 附件表VO
 * @author zdk
 * 2016-03-22 10:22:34
 */
public class FileVO implements VO {

	//FILE_ID
	int FILE_ID;

	//SERVICE_ID
	int SERVICE_ID;

	//目标id
	int TARGET_ID;

	//附件类型
	String TYPE;

	//附件名
	String FILE_NAME;

	//附件路径
	String FILE_PATH;

	int SIZE;

	String FILE_TYPE;

	public int getSIZE() {
		return SIZE;
	}

	public void setSIZE(int SIZE) {
		this.SIZE = SIZE;
	}

	public String getFILE_TYPE() {
		return FILE_TYPE;
	}

	public void setFILE_TYPE(String FILE_TYPE) {
		this.FILE_TYPE = FILE_TYPE;
	}

	/**
	 * @return 返回 FILE_ID FILE_ID
	 */
	public int getFILE_ID() {
		return FILE_ID;
	}

	/**
	 * @param FILE_ID 设置 FILE_ID 的值
	 */
	public void setFILE_ID(int FILE_ID) {
		this.FILE_ID = FILE_ID;
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
	 * @return 返回 TARGET_ID 目标id
	 */
	public int getTARGET_ID() {
		return TARGET_ID;
	}

	/**
	 * @param TARGET_ID 设置 TARGET_ID 的值
	 */
	public void setTARGET_ID(int TARGET_ID) {
		this.TARGET_ID = TARGET_ID;
	}

	/**
	 * @return 返回 TYPE 附件类型
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
	 * @return 返回 FILE_NAME 附件名
	 */
	public String getFILE_NAME() {
		return FILE_NAME;
	}

	/**
	 * @param FILE_NAME 设置 FILE_NAME 的值
	 */
	public void setFILE_NAME(String FILE_NAME) {
		this.FILE_NAME = FILE_NAME;
	}

	/**
	 * @return 返回 FILE_PATH 附件路径
	 */
	public String getFILE_PATH() {
		return FILE_PATH;
	}

	/**
	 * @param FILE_PATH 设置 FILE_PATH 的值
	 */
	public void setFILE_PATH(String FILE_PATH) {
		this.FILE_PATH = FILE_PATH;
	}

	public String toString() {
		StringBuffer ret = new StringBuffer();
		ret.append("toString : ");
		ret.append(", FILE_ID='" + FILE_ID + "'");
		ret.append(", SERVICE_ID='" + SERVICE_ID + "'");
		ret.append(", TARGET_ID='" + TARGET_ID + "'");
		ret.append(", TYPE='" + TYPE + "'");
		ret.append(", FILE_NAME='" + FILE_NAME + "'");
		ret.append(", FILE_PATH='" + FILE_PATH + "'");
		return ret.toString();
	}
}