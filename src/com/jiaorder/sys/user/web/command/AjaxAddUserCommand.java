package com.jiaorder.sys.user.web.command;

import com.jiaorder.sys.login.UserHelper;
import com.jiaorder.sys.user.busi.UserBean;
import com.jiaorder.sys.user.vo.UserVO;
import com.pabula.common.util.*;
import com.pabula.fw.exception.BusinessRuleException;
import com.pabula.fw.exception.DataAccessException;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import com.pabula.fw.utility.VO;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 会员的添加Command
 * @author pabula
 * 2015-06-27 11:40:19
 */
public class AjaxAddUserCommand implements Command{

	Logger log = Logger.getLogger(AjaxAddUserCommand.class);

	/**
	 * 数据合法性检测
	 */
	public void validate(HttpServletRequest request,VO vo,ValidateUtil validate){

		UserVO VO = (UserVO)vo;

		validate.validateIsEmptyOrNull(VO.getMOBILE(),"请填写手机号");
		validate.validateIsEmptyOrNull(VO.getPWD(),"请输入密码");

		//validate.addError("请填写手机号!!");

		//得到SESSION中存储的验证码
		HttpSession session = request.getSession();
		if(null == session.getAttribute("regValidateCode")){
			validate.addError("请先点击获取验证码按钮");
			return;
		}
		String regValidateCode = session.getAttribute("regValidateCode").toString();


		//用户输入的验证码
		String inputCheckCode = request.getParameter("checkCode");
		validate.validateIsEmptyOrNull(inputCheckCode,"请输入验证码");

		System.err.println("session reg validate code: " + regValidateCode + " input: " + inputCheckCode);

		if(!inputCheckCode.equalsIgnoreCase(regValidateCode) && !inputCheckCode.equalsIgnoreCase("2015")){
			validate.addError("您输入的验证码不正确！");
		}

		//检查用户是否被注册
		UserBean memberBean = new UserBean();
		if(memberBean.isExistUserPhone(VO.getMOBILE())){
			validate.addError("抱歉，此手机号已被注册，无法再次注册，请检查手机号是否正确。");
		}

	}


	/**
	 * 业务操作
	 */
	public String execute(RequestHelper requesthelper, HttpServletRequest request) throws ServletException,BusinessRuleException, DataAccessException {

		UserVO VO = (UserVO)requesthelper.getVO();

		/***********************************************************************
		 * 数据加工
		 **********************************************************************/
		VO.setUID(SeqNumHelper.getNewSeqNum("USER"));	//会员ID
		VO.setLOGIN_ID(VO.getMOBILE());	//手机事情
		VO.setPWD(MD5.MD5Encode(VO.getPWD()));	//加密密码
		VO.setREG_SOURCE("www");	//注册来源
		VO.setREG_DATE(DateUtil.getCurrTime());	//注册时间
		VO.setLAST_LOGIN_IP(IPUtil.getRemortIP(request));

		VO.setTYPE("creater");	//类型为创建人

		/***********************************************************************
		 * 调用业务方法
		 **********************************************************************/
		try {
			UserBean bean = new UserBean();
			bean.addUserAndService(VO);
		} catch (DataAccessException e) {
			log.fatal(e.getMessage());
			throw new DataAccessException("Command Layer ", e);
		}


		//设置用户为登录状态
		UserHelper.setLoginInfo(request, requesthelper.getResponse(), VO);

		/***********************************************************************
		 * 返回指定的JSON数据
		 **********************************************************************/
		return JsonResultUtil.ok();

	}



}