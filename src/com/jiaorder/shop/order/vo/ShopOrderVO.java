package com.jiaorder.shop.order.vo;

import com.pabula.fw.utility.VO;

import java.sql.Timestamp;

/**
 * 订单VO
 * @author ZDK
 * 2016-03-28 10:11:10
 */
public class ShopOrderVO implements VO {

	//ORDER_ID
	int ORDER_ID;

	//SERVICE_ID
	int SERVICE_ID;

	//会员id
	int MEMBER_ID;

	//收货地址id
	int DELIVERY_ADDRESS_ID;

	//会员姓名
	String USER_NAME;

	//订单编号
	String ORDER_NUM;

	//订单类型
	int TYPE;

	//订单状态
	int ORDER_STATE;

	//操作状态
	int STATE;

	//出库状态
	int REPOS_STATE;

	//发货状态
	int DELIVERY_STATE;

	//收款状态
	int MONEY_STATE;

	//作废原因
	String CANCEL_REASON;

	//会员级别名称
	String LEVEL_NAME;

	//折扣
	int PRICE_OFF;

	//是否特价
	int IS_SALE;

	//特价申请原因
	String SALE_REASON;

	//特价批准人
	String SALE_PERSONAL;

	//特价金额
	int SALE_PRICE;

	//合计金额
	int SUM_PRICE;

	//应付总额
	int OVER_PRICE;

	//支付方式
	String PAY_TYPE;

	//备注
	String REMARK;

	//交货日期
	Timestamp DELIVERY_DATETIME;

	//发票类型
	int INVOICE_TYPE;

	//发票抬头
	String FINANCE_INVOICE_TITLE;

	//发票内容
	String FINANCE_INVOICE_CONTENT;

	//标记1
	String SIGN1;

	//标记2
	String SIGN2;

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
	 * @return 返回 MEMBER_ID 会员id
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
	 * @return 返回 DELIVERY_ADDRESS_ID 收货地址id
	 */
	public int getDELIVERY_ADDRESS_ID() {
		return DELIVERY_ADDRESS_ID;
	}

	/**
	 * @param DELIVERY_ADDRESS_ID 设置 DELIVERY_ADDRESS_ID 的值
	 */
	public void setDELIVERY_ADDRESS_ID(int DELIVERY_ADDRESS_ID) {
		this.DELIVERY_ADDRESS_ID = DELIVERY_ADDRESS_ID;
	}

	/**
	 * @return 返回 USER_NAME 会员姓名
	 */
	public String getUSER_NAME() {
		return USER_NAME;
	}

	/**
	 * @param USER_NAME 设置 USER_NAME 的值
	 */
	public void setUSER_NAME(String USER_NAME) {
		this.USER_NAME = USER_NAME;
	}

	/**
	 * @return 返回 ORDER_NUM 订单编号
	 */
	public String getORDER_NUM() {
		return ORDER_NUM;
	}

	/**
	 * @param ORDER_NUM 设置 ORDER_NUM 的值
	 */
	public void setORDER_NUM(String ORDER_NUM) {
		this.ORDER_NUM = ORDER_NUM;
	}

	/**
	 * @return 返回 TYPE 订单类型
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

	/**
	 * @return 返回 ORDER_STATE 订单状态
	 */
	public int getORDER_STATE() {
		return ORDER_STATE;
	}

	/**
	 * @param ORDER_STATE 设置 ORDER_STATE 的值
	 */
	public void setORDER_STATE(int ORDER_STATE) {
		this.ORDER_STATE = ORDER_STATE;
	}

	/**
	 * @return 返回 STATE 操作状态
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
	 * @return 返回 REPOS_STATE 出库状态
	 */
	public int getREPOS_STATE() {
		return REPOS_STATE;
	}

	/**
	 * @param REPOS_STATE 设置 REPOS_STATE 的值
	 */
	public void setREPOS_STATE(int REPOS_STATE) {
		this.REPOS_STATE = REPOS_STATE;
	}

	/**
	 * @return 返回 DELIVERY_STATE 发货状态
	 */
	public int getDELIVERY_STATE() {
		return DELIVERY_STATE;
	}

	/**
	 * @param DELIVERY_STATE 设置 DELIVERY_STATE 的值
	 */
	public void setDELIVERY_STATE(int DELIVERY_STATE) {
		this.DELIVERY_STATE = DELIVERY_STATE;
	}

	/**
	 * @return 返回 MONEY_STATE 收款状态
	 */
	public int getMONEY_STATE() {
		return MONEY_STATE;
	}

	/**
	 * @param MONEY_STATE 设置 MONEY_STATE 的值
	 */
	public void setMONEY_STATE(int MONEY_STATE) {
		this.MONEY_STATE = MONEY_STATE;
	}

	/**
	 * @return 返回 CANCEL_REASON 作废原因
	 */
	public String getCANCEL_REASON() {
		return CANCEL_REASON;
	}

	/**
	 * @param CANCEL_REASON 设置 CANCEL_REASON 的值
	 */
	public void setCANCEL_REASON(String CANCEL_REASON) {
		this.CANCEL_REASON = CANCEL_REASON;
	}

	/**
	 * @return 返回 LEVEL_NAME 会员级别名称
	 */
	public String getLEVEL_NAME() {
		return LEVEL_NAME;
	}

	/**
	 * @param LEVEL_NAME 设置 LEVEL_NAME 的值
	 */
	public void setLEVEL_NAME(String LEVEL_NAME) {
		this.LEVEL_NAME = LEVEL_NAME;
	}

	/**
	 * @return 返回 PRICE_OFF 折扣
	 */
	public int getPRICE_OFF() {
		return PRICE_OFF;
	}

	/**
	 * @param PRICE_OFF 设置 PRICE_OFF 的值
	 */
	public void setPRICE_OFF(int PRICE_OFF) {
		this.PRICE_OFF = PRICE_OFF;
	}

	/**
	 * @return 返回 IS_SALE 是否特价
	 */
	public int getIS_SALE() {
		return IS_SALE;
	}

	/**
	 * @param IS_SALE 设置 IS_SALE 的值
	 */
	public void setIS_SALE(int IS_SALE) {
		this.IS_SALE = IS_SALE;
	}

	/**
	 * @return 返回 SALE_REASON 特价申请原因
	 */
	public String getSALE_REASON() {
		return SALE_REASON;
	}

	/**
	 * @param SALE_REASON 设置 SALE_REASON 的值
	 */
	public void setSALE_REASON(String SALE_REASON) {
		this.SALE_REASON = SALE_REASON;
	}

	/**
	 * @return 返回 SALE_PERSONAL 特价批准人
	 */
	public String getSALE_PERSONAL() {
		return SALE_PERSONAL;
	}

	/**
	 * @param SALE_PERSONAL 设置 SALE_PERSONAL 的值
	 */
	public void setSALE_PERSONAL(String SALE_PERSONAL) {
		this.SALE_PERSONAL = SALE_PERSONAL;
	}

	/**
	 * @return 返回 SALE_PRICE 特价金额
	 */
	public int getSALE_PRICE() {
		return SALE_PRICE;
	}

	/**
	 * @param SALE_PRICE 设置 SALE_PRICE 的值
	 */
	public void setSALE_PRICE(int SALE_PRICE) {
		this.SALE_PRICE = SALE_PRICE;
	}

	/**
	 * @return 返回 SUM_PRICE 合计金额
	 */
	public int getSUM_PRICE() {
		return SUM_PRICE;
	}

	/**
	 * @param SUM_PRICE 设置 SUM_PRICE 的值
	 */
	public void setSUM_PRICE(int SUM_PRICE) {
		this.SUM_PRICE = SUM_PRICE;
	}

	/**
	 * @return 返回 OVER_PRICE 应付总额
	 */
	public int getOVER_PRICE() {
		return OVER_PRICE;
	}

	/**
	 * @param OVER_PRICE 设置 OVER_PRICE 的值
	 */
	public void setOVER_PRICE(int OVER_PRICE) {
		this.OVER_PRICE = OVER_PRICE;
	}

	/**
	 * @return 返回 PAY_TYPE 支付方式
	 */
	public String getPAY_TYPE() {
		return PAY_TYPE;
	}

	/**
	 * @param PAY_TYPE 设置 PAY_TYPE 的值
	 */
	public void setPAY_TYPE(String PAY_TYPE) {
		this.PAY_TYPE = PAY_TYPE;
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
	 * @return 返回 DELIVERY_DATETIME 交货日期
	 */
	public Timestamp getDELIVERY_DATETIME() {
		return DELIVERY_DATETIME;
	}

	/**
	 * @param DELIVERY_DATETIME 设置 DELIVERY_DATETIME 的值
	 */
	public void setDELIVERY_DATETIME(Timestamp DELIVERY_DATETIME) {
		this.DELIVERY_DATETIME = DELIVERY_DATETIME;
	}

	/**
	 * @return 返回 INVOICE_TYPE 发票类型
	 */
	public int getINVOICE_TYPE() {
		return INVOICE_TYPE;
	}

	/**
	 * @param INVOICE_TYPE 设置 INVOICE_TYPE 的值
	 */
	public void setINVOICE_TYPE(int INVOICE_TYPE) {
		this.INVOICE_TYPE = INVOICE_TYPE;
	}

	/**
	 * @return 返回 FINANCE_INVOICE_TITLE 发票抬头
	 */
	public String getFINANCE_INVOICE_TITLE() {
		return FINANCE_INVOICE_TITLE;
	}

	/**
	 * @param FINANCE_INVOICE_TITLE 设置 FINANCE_INVOICE_TITLE 的值
	 */
	public void setFINANCE_INVOICE_TITLE(String FINANCE_INVOICE_TITLE) {
		this.FINANCE_INVOICE_TITLE = FINANCE_INVOICE_TITLE;
	}

	/**
	 * @return 返回 FINANCE_INVOICE_CONTENT 发票内容
	 */
	public String getFINANCE_INVOICE_CONTENT() {
		return FINANCE_INVOICE_CONTENT;
	}

	/**
	 * @param FINANCE_INVOICE_CONTENT 设置 FINANCE_INVOICE_CONTENT 的值
	 */
	public void setFINANCE_INVOICE_CONTENT(String FINANCE_INVOICE_CONTENT) {
		this.FINANCE_INVOICE_CONTENT = FINANCE_INVOICE_CONTENT;
	}

	/**
	 * @return 返回 SIGN1 标记1
	 */
	public String getSIGN1() {
		return SIGN1;
	}

	/**
	 * @param SIGN1 设置 SIGN1 的值
	 */
	public void setSIGN1(String SIGN1) {
		this.SIGN1 = SIGN1;
	}

	/**
	 * @return 返回 SIGN2 标记2
	 */
	public String getSIGN2() {
		return SIGN2;
	}

	/**
	 * @param SIGN2 设置 SIGN2 的值
	 */
	public void setSIGN2(String SIGN2) {
		this.SIGN2 = SIGN2;
	}

	public String toString() {
		StringBuffer ret = new StringBuffer();
		ret.append("toString : ");
		ret.append(", ORDER_ID='" + ORDER_ID + "'");
		ret.append(", SERVICE_ID='" + SERVICE_ID + "'");
		ret.append(", MEMBER_ID='" + MEMBER_ID + "'");
		ret.append(", DELIVERY_ADDRESS_ID='" + DELIVERY_ADDRESS_ID + "'");
		ret.append(", USER_NAME='" + USER_NAME + "'");
		ret.append(", ORDER_NUM='" + ORDER_NUM + "'");
		ret.append(", TYPE='" + TYPE + "'");
		ret.append(", ORDER_STATE='" + ORDER_STATE + "'");
		ret.append(", STATE='" + STATE + "'");
		ret.append(", REPOS_STATE='" + REPOS_STATE + "'");
		ret.append(", DELIVERY_STATE='" + DELIVERY_STATE + "'");
		ret.append(", MONEY_STATE='" + MONEY_STATE + "'");
		ret.append(", CANCEL_REASON='" + CANCEL_REASON + "'");
		ret.append(", LEVEL_NAME='" + LEVEL_NAME + "'");
		ret.append(", PRICE_OFF='" + PRICE_OFF + "'");
		ret.append(", IS_SALE='" + IS_SALE + "'");
		ret.append(", SALE_REASON='" + SALE_REASON + "'");
		ret.append(", SALE_PERSONAL='" + SALE_PERSONAL + "'");
		ret.append(", SALE_PRICE='" + SALE_PRICE + "'");
		ret.append(", SUM_PRICE='" + SUM_PRICE + "'");
		ret.append(", OVER_PRICE='" + OVER_PRICE + "'");
		ret.append(", PAY_TYPE='" + PAY_TYPE + "'");
		ret.append(", REMARK='" + REMARK + "'");
		ret.append(", DELIVERY_DATETIME='" + DELIVERY_DATETIME + "'");
		ret.append(", INVOICE_TYPE='" + INVOICE_TYPE + "'");
		ret.append(", FINANCE_INVOICE_TITLE='" + FINANCE_INVOICE_TITLE + "'");
		ret.append(", FINANCE_INVOICE_CONTENT='" + FINANCE_INVOICE_CONTENT + "'");
		ret.append(", SIGN1='" + SIGN1 + "'");
		ret.append(", SIGN2='" + SIGN2 + "'");
		return ret.toString();
	}
}