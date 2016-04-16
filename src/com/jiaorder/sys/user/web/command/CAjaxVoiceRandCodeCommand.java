package com.jiaorder.sys.user.web.command;

import com.jiaorder.sys.user.busi.UserBean;
import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.StrUtil;
import com.pabula.common.util.ValidateUtil;
import com.pabula.common.util.VoiceCheckCodeUtil;
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
public class CAjaxVoiceRandCodeCommand implements Command {
    Logger log = Logger.getLogger(CAjaxVoiceRandCodeCommand.class);

    public void validate(HttpServletRequest request,VO vo,ValidateUtil validate){
        String phone = request.getParameter("MOBILE");

        validate.validateIsEmptyOrNull(phone,"请提供电话");

        if(phone.length() != 11 || phone.indexOf("1")!=0 || StrUtil.isNumber(phone)){
            validate.addError("请输入正确的手机号！");
        }

        //检查用户是否被注册
        UserBean memberBean = new UserBean();
        if(!memberBean.isExistUserPhone(phone)){
            validate.addError("抱歉，此手机号已被注册，无法再次注册，请检查手机号是否正确。");
        }
    }


    public String execute(RequestHelper requesthelper, HttpServletRequest request) throws ServletException,BusinessRuleException, DataAccessException {
        HttpSession session = request.getSession();
        Object randomStrObj = session.getAttribute("regValidateCode");
        String randomStr =null;

        if(null ==randomStrObj) {
            return "error";
        }
        randomStr = randomStrObj.toString();

        session.setMaxInactiveInterval(2*60);	//2分钟

        System.err.println("regValidateCode: " + randomStr);

        //发送语音验证码
        VoiceCheckCodeUtil.sendVoiceCheckCode(request.getParameter("MOBILE"),randomStr,"3");

        /***********************************************************************
         * 返回指定的JSON数据
         **********************************************************************/
        return JsonResultUtil.ok();

    }
}
