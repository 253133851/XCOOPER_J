package com.jiaorder.sys.member.web.command;

import com.jiaorder.sys.log.LogType;
import com.jiaorder.sys.log.LogUtil;
import com.jiaorder.sys.login.UserHelper;
import com.jiaorder.sys.member.busi.MemberBean;
import com.jiaorder.sys.member.vo.MemberVO;
import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.StrUtil;
import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import com.pabula.fw.utility.VO;

import javax.servlet.http.HttpServletRequest;

/**
 * 添加用户
 */
public class CAjaxAddMemberCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) {

        MemberBean bean = MemberBean.newInstance();

        //接收用户分类相关数据
        int serviceId =  UserHelper.getServiceID(request);
        int id = StrUtil.getNotNullIntValue(request.getParameter("Id"), 0);

        //日志记录
        LogUtil.operLog(LogType.MEMEBER,"新增客户",id,request);

        //创建用户分类vo并添加
        MemberVO vo = new MemberVO();
        vo.setSERVICE_ID(serviceId);
        if (bean.addMember(vo)) {
            return JsonResultUtil.ok();
        }
        return JsonResultUtil.error();
    }

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {

    }
}
