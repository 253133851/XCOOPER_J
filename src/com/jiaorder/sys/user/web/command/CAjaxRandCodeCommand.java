package com.jiaorder.sys.user.web.command;

import com.jiaorder.sys.user.busi.UserBean;
import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.RandomNum;
import com.pabula.common.util.StrUtil;
import com.pabula.common.util.ValidateUtil;
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
 * 获得会员信息的JSON报文
 */
public class CAjaxRandCodeCommand implements Command {

	Logger log = Logger.getLogger(CAjaxRandCodeCommand.class);

	/**
	 * 数据合法性检测
	 */
	public void validate(HttpServletRequest request, VO vo, ValidateUtil validate){
		String phone = request.getParameter("MOBILE");

		validate.validateIsEmptyOrNull(phone,"请提供电话");

		if(phone.length() != 11 || phone.indexOf("1")!=0 || StrUtil.isNumber(phone)){
			validate.addError("请输入正确的手机号！");
		}

		//检查用户是否被注册
		UserBean userBean = new UserBean();
		if(userBean.isExistUserPhone(phone)){
			validate.addError("抱歉，此手机号已被注册，无法再次注册，请检查手机号是否正确。");
		}
	}


	/**
	 * 业务操作
	 */
	public String execute(RequestHelper requesthelper, HttpServletRequest request) throws ServletException,BusinessRuleException, DataAccessException {

		//生成6位随机数
		RandomNum randomNum = new RandomNum();
		//sunsai 修改，因为语音验证码为4-8位
		randomNum.setRange(100000, 999999);
		try {
			randomNum.generateRandomObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String randomStr = randomNum.getRandom().toString();

		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(5*60);	// 5分钟
		session.setAttribute("regValidateCode", randomStr);

		System.err.println(request.getParameter("MOBILE") + " ： regValidateCode: " + randomStr);

		//发送短信验证码
		//SMSCheckCodeUtil.sendCheckCode(request.getParameter("MOBILE"),randomStr,"2");

		/***********************************************************************
		 * 返回指定的JSON数据
		 **********************************************************************/
		return JsonResultUtil.ok();

	}



}

