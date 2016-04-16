package com.jiaorder.sys.login.web.command;

import com.jiaorder.sys.log.LogType;
import com.jiaorder.sys.log.LogUtil;
import com.jiaorder.sys.login.UserHelper;
import com.jiaorder.sys.user.busi.UserBean;
import com.jiaorder.sys.user.vo.UserVO;
import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.MD5;
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

/**
 * 会员登录
 */
public class CAjaxLoginCommand implements Command {

	Logger log = Logger.getLogger(CAjaxLoginCommand.class);

	UserVO uservo = null;
	UserBean userBean = new UserBean();


	/**
	 * 数据合法性检测
	 */
	public void validate(HttpServletRequest request, VO vo, ValidateUtil validate){

		String lid = request.getParameter("mobile");
		String pwd = request.getParameter("password");

		validate.validateIsEmptyOrNull(lid,"请提供登录用户名");
		validate.validateIsEmptyOrNull(pwd, "请提供用户密码");

		//非法字符检测
		if(!StrUtil.checkStringRule(lid)){
			validate.addError("提供的登录名非法");
		}

		//如果用户与密码都提供了
		if(StrUtil.isNotNull(lid) && StrUtil.isNotNull(pwd)){
			log.error("安全事件: [" + lid + "] 正在试图登录! ");

			try {
				//获取用户信息
				uservo  = userBean.getUserForLoginID(lid);

				//用户是否存在
				if(null == uservo){
					validate.addError("不存在此用户，请检查用户名是否正确");
					return;
				}

				//密码是否正确
				String dbPwd = uservo.getPWD();	//密码

				//如果数据库中取得的PWD与算出的PWD相同，则认证通过
				String md5Pwd = MD5.MD5Encode(pwd);
				if(!dbPwd.equals(md5Pwd)){//已经移位的MD5编码
					validate.addError("用户密码错误！");

					//日志记录
					LogUtil.operLog(LogType.LOGIN,"用户密码错误",lid,request);
				}
			} catch (DataAccessException e) {
				e.printStackTrace();
				validate.addError("出错啦："+ e.getMessage());
			}

		}

	}


	/**
	 * 业务操作
	 */
	public String execute(RequestHelper requesthelper, HttpServletRequest request) throws ServletException,BusinessRuleException, DataAccessException {
//		//登录成功与失败页
//		String loginPage = StrUtil.getNotNullStringValue(request.getParameter("loginPage"),"/");
//		String loginErrpage = StrUtil.getNotNullStringValue(request.getParameter("LoginErrpage"),"/common/member_login_err.jsp");
//
//		//是否在登录后，返回原登录位置
//		int isReturnRequetURL = StrUtil.getNotNullIntValue(request.getParameter("isReturnThisPage"),0);
//		if(isReturnRequetURL == 1){
//			loginPage = request.getHeader("Referer");
//		}

		//登陆成功
		UserHelper.setLoginInfo(request, requesthelper.getResponse(), uservo);

		//日志记录
		LogUtil.operLog(LogType.LOGIN,"成功登录",uservo.getUID(),request);

		return JsonResultUtil.ok();
	}



}