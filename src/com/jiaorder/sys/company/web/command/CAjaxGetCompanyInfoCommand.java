package com.jiaorder.sys.company.web.command;

import com.jiaorder.sys.company.busi.CompanyBean;
import com.jiaorder.sys.company.vo.CompanyVO;
import com.jiaorder.sys.login.UserHelper;
import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.exception.DataAccessException;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import com.pabula.fw.utility.VO;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取公司信息
 */
public class CAjaxGetCompanyInfoCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) {
        String json = "";

        try {

            CompanyVO companyVO = new CompanyBean().getCompanyByServiceID(UserHelper.getUserInfo(request).getSERVICE_ID());

            json = JsonResultUtil.instance().addData(companyVO).json();


        } catch (DataAccessException e) {
            e.printStackTrace();
        }


        return json;

    }

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {

    }
}
