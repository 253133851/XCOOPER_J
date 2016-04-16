package com.jiaorder.sys.user.web.command;

import com.jiaorder.sys.login.UserHelper;
import com.jiaorder.sys.user.busi.UserBean;
import com.jiaorder.sys.user.vo.UserVO;
import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.MD5;
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
 * 修改用户信息
 */
public class CAjaxUpdatePwdCommand implements Command {

	Logger log = Logger.getLogger(CAjaxUpdatePwdCommand.class);

	public void validate(HttpServletRequest request, VO vo, ValidateUtil validate){
		validate.validateIsEmptyOrNull(request.getParameter("old"),"请输入原密码");
		validate.validateIsEmptyOrNull(request.getParameter("new"),"请输入新密码");

		if(!request.getParameter("new").equalsIgnoreCase(request.getParameter("new2"))){
			validate.addError("新密码与确认密码输入的不一样");
			return;
		}

		UserBean userBean = new UserBean();
		try {
			UserVO userVO = userBean.getUserByID(UserHelper.getUserInfo(request).getUID());
			String oldMd5Pwd = MD5.MD5Encode(request.getParameter("old"));

			if(!userVO.getPWD().equalsIgnoreCase(oldMd5Pwd)){
				validate.addError("原密码输入不正确");
			}

		} catch (DataAccessException e) {
			validate.addError("服务器异常:" + e.getMessage());
		}

	}


	/**
	 * 执行
	 * @param requesthelper
	 * @param request
	 * @return
	 * @throws ServletException
	 * @throws BusinessRuleException
	 * @throws DataAccessException
     */
	public String execute(RequestHelper requesthelper, HttpServletRequest request) throws ServletException,BusinessRuleException, DataAccessException {

		UserBean userBean = new UserBean();

		//修改密码
		userBean.modifyUserPWD(UserHelper.getUserInfo(request).getUID(),request.getParameter("new"));


		UserHelper.updateUserSession(request);

		return JsonResultUtil.ok();
	}


}
