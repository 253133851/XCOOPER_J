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
 * 修改客户级别command
 */
public class CAjaxModifyMemberLevelCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) {

        MemberLevelBean bean = MemberLevelBean.newInstance();

        //接收客户级别相关数据
        int serviceId = UserHelper.getServiceID(request);
        int MemberLevelId = StrUtil.getNotNullIntValue(request.getParameter("MemberLevelId"), 0);
        String MemberLevel = request.getParameter("MemberLevel");
        String PriceOff = request.getParameter("PriceOff");

        //日志记录
        LogUtil.operLog(LogType.MEMEBER,"修改客户级别",MemberLevelId,request);
        if (bean.modifyMemberLevel(serviceId, MemberLevelId, MemberLevel, PriceOff)) {
            return JsonResultUtil.ok();
        }
        return JsonResultUtil.error();
    }

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {

    }

}
