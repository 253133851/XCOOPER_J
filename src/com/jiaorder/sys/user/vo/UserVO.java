package com.jiaorder.sys.user.vo;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.xcooper.ENV;
import com.pabula.common.util.StrUtil;
import com.pabula.fw.utility.VO;

import java.sql.Timestamp;

/**
 * 用户VO
 * @author pabula
 * 2016-03-11 00:49:22
 */
@JsonFilter(ENV.JSON_FILTER_NAME)
public class UserVO implements VO {

	//用户ID
	int UID;

	//登录ID
	String LOGIN_ID;

	//服务ID
	int SERVICE_ID;

	//类型  manager:管理员 member:会员 creater:创建人
	String TYPE;

	//密码
	String PWD;

	//注册日期
	Timestamp REG_DATE;

	//登录次数
	int LOGINS;

	//最后登录时间
	Timestamp LAST_LOGIN_DATE;

	//最后登录IP
	String LAST_LOGIN_IP;

	//密码问题
	String QUESTION;

	//问题答案
	String ANSWER;

	//用户状态
	int STATE;

	//审核状态
	int AUDIT_STATE;

	//姓名
	String USER_NAME;

	//EMAIL
	String EMAIL;

	//头像
	String FACE_URL;

	//个人说明/签名
	String USER_REMARK;

	//注册来源
	String REG_SOURCE;

	//注册验证码
	String REG_VILIDATE_CODE;

	//添加人路径
	String ADD_USER_PATH;

	//手机号
	String MOBILE;


	//编码
	String USER_CODE;

	//职位
	String JOB;

	//QQ
	String QQ;

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
	 * @return 返回 LOGIN_ID 登录ID
	 */
	public String getLOGIN_ID() {
		return LOGIN_ID;
	}

	/**
	 * @param LOGIN_ID 设置 LOGIN_ID 的值
	 */
	public void setLOGIN_ID(String LOGIN_ID) {
		this.LOGIN_ID = LOGIN_ID;
	}

	/**
	 * @return 返回 PWD 密码
	 */
	public String getPWD() {
		return PWD;
	}

	/**
	 * @param PWD 设置 PWD 的值
	 */
	public void setPWD(String PWD) {
		this.PWD = PWD;
	}

	/**
	 * @return 返回 REG_DATE 注册日期
	 */
	public Timestamp getREG_DATE() {
		return REG_DATE;
	}

	/**
	 * @param REG_DATE 设置 REG_DATE 的值
	 */
	public void setREG_DATE(Timestamp REG_DATE) {
		this.REG_DATE = REG_DATE;
	}

	/**
	 * @return 返回 LOGINS 登录次数
	 */
	public int getLOGINS() {
		return LOGINS;
	}

	/**
	 * @param LOGINS 设置 LOGINS 的值
	 */
	public void setLOGINS(int LOGINS) {
		this.LOGINS = LOGINS;
	}

	/**
	 * @return 返回 LAST_LOGIN_DATE 最后登录时间
	 */
	public Timestamp getLAST_LOGIN_DATE() {
		return LAST_LOGIN_DATE;
	}

	/**
	 * @param LAST_LOGIN_DATE 设置 LAST_LOGIN_DATE 的值
	 */
	public void setLAST_LOGIN_DATE(Timestamp LAST_LOGIN_DATE) {
		this.LAST_LOGIN_DATE = LAST_LOGIN_DATE;
	}

	/**
	 * @return 返回 LAST_LOGIN_IP 最后登录IP
	 */
	public String getLAST_LOGIN_IP() {
		return LAST_LOGIN_IP;
	}

	/**
	 * @param LAST_LOGIN_IP 设置 LAST_LOGIN_IP 的值
	 */
	public void setLAST_LOGIN_IP(String LAST_LOGIN_IP) {
		this.LAST_LOGIN_IP = LAST_LOGIN_IP;
	}

	/**
	 * @return 返回 QUESTION 密码问题
	 */
	public String getQUESTION() {
		return QUESTION;
	}

	/**
	 * @param QUESTION 设置 QUESTION 的值
	 */
	public void setQUESTION(String QUESTION) {
		this.QUESTION = QUESTION;
	}

	/**
	 * @return 返回 ANSWER 问题答案
	 */
	public String getANSWER() {
		return ANSWER;
	}

	/**
	 * @param ANSWER 设置 ANSWER 的值
	 */
	public void setANSWER(String ANSWER) {
		this.ANSWER = ANSWER;
	}

	/**
	 * @return 返回 STATE 用户状态
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
	 * @return 返回 AUDIT_STATE 审核状态
	 */
	public int getAUDIT_STATE() {
		return AUDIT_STATE;
	}

	/**
	 * @param AUDIT_STATE 设置 AUDIT_STATE 的值
	 */
	public void setAUDIT_STATE(int AUDIT_STATE) {
		this.AUDIT_STATE = AUDIT_STATE;
	}

	public String getUSER_NAME() {
		return USER_NAME;
	}

	/**
	 * 取得用于显示的USER_NAME
	 * @return
     */
	public String getUSER_NAMEByView(){
		if(StrUtil.isNull(USER_NAME)){
			return "小加加";
		}else{
			return USER_NAME;
		}
	}

	public void setUSER_NAME(String USER_NAME) {
		this.USER_NAME = USER_NAME;
	}

	/**
	 * @return 返回 EMAIL EMAIL
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
	 * @return 返回 FACE_URL 头像
	 */
	public String getFACE_URL() {
		return FACE_URL;
	}

	/**
	 * @param FACE_URL 设置 FACE_URL 的值
	 */
	public void setFACE_URL(String FACE_URL) {
		this.FACE_URL = FACE_URL;
	}

	/**
	 * @return 返回 USER_REMARK 个人说明/签名
	 */
	public String getUSER_REMARK() {
		return USER_REMARK;
	}

	/**
	 * @param USER_REMARK 设置 USER_REMARK 的值
	 */
	public void setUSER_REMARK(String USER_REMARK) {
		this.USER_REMARK = USER_REMARK;
	}

	/**
	 * @return 返回 REG_SOURCE 注册来源
	 */
	public String getREG_SOURCE() {
		return REG_SOURCE;
	}

	/**
	 * @param REG_SOURCE 设置 REG_SOURCE 的值
	 */
	public void setREG_SOURCE(String REG_SOURCE) {
		this.REG_SOURCE = REG_SOURCE;
	}

	/**
	 * @return 返回 REG_VILIDATE_CODE 注册验证码
	 */
	public String getREG_VILIDATE_CODE() {
		return REG_VILIDATE_CODE;
	}

	/**
	 * @param REG_VILIDATE_CODE 设置 REG_VILIDATE_CODE 的值
	 */
	public void setREG_VILIDATE_CODE(String REG_VILIDATE_CODE) {
		this.REG_VILIDATE_CODE = REG_VILIDATE_CODE;
	}

	/**
	 * @return 返回 ADD_USER_PATH 添加人路径
	 */
	public String getADD_USER_PATH() {
		return ADD_USER_PATH;
	}

	/**
	 * @param ADD_USER_PATH 设置 ADD_USER_PATH 的值
	 */
	public void setADD_USER_PATH(String ADD_USER_PATH) {
		this.ADD_USER_PATH = ADD_USER_PATH;
	}

	public int getSERVICE_ID() {
		return SERVICE_ID;
	}

	public void setSERVICE_ID(int SERVICE_ID) {
		this.SERVICE_ID = SERVICE_ID;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String TYPE) {
		this.TYPE = TYPE;
	}

	/**
	 * @return 返回 MOBILE 手机号
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

	public String getUSER_CODE() {
		return USER_CODE;
	}

	public void setUSER_CODE(String USER_CODE) {
		this.USER_CODE = USER_CODE;
	}

	public String getJOB() {
		return JOB;
	}

	public void setJOB(String JOB) {
		this.JOB = JOB;
	}

	public String getQQ() {
		return QQ;
	}

	public void setQQ(String QQ) {
		this.QQ = QQ;
	}

	public String toString() {
		StringBuffer ret = new StringBuffer();
		ret.append("toString : ");
		ret.append(", UID='" + UID + "'");
		ret.append(", LOGIN_ID='" + LOGIN_ID + "'");
		ret.append(", PWD='" + PWD + "'");
		ret.append(", REG_DATE='" + REG_DATE + "'");
		ret.append(", LOGINS='" + LOGINS + "'");
		ret.append(", LAST_LOGIN_DATE='" + LAST_LOGIN_DATE + "'");
		ret.append(", LAST_LOGIN_IP='" + LAST_LOGIN_IP + "'");
		ret.append(", QUESTION='" + QUESTION + "'");
		ret.append(", ANSWER='" + ANSWER + "'");
		ret.append(", STATE='" + STATE + "'");
		ret.append(", AUDIT_STATE='" + AUDIT_STATE + "'");
		ret.append(", EMAIL='" + EMAIL + "'");
		ret.append(", FACE_URL='" + FACE_URL + "'");
		ret.append(", USER_REMARK='" + USER_REMARK + "'");
		ret.append(", REG_SOURCE='" + REG_SOURCE + "'");
		ret.append(", REG_VILIDATE_CODE='" + REG_VILIDATE_CODE + "'");
		ret.append(", ADD_USER_PATH='" + ADD_USER_PATH + "'");
		ret.append(", MOBILE='" + MOBILE + "'");
		return ret.toString();
	}
}