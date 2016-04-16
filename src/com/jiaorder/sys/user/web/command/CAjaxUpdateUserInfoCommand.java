package com.jiaorder.sys.user.web.command;

import com.jiaorder.sys.login.UserHelper;
import com.jiaorder.sys.user.busi.UserBean;
import com.jiaorder.sys.user.vo.UserVO;
import com.pabula.common.util.JsonResultUtil;
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
public class CAjaxUpdateUserInfoCommand implements Command {

	Logger log = Logger.getLogger(CAjaxUpdateUserInfoCommand.class);

	public void validate(HttpServletRequest request,VO vo,ValidateUtil validate){
		validate.validateIsEmptyOrNull(request.getParameter("user_NAME"),"请输入姓名");
		validate.validateIsEmptyOrNull(request.getParameter("mobile"),"请输入手机号");
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

		UserBean userBean = new UserBean();

		//取得最新的USERVO
		UserVO userVO = userBean.getUserByID(UserHelper.getUserInfo(request).getUID());

		userVO.setUSER_NAME(request.getParameter("user_NAME"));
		userVO.setMOBILE(request.getParameter("mobile"));
		userVO.setJOB(request.getParameter("job"));
		userVO.setQQ(request.getParameter("qq"));
		userVO.setUSER_CODE(request.getParameter("user_CODE"));
		userVO.setEMAIL(request.getParameter("email"));

		//更新
		userBean.modifyUser(userVO);

		UserHelper.updateUserSession(request);

		return JsonResultUtil.ok();
	}


}
