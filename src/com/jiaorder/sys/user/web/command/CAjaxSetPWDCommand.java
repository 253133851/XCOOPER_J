package com.jiaorder.sys.user.web.command;

import com.jiaorder.sys.user.busi.UserBean;
import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.RandomNum;
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
 * Created by sunsai on 2015/9/15.
 */
public class CAjaxSetPWDCommand implements Command {

	Logger log = Logger.getLogger(CAjaxSetPWDCommand.class);

	public void validate(HttpServletRequest request,VO vo,ValidateUtil validate){
		validate.validateIsEmptyOrNull(request.getParameter("RESET_MOBILE"),"请填写手机号");
		//validate.validateIsEmptyOrNull(request.getParameter("PWD"),"请输入密码");
		//validate.addError("请填写手机号!!");

		//得到SESSION中存储的验证码
		HttpSession session = request.getSession();
		String regValidateCode = "";
		if(null == session.getAttribute("resetPwdValidateCode")){
			validate.addError("请先点击获取验证码按钮,获取手机验证码");
		}else{
			regValidateCode = session.getAttribute("resetPwdValidateCode").toString();
		}



		//用户输入的验证码
		String inputCheckCode = request.getParameter("resetCheckcode");
		validate.validateIsEmptyOrNull(inputCheckCode,"请输入验证码");

		System.err.println("session resetPwdValidateCode validate code: " + regValidateCode + " input: " + inputCheckCode);

		if(!inputCheckCode.equalsIgnoreCase(regValidateCode)){
			validate.addError("您输入的验证码不正确！");
		}

		//检查用户是否被注册
		UserBean userBean = new UserBean();
		if(!userBean.isExistUserPhone(request.getParameter("RESET_MOBILE"))){
			validate.addError("抱歉，此手机号尚未注册，请先注册或检查手机号是否正确。");
		}


	}


	/**
	 * sunsai
	 * @param requesthelper
	 * @param request
	 * @return
	 * @throws ServletException
	 * @throws BusinessRuleException
	 * @throws DataAccessException
	 */
	public String execute(RequestHelper requesthelper, HttpServletRequest request) throws ServletException,BusinessRuleException, DataAccessException {

		//生成8位随机数
		RandomNum randomNum = new RandomNum();
		//sunsai 修改，因为语音验证码为4-8位
		randomNum.setRange(10000000, 99999900);
		try {
			randomNum.generateRandomObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String randomStr = randomNum.getRandom().toString();


		//发送新密码短信
		//SMSCheckCodeUtil.sendNewPwd(request.getParameter("RESET_MOBILE"), randomStr, "2");
		System.err.println("new pwd: " + randomStr);

		//重置新密码
		UserBean bean = new UserBean();
		bean.modifyUserPWD(request.getParameter("RESET_MOBILE"), randomStr);

		return JsonResultUtil.ok();
	}


}
