package com.jiaorder.shop.price.vo;

import com.pabula.fw.utility.VO;

/**
 * 商品价格会员对应VO
 * @author sunsai
 * 2016-03-18 14:51:24
 */
public class ProductPriceMemberVO implements VO {

	//ID
	int ID;

	//SERVICE_ID
	int SERVICE_ID;

	//MEMBER_ID
	int MEMBER_ID;

	//SKU_ID
	int SKU_ID;

	//允许订货
	int CAN_BUY;

	//订货价
	int PRICE;

	//起定量
	int MIN_NUM;

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
	 * @return 返回 SKU_ID SKU_ID
	 */
	public int getSKU_ID() {
		return SKU_ID;
	}

	/**
	 * @param SKU_ID 设置 SKU_ID 的值
	 */
	public void setSKU_ID(int SKU_ID) {
		this.SKU_ID = SKU_ID;
	}

	/**
	 * @return 返回 CAN_BUY 允许订货
	 */
	public int getCAN_BUY() {
		return CAN_BUY;
	}

	/**
	 * @param CAN_BUY 设置 CAN_BUY 的值
	 */
	public void setCAN_BUY(int CAN_BUY) {
		this.CAN_BUY = CAN_BUY;
	}

	/**
	 * @return 返回 PRICE 订货价
	 */
	public int getPRICE() {
		return PRICE;
	}

	/**
	 * @param PRICE 设置 PRICE 的值
	 */
	public void setPRICE(int PRICE) {
		this.PRICE = PRICE;
	}

	/**
	 * @return 返回 MIN_NUM 起定量
	 */
	public int getMIN_NUM() {
		return MIN_NUM;
	}

	/**
	 * @param MIN_NUM 设置 MIN_NUM 的值
	 */
	public void setMIN_NUM(int MIN_NUM) {
		this.MIN_NUM = MIN_NUM;
	}

	public String toString() {
		StringBuffer ret = new StringBuffer();
		ret.append("toString : ");
		ret.append(", ID='" + ID + "'");
		ret.append(", SERVICE_ID='" + SERVICE_ID + "'");
		ret.append(", MEMBER_ID='" + MEMBER_ID + "'");
		ret.append(", SKU_ID='" + SKU_ID + "'");
		ret.append(", CAN_BUY='" + CAN_BUY + "'");
		ret.append(", PRICE='" + PRICE + "'");
		ret.append(", MIN_NUM='" + MIN_NUM + "'");
		return ret.toString();
	}
}