package com.jiaorder.sys.memberlevel.web.command;

import com.jiaorder.sys.log.LogType;
import com.jiaorder.sys.log.LogUtil;
import com.jiaorder.sys.login.UserHelper;
import com.jiaorder.sys.memberlevel.busi.MemberLevelBean;
import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.StrUtil;
import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import com.pabula.fw.utility.VO;

import javax.servlet.http.HttpServletRequest;

/**
 * 交换两个客户级别的排序command
 */
public class CAjaxUpdateMemberLevelOrderNumCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) {

        MemberLevelBean bean = MemberLevelBean.newInstance();

        //得到要交换排序的客户级别id
        int serviceId = UserHelper.getServiceID(request);
        int MemberLevelId1 = StrUtil.getNotNullIntValue(request.getParameter("MemberLevelId1"), 0);
        int MemberLevelId2 = StrUtil.getNotNullIntValue(request.getParameter("MemberLevelId2"), 0);

        //日志记录
        LogUtil.operLog(LogType.MEMEBER,"修改客户级别排序",MemberLevelId1+" & "+MemberLevelId2,request);

        if (bean.exchangeMemberLevelOrderNum(serviceId, MemberLevelId1,MemberLevelId2)) {
            return JsonResultUtil.ok();
        }
        return JsonResultUtil.error();
    }

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {

    }


}
