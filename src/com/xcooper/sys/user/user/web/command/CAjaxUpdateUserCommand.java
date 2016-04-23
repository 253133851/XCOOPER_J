package com.xcooper.sys.user.user.web.command;

import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.StrUtil;
import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.exception.BusinessRuleException;
import com.pabula.fw.exception.DataAccessException;
import com.pabula.fw.exception.RuleException;
import com.pabula.fw.exception.SysException;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import com.pabula.fw.utility.VO;
import com.xcooper.list.busi.ListBean;
import com.xcooper.list.vo.ListVO;
import com.xcooper.sys.user.user.busi.UserBean;
import com.xcooper.sys.user.user.vo.UserVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by zdk on 2016.4.17.
 */
public class CAjaxUpdateUserCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) throws ServletException, BusinessRuleException, DataAccessException, SysException {

        UserBean userBean = new UserBean();

        int uid = StrUtil.getNotNullIntValue(request.getParameter("uid"), 0);

        UserVO userVO = userBean.getUserByID(uid);

        //修改登录id loginId
        userVO.setLOGIN_ID(request.getParameter("loginId"));

        //修改密码 pwd
        userVO.setPWD(request.getParameter("pwd"));

        //修改密码问题 question
        userVO.setQUESTION(request.getParameter("question"));

        //修改用户状态 state
        userVO.setSTATE(StrUtil.getNotNullIntValue(request.getParameter("state"), 0));

        //修改e-mail email
        userVO.setEMAIL(request.getParameter("email"));

        //修改头像url faceUrl
        userVO.setFACE_URL(request.getParameter("faceUrl"));

        //修改个人说明 userRemark
        userVO.setUSER_REMARK(request.getParameter("userRemark"));

        //修改手机验证码 regVilidateCode
        userVO.setREG_VILIDATE_CODE(request.getParameter("regVilidateCode"));

        //修改手机号 phone
        userVO.setPHONE(request.getParameter("phone"));

        userBean.modifyUser(userVO);

        //返回ok

        return JsonResultUtil.instance().ok();
    }

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) throws RuleException {

    }
}
