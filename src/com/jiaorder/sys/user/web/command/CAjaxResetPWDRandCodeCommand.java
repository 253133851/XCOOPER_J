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
 * Created by sunsai on 2015/9/15.
 */
public class CAjaxResetPWDRandCodeCommand implements Command {

    Logger log = Logger.getLogger(CAjaxResetPWDRandCodeCommand.class);

    /**
     * 数据合法性检测
     */
    public void validate(HttpServletRequest request,VO vo,ValidateUtil validate){
        String phone = request.getParameter("RESET_MOBILE");

        validate.validateIsEmptyOrNull(phone,"请提供手机号");

        if(phone.length() != 11 || phone.indexOf("1")!=0 || StrUtil.isNumber(phone)){
            validate.addError("请输入正确的手机号！");
        }

        //检查用户是否被注册
        UserBean memberBean = new UserBean();
        if(!memberBean.isExistUserPhone(phone)){
            validate.addError("抱歉，此手机号尚未注册，请先注册或检查手机号是否正确。");
        }
    }


    /**
     * 业务操作
     */
    public String execute(RequestHelper requesthelper,HttpServletRequest request) throws ServletException,BusinessRuleException, DataAccessException {

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
        session.setMaxInactiveInterval(2*60);	//2分钟
        session.setAttribute("resetPwdValidateCode", randomStr);

        System.err.println("resetPwdValidateCode: " + randomStr);

        //发送短信验证码
       // SMSCheckCodeUtil.sendResetPwdRandCode(request.getParameter("RESET_MOBILE"), randomStr, "2");

        /***********************************************************************
         * 返回指定的JSON数据
         **********************************************************************/
        return JsonResultUtil.ok();

    }
}
