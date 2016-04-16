package com.jiaorder.sys.company.web.command;

import com.jiaorder.sys.company.busi.CompanyBean;
import com.jiaorder.sys.company.vo.CompanyVO;
import com.jiaorder.sys.log.LogType;
import com.jiaorder.sys.log.LogUtil;
import com.jiaorder.sys.login.UserHelper;
import com.jiaorder.sys.service.busi.ServiceBean;
import com.jiaorder.sys.user.busi.UserBean;
import com.jiaorder.sys.user.vo.UserVO;
import com.pabula.common.util.JsonResultUtil;
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
 * 修改公司主要数据
 */
public class CAjaxUpdateCompanyMainInfoCommand implements Command {

	Logger log = Logger.getLogger(CAjaxUpdateCompanyMainInfoCommand.class);

	/**
	 * 数据合法性检测
	 */
	public void validate(HttpServletRequest request, VO vo, ValidateUtil validate){
		validate.validateIsEmptyOrNull(request.getParameter("companyname"),"请提供公司名称");
		validate.validateIsEmptyOrNull(request.getParameter("type"),"请选择行业");
		validate.validateIsEmptyOrNull(request.getParameter("username"),"请填写您的姓名");

	}


	/**
	 * 业务操作
	 */
	public String execute(RequestHelper requesthelper, HttpServletRequest request) throws ServletException,BusinessRuleException, DataAccessException {

		String companyname = request.getParameter("companyname");
		String type = request.getParameter("type");
		String paramRemark = StrUtil.strArrayToStr(request.getParameterValues("paramremark"),",");
		String username = request.getParameter("username");

		UserVO userVO = UserHelper.getUserInfo(request);
		int serviceID = userVO.getSERVICE_ID();


		//更新service表
		ServiceBean serviceBean = new ServiceBean();
		serviceBean.modifyServiceParam("",paramRemark,serviceID);

		//更新company表
		CompanyBean companyBean = new CompanyBean();
		CompanyVO companyVO = companyBean.getCompanyByServiceID(serviceID);
		if(null != companyVO){
			companyVO.setCOMPANY_NAME(companyname);
			companyVO.setTYPE(type);

			companyBean.modifyCompany(companyVO);
		}

		//更新用户信息
		if(StrUtil.isNotNull(username)){
			UserVO newUserVO = new UserBean().getUserByID(userVO.getUID());	//从数据库拉出最新的用户信息
			newUserVO.setUSER_NAME(username);

			new UserBean().modifyUser(newUserVO);

			//更新用户SESSION
			UserHelper.updateUserSession(request);
		}

		//日志记录
		LogUtil.operLog(LogType.OTHER,"完善了公司信息",companyVO.getCOMPANY_ID(),request);


		/***********************************************************************
		 * 返回指定的JSON数据
		 **********************************************************************/
		return JsonResultUtil.ok();

	}



}

