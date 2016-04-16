package com.jiaorder.sys.member.vo;

import com.jiaorder.sys.user.vo.UserVO;

import java.sql.Timestamp;

/**
 * 会员VO
 * @author pabula
 * 2016-03-11 00:52:50
 */
public class MemberVO extends UserVO {

	int MEMBER_ID;

	//用户ID
	int UID;

	//SERVICE_ID
	int SERVICE_ID;

	//姓名
	String USER_NAME;

	//会员级别
	int MEMBER_LEVEL_ID;

	//所属角色
	String ROLE_ID_LIST;

	//所属地区
	String CLASS_ID_LIST;

	//编码
	String USER_CDOE;

	//职位
	String JOB;

	//手机
	String MOBILE;

	//邮箱
	String EMAIL;

	//QQ
	String QQ;

	//固定电话
	String TEL;

	//传真
	String FAX;

	//开始时间
	Timestamp BEGIN_DATETIME;

	//结束时间
	Timestamp END_DATETIME;

	//备注
	String REMARK;

	//所在地区_国家
	String ADDR_GUO;

	//所在地区_省
	String ADDR_SHENG;

	//所在地区_市
	String ADDR_SHI;

	//所在地区_县
	String ADDR_XIAN;

	//所在地区_区
	String ADDR_QU;

	//地址_详细地址
	String ADDR;

	//地址_邮编
	String ADDR_ZIP_CODE;

	//地址_物流编码
	String ADDR_EXPRESS_CODE;

	//财务_开户名称
	String FINANCE_ACCOUNT_NAME;

	//财务_开户银行
	String FINANCE_ACCOUNT_BANK;

	//财务_银行帐号
	String FINANCE_ACCOUNT_ID;

	//财务_发票抬头
	String FINANCE_INVOICE_TITLE;

	//财务_纳税人识别号
	String FINANCE_INVOICE_ID;

	//财务_支付宝
	String FINANCE_INVOICE_ALIPAY;

	//归属区
	String MEMBER_CLASS_ORDER_NUM;

	//级别名称
	String LEVEL_NAME;

	//每页大小	//用于分页
	int PageSize;
	//当前页码
	int PageIndex;


	public void setPageInfo(int pageSize,int PageIndex) {
		this.PageSize = pageSize;
		this.PageIndex = PageIndex;
	}

	public int getPageSize() {
		return PageSize;
	}

	public int getPageIndex() {
		return PageIndex;
	}

	public void setPageSize(int pageSize) {
		PageSize = pageSize;
	}


	public void setPageIndex(int pageIndex) {
		PageIndex = pageIndex;
	}

	public String getLEVEL_NAME() {
		return LEVEL_NAME;
	}

	public void setLEVEL_NAME(String LEVEL_NAME) {
		this.LEVEL_NAME = LEVEL_NAME;
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
	 * @return 返回 USER_NAME 姓名
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
	 * @return 返回 MEMBER_LEVEL_ID 会员级别
	 */
	public int getMEMBER_LEVEL_ID() {
		return MEMBER_LEVEL_ID;
	}

	/**
	 * @param MEMBER_LEVEL_ID 设置 MEMBER_LEVEL_ID 的值
	 */
	public void setMEMBER_LEVEL_ID(int MEMBER_LEVEL_ID) {
		this.MEMBER_LEVEL_ID = MEMBER_LEVEL_ID;
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
	 * @return 返回 CLASS_ID_LIST 所属地区
	 */
	public String getCLASS_ID_LIST() {
		return CLASS_ID_LIST;
	}

	/**
	 * @param CLASS_ID_LIST 设置 CLASS_ID_LIST 的值
	 */
	public void setCLASS_ID_LIST(String CLASS_ID_LIST) {
		this.CLASS_ID_LIST = CLASS_ID_LIST;
	}

	/**
	 * @return 返回 USER_CDOE 编码
	 */
	public String getUSER_CDOE() {
		return USER_CDOE;
	}

	/**
	 * @param USER_CDOE 设置 USER_CDOE 的值
	 */
	public void setUSER_CDOE(String USER_CDOE) {
		this.USER_CDOE = USER_CDOE;
	}

	/**
	 * @return 返回 JOB 职位
	 */
	public String getJOB() {
		return JOB;
	}

	/**
	 * @param JOB 设置 JOB 的值
	 */
	public void setJOB(String JOB) {
		this.JOB = JOB;
	}

	/**
	 * @return 返回 MOBILE 手机
	 */
	public String getMOBILE() {
		return MOBILE;
	}

	/**
	 * @param MOBILE 设置 MOBILE 的值
	 */
	public void setMOBILE(String MOBILE) {
		this.MOBILE = MOBILE;
	}

	/**
	 * @return 返回 EMAIL 邮箱
	 */
	public String getEMAIL() {
		return EMAIL;
	}

	/**
	 * @param EMAIL 设置 EMAIL 的值
	 */
	public void setEMAIL(String EMAIL) {
		this.EMAIL = EMAIL;
	}

	/**
	 * @return 返回 QQ QQ
	 */
	public String getQQ() {
		return QQ;
	}

	/**
	 * @param QQ 设置 QQ 的值
	 */
	public void setQQ(String QQ) {
		this.QQ = QQ;
	}

	/**
	 * @return 返回 TEL 固定电话
	 */
	public String getTEL() {
		return TEL;
	}

	/**
	 * @param TEL 设置 TEL 的值
	 */
	public void setTEL(String TEL) {
		this.TEL = TEL;
	}

	/**
	 * @return 返回 FAX 传真
	 */
	public String getFAX() {
		return FAX;
	}

	/**
	 * @param FAX 设置 FAX 的值
	 */
	public void setFAX(String FAX) {
		this.FAX = FAX;
	}

	/**
	 * @return 返回 BEGIN_DATETIME 开始时间
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
	 * @return 返回 END_DATETIME 结束时间
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
	 * @return 返回 ADDR_GUO 所在地区_国家
	 */
	public String getADDR_GUO() {
		return ADDR_GUO;
	}

	/**
	 * @param ADDR_GUO 设置 ADDR_GUO 的值
	 */
	public void setADDR_GUO(String ADDR_GUO) {
		this.ADDR_GUO = ADDR_GUO;
	}

	/**
	 * @return 返回 ADDR_SHENG 所在地区_省
	 */
	public String getADDR_SHENG() {
		return ADDR_SHENG;
	}

	/**
	 * @param ADDR_SHENG 设置 ADDR_SHENG 的值
	 */
	public void setADDR_SHENG(String ADDR_SHENG) {
		this.ADDR_SHENG = ADDR_SHENG;
	}

	/**
	 * @return 返回 ADDR_SHI 所在地区_市
	 */
	public String getADDR_SHI() {
		return ADDR_SHI;
	}

	/**
	 * @param ADDR_SHI 设置 ADDR_SHI 的值
	 */
	public void setADDR_SHI(String ADDR_SHI) {
		this.ADDR_SHI = ADDR_SHI;
	}

	/**
	 * @return 返回 ADDR_XIAN 所在地区_县
	 */
	public String getADDR_XIAN() {
		return ADDR_XIAN;
	}

	/**
	 * @param ADDR_XIAN 设置 ADDR_XIAN 的值
	 */
	public void setADDR_XIAN(String ADDR_XIAN) {
		this.ADDR_XIAN = ADDR_XIAN;
	}

	/**
	 * @return 返回 ADDR_QU 所在地区_区
	 */
	public String getADDR_QU() {
		return ADDR_QU;
	}

	/**
	 * @param ADDR_QU 设置 ADDR_QU 的值
	 */
	public void setADDR_QU(String ADDR_QU) {
		this.ADDR_QU = ADDR_QU;
	}

	/**
	 * @return 返回 ADDR 地址_详细地址
	 */
	public String getADDR() {
		return ADDR;
	}

	/**
	 * @param ADDR 设置 ADDR 的值
	 */
	public void setADDR(String ADDR) {
		this.ADDR = ADDR;
	}

	/**
	 * @return 返回 ADDR_ZIP_CODE 地址_邮编
	 */
	public String getADDR_ZIP_CODE() {
		return ADDR_ZIP_CODE;
	}

	/**
	 * @param ADDR_ZIP_CODE 设置 ADDR_ZIP_CODE 的值
	 */
	public void setADDR_ZIP_CODE(String ADDR_ZIP_CODE) {
		this.ADDR_ZIP_CODE = ADDR_ZIP_CODE;
	}

	/**
	 * @return 返回 ADDR_EXPRESS_CODE 地址_物流编码
	 */
	public String getADDR_EXPRESS_CODE() {
		return ADDR_EXPRESS_CODE;
	}

	/**
	 * @param ADDR_EXPRESS_CODE 设置 ADDR_EXPRESS_CODE 的值
	 */
	public void setADDR_EXPRESS_CODE(String ADDR_EXPRESS_CODE) {
		this.ADDR_EXPRESS_CODE = ADDR_EXPRESS_CODE;
	}

	/**
	 * @return 返回 FINANCE_ACCOUNT_NAME 财务_开户名称
	 */
	public String getFINANCE_ACCOUNT_NAME() {
		return FINANCE_ACCOUNT_NAME;
	}

	/**
	 * @param FINANCE_ACCOUNT_NAME 设置 FINANCE_ACCOUNT_NAME 的值
	 */
	public void setFINANCE_ACCOUNT_NAME(String FINANCE_ACCOUNT_NAME) {
		this.FINANCE_ACCOUNT_NAME = FINANCE_ACCOUNT_NAME;
	}

	/**
	 * @return 返回 FINANCE_ACCOUNT_BANK 财务_开户银行
	 */
	public String getFINANCE_ACCOUNT_BANK() {
		return FINANCE_ACCOUNT_BANK;
	}

	/**
	 * @param FINANCE_ACCOUNT_BANK 设置 FINANCE_ACCOUNT_BANK 的值
	 */
	public void setFINANCE_ACCOUNT_BANK(String FINANCE_ACCOUNT_BANK) {
		this.FINANCE_ACCOUNT_BANK = FINANCE_ACCOUNT_BANK;
	}

	/**
	 * @return 返回 FINANCE_ACCOUNT_ID 财务_银行帐号
	 */
	public String getFINANCE_ACCOUNT_ID() {
		return FINANCE_ACCOUNT_ID;
	}

	/**
	 * @param FINANCE_ACCOUNT_ID 设置 FINANCE_ACCOUNT_ID 的值
	 */
	public void setFINANCE_ACCOUNT_ID(String FINANCE_ACCOUNT_ID) {
		this.FINANCE_ACCOUNT_ID = FINANCE_ACCOUNT_ID;
	}

	/**
	 * @return 返回 FINANCE_INVOICE_TITLE 财务_发票抬头
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
	 * @return 返回 FINANCE_INVOICE_ID 财务_纳税人识别号
	 */
	public String getFINANCE_INVOICE_ID() {
		return FINANCE_INVOICE_ID;
	}

	/**
	 * @param FINANCE_INVOICE_ID 设置 FINANCE_INVOICE_ID 的值
	 */
	public void setFINANCE_INVOICE_ID(String FINANCE_INVOICE_ID) {
		this.FINANCE_INVOICE_ID = FINANCE_INVOICE_ID;
	}

	/**
	 * @return 返回 FINANCE_INVOICE_ALIPAY 财务_支付宝
	 */
	public String getFINANCE_INVOICE_ALIPAY() {
		return FINANCE_INVOICE_ALIPAY;
	}

	/**
	 * @param FINANCE_INVOICE_ALIPAY 设置 FINANCE_INVOICE_ALIPAY 的值
	 */
	public void setFINANCE_INVOICE_ALIPAY(String FINANCE_INVOICE_ALIPAY) {
		this.FINANCE_INVOICE_ALIPAY = FINANCE_INVOICE_ALIPAY;
	}

	public String getMEMBER_CLASS_ORDER_NUM() {
		return MEMBER_CLASS_ORDER_NUM;
	}

	public void setMEMBER_CLASS_ORDER_NUM(String MEMBER_CLASS_ORDER_NUM) {
		this.MEMBER_CLASS_ORDER_NUM = MEMBER_CLASS_ORDER_NUM;
	}

	public String toString() {
		StringBuffer ret = new StringBuffer();
		ret.append("toString : ");
		ret.append(", MEMBER_ID='" + MEMBER_ID + "'");
		ret.append(", UID='" + UID + "'");
		ret.append(", SERVICE_ID='" + SERVICE_ID + "'");
		ret.append(", USER_NAME='" + USER_NAME + "'");
		ret.append(", MEMBER_LEVEL_ID='" + MEMBER_LEVEL_ID + "'");
		ret.append(", ROLE_ID_LIST='" + ROLE_ID_LIST + "'");
		ret.append(", CLASS_ID_LIST='" + CLASS_ID_LIST + "'");
		ret.append(", USER_CDOE='" + USER_CDOE + "'");
		ret.append(", JOB='" + JOB + "'");
		ret.append(", MOBILE='" + MOBILE + "'");
		ret.append(", EMAIL='" + EMAIL + "'");
		ret.append(", QQ='" + QQ + "'");
		ret.append(", TEL='" + TEL + "'");
		ret.append(", FAX='" + FAX + "'");
		ret.append(", BEGIN_DATETIME='" + BEGIN_DATETIME + "'");
		ret.append(", END_DATETIME='" + END_DATETIME + "'");
		ret.append(", REMARK='" + REMARK + "'");
		ret.append(", ADDR_GUO='" + ADDR_GUO + "'");
		ret.append(", ADDR_SHENG='" + ADDR_SHENG + "'");
		ret.append(", ADDR_SHI='" + ADDR_SHI + "'");
		ret.append(", ADDR_XIAN='" + ADDR_XIAN + "'");
		ret.append(", ADDR_QU='" + ADDR_QU + "'");
		ret.append(", ADDR='" + ADDR + "'");
		ret.append(", ADDR_ZIP_CODE='" + ADDR_ZIP_CODE + "'");
		ret.append(", ADDR_EXPRESS_CODE='" + ADDR_EXPRESS_CODE + "'");
		ret.append(", FINANCE_ACCOUNT_NAME='" + FINANCE_ACCOUNT_NAME + "'");
		ret.append(", FINANCE_ACCOUNT_BANK='" + FINANCE_ACCOUNT_BANK + "'");
		ret.append(", FINANCE_ACCOUNT_ID='" + FINANCE_ACCOUNT_ID + "'");
		ret.append(", FINANCE_INVOICE_TITLE='" + FINANCE_INVOICE_TITLE + "'");
		ret.append(", FINANCE_INVOICE_ID='" + FINANCE_INVOICE_ID + "'");
		ret.append(", FINANCE_INVOICE_ALIPAY='" + FINANCE_INVOICE_ALIPAY + "'");
		return ret.toString();
	}
}