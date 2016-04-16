package com.jiaorder.member.notifyclass.vo;

import com.pabula.fw.utility.VO;

/**
 * 通知分类表VO
 * @author zdk
 * 2016-03-21 15:33:41
 */
public class NotifyClassVO implements VO {

	public static final int HAS_DELETED = 1; // 已经删除
	public static final int NOT_DELETE = -1; // 没有删除

	//NOTIFY_CLASS_ID
	int NOTIFY_CLASS_ID;

	//SERVICE_ID
	int SERVICE_ID;

	//通知分类名
	String NAME;

	//类别排序值
	int ORDER_NUM;

	//是否删除
	int IS_DEL;

	//是否为默认
	int IS_DEFAULT;

	public NotifyClassVO() {
		this.IS_DEL = NOT_DELETE;
	}

	/**
	 * @return 返回 NOTIFY_CLASS_ID NOTIFY_CLASS_ID
	 */
	public int getNOTIFY_CLASS_ID() {
		return NOTIFY_CLASS_ID;
	}

	/**
	 * @param NOTIFY_CLASS_ID 设置 NOTIFY_CLASS_ID 的值
	 */
	public void setNOTIFY_CLASS_ID(int NOTIFY_CLASS_ID) {
		this.NOTIFY_CLASS_ID = NOTIFY_CLASS_ID;
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
	 * @return 返回 NAME 通知分类名
	 */
	public String getNAME() {
		return NAME;
	}

	/**
	 * @param NAME 设置 NAME 的值
	 */
	public void setNAME(String NAME) {
		this.NAME = NAME;
	}

	/**
	 * @return 返回 ORDER_NUM 类别排序值
	 */
	public int getORDER_NUM() {
		return ORDER_NUM;
	}

	/**
	 * @param ORDER_NUM 设置 ORDER_NUM 的值
	 */
	public void setORDER_NUM(int ORDER_NUM) {
		this.ORDER_NUM = ORDER_NUM;
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
	 * @return 返回 IS_DEFAULT 是否为默认
	 */
	public int getIS_DEFAULT() {
		return IS_DEFAULT;
	}

	/**
	 * @param IS_DEFAULT 设置 IS_DEFAULT 的值
	 */
	public void setIS_DEFAULT(int IS_DEFAULT) {
		this.IS_DEFAULT = IS_DEFAULT;
	}

	public String toString() {
		StringBuffer ret = new StringBuffer();
		ret.append("toString : ");
		ret.append(", NOTIFY_CLASS_ID='" + NOTIFY_CLASS_ID + "'");
		ret.append(", SERVICE_ID='" + SERVICE_ID + "'");
		ret.append(", NAME='" + NAME + "'");
		ret.append(", ORDER_NUM='" + ORDER_NUM + "'");
		ret.append(", IS_DEL='" + IS_DEL + "'");
		ret.append(", IS_DEFAULT='" + IS_DEFAULT + "'");
		return ret.toString();
	}
}