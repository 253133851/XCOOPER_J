package com.jiaorder.sys.member.web.command;

import com.jiaorder.sys.log.LogType;
import com.jiaorder.sys.log.LogUtil;
import com.jiaorder.sys.login.UserHelper;
import com.jiaorder.sys.member.busi.MemberBean;
import com.jiaorder.sys.member.vo.MemberVO;
import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import com.pabula.fw.utility.VO;

import javax.servlet.http.HttpServletRequest;

/**
 * 更新用户
 */
public class CAjaxUpdateMemberCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) {

        //日志记录
        LogUtil.operLog(LogType.MEMEBER,"更新客户","",request);

        MemberBean bean = MemberBean.newInstance();
        MemberVO memberVO=new MemberVO();
        boolean result = bean.modifyMember(serviceId, memberVO );
        if (result) {
            return JsonResultUtil.ok();
        }
        return JsonResultUtil.error();
    }

    int serviceId;

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {

        serviceId =  UserHelper.getServiceID(request);
        //得到用户分类相关数据

    }
}
