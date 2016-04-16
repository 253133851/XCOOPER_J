package com.jiaorder.shop.order.vo;

import com.pabula.fw.utility.VO;

/**
 * 订单详细VO
 * @author ZDK
 * 2016-03-28 10:12:45
 */
public class ShopOrderDetailVO implements VO {

	//ORDER_DETAIL_ID
	int ORDER_DETAIL_ID;

	//SERVICE_ID
	int SERVICE_ID;

	//ORDER_ID
	int ORDER_ID;

	//PRD_ID
	int PRD_ID;

	//促销id
	int PROMOTION_ID;

	//PRD_SPU
	String PRD_SPU;

	//PRD_SKU
	String PRD_SKU;

	//PRD_NAME
	String PRD_NAME;

	//商品别名
	String PRD_OTHER_NAME;

	//规格名1
	String SKU_NAME1;

	//规格内容1
	String SKU_CONTENT1;

	//规格名2
	String SKU_NAME2;

	//规格内容2
	String SKU_CONTENT2;

	//规格名3
	String SKU_NAME3;

	//规格内容3
	String SKU_CONTENT3;

	//备注
	String REMARK;

	//分类
	String CLASS;

	//单位
	String UNIT;

	//标签
	String TAG;

	//品牌
	String BRAND;

	//缩略图
	String IMG;

	//条形码
	String BAR_CODE;

	//市场价
	int PRICE;

	//成本价
	int COST;

	//折扣价
	int DISCOUNT_PRICE;

	//购买数量
	int BUY_COUNT;

	//商品类型
	int TYPE;

	/**
	 * @return 返回 ORDDER_DETAIL_ID ORDDER_DETAIL_ID
	 */
	public int getORDER_DETAIL_ID() {
		return ORDER_DETAIL_ID;
	}

	/**
	 * @param ORDER_DETAIL_ID 设置 ORDDER_DETAIL_ID 的值
	 */
	public void setORDER_DETAIL_ID(int ORDER_DETAIL_ID) {
		this.ORDER_DETAIL_ID = ORDER_DETAIL_ID;
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
	 * @return 返回 ORDER_ID ORDER_ID
	 */
	public int getORDER_ID() {
		return ORDER_ID;
	}

	/**
	 * @param ORDER_ID 设置 ORDER_ID 的值
	 */
	public void setORDER_ID(int ORDER_ID) {
		this.ORDER_ID = ORDER_ID;
	}

	/**
	 * @return 返回 PRD_ID PRD_ID
	 */
	public int getPRD_ID() {
		return PRD_ID;
	}

	/**
	 * @param PRD_ID 设置 PRD_ID 的值
	 */
	public void setPRD_ID(int PRD_ID) {
		this.PRD_ID = PRD_ID;
	}

	/**
	 * @return 返回 PROMOTION_ID 促销id
	 */
	public int getPROMOTION_ID() {
		return PROMOTION_ID;
	}

	/**
	 * @param PROMOTION_ID 设置 PROMOTION_ID 的值
	 */
	public void setPROMOTION_ID(int PROMOTION_ID) {
		this.PROMOTION_ID = PROMOTION_ID;
	}

	/**
	 * @return 返回 PRD_SPU PRD_SPU
	 */
	public String getPRD_SPU() {
		return PRD_SPU;
	}

	/**
	 * @param PRD_SPU 设置 PRD_SPU 的值
	 */
	public void setPRD_SPU(String PRD_SPU) {
		this.PRD_SPU = PRD_SPU;
	}

	/**
	 * @return 返回 PRD_SKU PRD_SKU
	 */
	public String getPRD_SKU() {
		return PRD_SKU;
	}

	/**
	 * @param PRD_SKU 设置 PRD_SKU 的值
	 */
	public void setPRD_SKU(String PRD_SKU) {
		this.PRD_SKU = PRD_SKU;
	}

	/**
	 * @return 返回 PRD_NAME PRD_NAME
	 */
	public String getPRD_NAME() {
		return PRD_NAME;
	}

	/**
	 * @param PRD_NAME 设置 PRD_NAME 的值
	 */
	public void setPRD_NAME(String PRD_NAME) {
		this.PRD_NAME = PRD_NAME;
	}

	/**
	 * @return 返回 PRD_OTHER_NAME 商品别名
	 */
	public String getPRD_OTHER_NAME() {
		return PRD_OTHER_NAME;
	}

	/**
	 * @param PRD_OTHER_NAME 设置 PRD_OTHER_NAME 的值
	 */
	public void setPRD_OTHER_NAME(String PRD_OTHER_NAME) {
		this.PRD_OTHER_NAME = PRD_OTHER_NAME;
	}

	/**
	 * @return 返回 SKU_NAME1 规格名1
	 */
	public String getSKU_NAME1() {
		return SKU_NAME1;
	}

	/**
	 * @param SKU_NAME1 设置 SKU_NAME1 的值
	 */
	public void setSKU_NAME1(String SKU_NAME1) {
		this.SKU_NAME1 = SKU_NAME1;
	}

	/**
	 * @return 返回 SKU_CONTENT1 规格内容1
	 */
	public String getSKU_CONTENT1() {
		return SKU_CONTENT1;
	}

	/**
	 * @param SKU_CONTENT1 设置 SKU_CONTENT1 的值
	 */
	public void setSKU_CONTENT1(String SKU_CONTENT1) {
		this.SKU_CONTENT1 = SKU_CONTENT1;
	}

	/**
	 * @return 返回 SKU_NAME2 规格名2
	 */
	public String getSKU_NAME2() {
		return SKU_NAME2;
	}

	/**
	 * @param SKU_NAME2 设置 SKU_NAME2 的值
	 */
	public void setSKU_NAME2(String SKU_NAME2) {
		this.SKU_NAME2 = SKU_NAME2;
	}

	/**
	 * @return 返回 SKU_CONTENT2 规格内容2
	 */
	public String getSKU_CONTENT2() {
		return SKU_CONTENT2;
	}

	/**
	 * @param SKU_CONTENT2 设置 SKU_CONTENT2 的值
	 */
	public void setSKU_CONTENT2(String SKU_CONTENT2) {
		this.SKU_CONTENT2 = SKU_CONTENT2;
	}

	/**
	 * @return 返回 SKU_NAME3 规格名3
	 */
	public String getSKU_NAME3() {
		return SKU_NAME3;
	}

	/**
	 * @param SKU_NAME3 设置 SKU_NAME3 的值
	 */
	public void setSKU_NAME3(String SKU_NAME3) {
		this.SKU_NAME3 = SKU_NAME3;
	}

	/**
	 * @return 返回 SKU_CONTENT3 规格内容3
	 */
	public String getSKU_CONTENT3() {
		return SKU_CONTENT3;
	}

	/**
	 * @param SKU_CONTENT3 设置 SKU_CONTENT3 的值
	 */
	public void setSKU_CONTENT3(String SKU_CONTENT3) {
		this.SKU_CONTENT3 = SKU_CONTENT3;
	}

	/**
	 * @return 返回 REMARK 备注
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
	 * @return 返回 CLASS 分类
	 */
	public String getCLASS() {
		return CLASS;
	}

	/**
	 * @param CLASS 设置 CLASS 的值
	 */
	public void setCLASS(String CLASS) {
		this.CLASS = CLASS;
	}

	/**
	 * @return 返回 UNIT 单位
	 */
	public String getUNIT() {
		return UNIT;
	}

	/**
	 * @param UNIT 设置 UNIT 的值
	 */
	public void setUNIT(String UNIT) {
		this.UNIT = UNIT;
	}

	/**
	 * @return 返回 TAG 标签
	 */
	public String getTAG() {
		return TAG;
	}

	/**
	 * @param TAG 设置 TAG 的值
	 */
	public void setTAG(String TAG) {
		this.TAG = TAG;
	}

	/**
	 * @return 返回 BRAND 品牌
	 */
	public String getBRAND() {
		return BRAND;
	}

	/**
	 * @param BRAND 设置 BRAND 的值
	 */
	public void setBRAND(String BRAND) {
		this.BRAND = BRAND;
	}

	/**
	 * @return 返回 IMG 缩略图
	 */
	public String getIMG() {
		return IMG;
	}

	/**
	 * @param IMG 设置 IMG 的值
	 */
	public void setIMG(String IMG) {
		this.IMG = IMG;
	}

	/**
	 * @return 返回 BAR_CODE 条形码
	 */
	public String getBAR_CODE() {
		return BAR_CODE;
	}

	/**
	 * @param BAR_CODE 设置 BAR_CODE 的值
	 */
	public void setBAR_CODE(String BAR_CODE) {
		this.BAR_CODE = BAR_CODE;
	}

	/**
	 * @return 返回 PRICE 市场价
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
	 * @return 返回 COST 成本价
	 */
	public int getCOST() {
		return COST;
	}

	/**
	 * @param COST 设置 COST 的值
	 */
	public void setCOST(int COST) {
		this.COST = COST;
	}

	/**
	 * @return 返回 DISCOUNT_PRICE 折扣价
	 */
	public int getDISCOUNT_PRICE() {
		return DISCOUNT_PRICE;
	}

	/**
	 * @param DISCOUNT_PRICE 设置 DISCOUNT_PRICE 的值
	 */
	public void setDISCOUNT_PRICE(int DISCOUNT_PRICE) {
		this.DISCOUNT_PRICE = DISCOUNT_PRICE;
	}

	/**
	 * @return 返回 BUY_COUNT 购买数量
	 */
	public int getBUY_COUNT() {
		return BUY_COUNT;
	}

	/**
	 * @param BUY_COUNT 设置 BUY_COUNT 的值
	 */
	public void setBUY_COUNT(int BUY_COUNT) {
		this.BUY_COUNT = BUY_COUNT;
	}

	/**
	 * @return 返回 TYPE 商品类型
	 */
	public int getTYPE() {
		return TYPE;
	}

	/**
	 * @param TYPE 设置 TYPE 的值
	 */
	public void setTYPE(int TYPE) {
		this.TYPE = TYPE;
	}

	public String toString() {
		StringBuffer ret = new StringBuffer();
		ret.append("toString : ");
		ret.append(", ORDER_DETAIL_ID='" + ORDER_DETAIL_ID + "'");
		ret.append(", SERVICE_ID='" + SERVICE_ID + "'");
		ret.append(", ORDER_ID='" + ORDER_ID + "'");
		ret.append(", PRD_ID='" + PRD_ID + "'");
		ret.append(", PROMOTION_ID='" + PROMOTION_ID + "'");
		ret.append(", PRD_SPU='" + PRD_SPU + "'");
		ret.append(", PRD_SKU='" + PRD_SKU + "'");
		ret.append(", PRD_NAME='" + PRD_NAME + "'");
		ret.append(", PRD_OTHER_NAME='" + PRD_OTHER_NAME + "'");
		ret.append(", SKU_NAME1='" + SKU_NAME1 + "'");
		ret.append(", SKU_CONTENT1='" + SKU_CONTENT1 + "'");
		ret.append(", SKU_NAME2='" + SKU_NAME2 + "'");
		ret.append(", SKU_CONTENT2='" + SKU_CONTENT2 + "'");
		ret.append(", SKU_NAME3='" + SKU_NAME3 + "'");
		ret.append(", SKU_CONTENT3='" + SKU_CONTENT3 + "'");
		ret.append(", REMARK='" + REMARK + "'");
		ret.append(", CLASS='" + CLASS + "'");
		ret.append(", UNIT='" + UNIT + "'");
		ret.append(", TAG='" + TAG + "'");
		ret.append(", BRAND='" + BRAND + "'");
		ret.append(", IMG='" + IMG + "'");
		ret.append(", BAR_CODE='" + BAR_CODE + "'");
		ret.append(", PRICE='" + PRICE + "'");
		ret.append(", COST='" + COST + "'");
		ret.append(", DISCOUNT_PRICE='" + DISCOUNT_PRICE + "'");
		ret.append(", BUY_COUNT='" + BUY_COUNT + "'");
		ret.append(", TYPE='" + TYPE + "'");
		return ret.toString();
	}
}