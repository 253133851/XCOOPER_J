package com.jiaorder.sys.user.web.command;

import com.jiaorder.sys.login.UserHelper;
import com.jiaorder.sys.user.vo.UserVO;
import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import com.pabula.fw.utility.VO;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取用户信息
 */
public class CAjaxGetUserInfoCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) {

        UserVO userVO = UserHelper.getUserInfo(request);

        String json = JsonResultUtil.instance().addData(userVO).json("pwd,last_LOGIN_IP,reg_DATE,answer,add_USER_PATH,last_LOGIN_DATE,reg_SOURCE,type,state,service_ID,uid,audit_STATE,reg_VILIDATE_CODE,logins,login_ID");

        return json;
    }

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {

    }
}
