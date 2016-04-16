package com.jiaorder.sys.company.web.command;

import com.jiaorder.sys.company.busi.CompanyBean;
import com.jiaorder.sys.company.vo.CompanyVO;
import com.jiaorder.sys.log.LogType;
import com.jiaorder.sys.log.LogUtil;
import com.jiaorder.sys.login.UserHelper;
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
 * 修改公司信息
 */
public class CAjaxUpdateCompanyInfoCommand implements Command {

	Logger log = Logger.getLogger(CAjaxUpdateCompanyInfoCommand.class);

	public void validate(HttpServletRequest request,VO vo,ValidateUtil validate){
		validate.validateIsEmptyOrNull(request.getParameter("company_NAME"),"请输入公司名称");
		validate.validateIsEmptyOrNull(request.getParameter("contact_TEL"),"请输入联系人手机号");
	}


	/**
	 * sunsai
	 * @param requesthelper
	 * @param request
	 * @return
	 * @throws ServletException
	 * @throws BusinessRuleException
	 * @throws DataAccessException
	 * @Loggable("注解SETA的参数")
	 */
	public String execute(RequestHelper requesthelper,HttpServletRequest request) throws ServletException,BusinessRuleException, DataAccessException {

		CompanyVO companyVO = new CompanyBean().getCompanyByServiceID(UserHelper.getUserInfo(request).getSERVICE_ID());

		companyVO.setCOMPANY_NAME(request.getParameter("company_NAME"));
		companyVO.setTYPE(request.getParameter("type"));
		companyVO.setADDR(request.getParameter("addr"));
		companyVO.setADDR_ZIP_CODE(request.getParameter("addr_ZIP_CODE"));
		companyVO.setTEL(request.getParameter("tel"));
		companyVO.setFAX(request.getParameter("fax"));
		companyVO.setWEBSITE(request.getParameter("website"));

		companyVO.setCONTACT_NAME(request.getParameter("contact_NAME"));
		companyVO.setCONTACT_TEL(request.getParameter("contact_TEL"));
		companyVO.setCONTACT_JOB(request.getParameter("contact_JOB"));
		companyVO.setCONTACT_EMAIL(request.getParameter("contact_EMAIL"));
		companyVO.setCONTACT_QQ(request.getParameter("contact_QQ"));

		companyVO.setFINANCE_INVOICE_ID(request.getParameter("finance_INVOICE_ID"));
		companyVO.setFINANCE_INVOICE_TITLE(request.getParameter("finance_INVOICE_TITLE"));

		companyVO.setSERVICE_PHONE(request.getParameter("service_PHONE"));


		//更新
		new CompanyBean().modifyCompany(companyVO);

		UserHelper.updateUserSession(request);


		//日志记录
		LogUtil.operLog(LogType.OTHER,"修改了公司信息",companyVO.getCOMPANY_ID(),request);

		return JsonResultUtil.ok();

		//Logger.log("添加参数",uid,"名称：" + name);
	}


}
