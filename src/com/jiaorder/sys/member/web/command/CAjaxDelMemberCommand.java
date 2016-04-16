package com.jiaorder.sys.member.web.command;

import com.jiaorder.sys.log.LogType;
import com.jiaorder.sys.log.LogUtil;
import com.jiaorder.sys.login.UserHelper;
import com.jiaorder.sys.member.busi.MemberBean;
import com.pabula.common.util.StrUtil;
import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import com.pabula.fw.utility.VO;

import javax.servlet.http.HttpServletRequest;

/**
 * 删除用户
 */
public class CAjaxDelMemberCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) {

        MemberBean bean = MemberBean.newInstance();

        //得到要删除的分类的id
        int id = StrUtil.getNotNullIntValue(request.getParameter("id"), 0);
        int serviceId =  UserHelper.getServiceID(request);


        //日志记录
        LogUtil.operLog(LogType.MEMEBER,"删除客户",id,request);
        return  null;
    }

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {

    }
}
