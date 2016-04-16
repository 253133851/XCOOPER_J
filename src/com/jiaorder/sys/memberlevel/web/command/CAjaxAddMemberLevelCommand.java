package com.jiaorder.sys.memberlevel.web.command;

import com.jiaorder.sys.log.LogType;
import com.jiaorder.sys.log.LogUtil;
import com.jiaorder.sys.login.UserHelper;
import com.jiaorder.sys.memberlevel.busi.MemberLevelBean;
import com.jiaorder.sys.memberlevel.vo.MemberLevelVO;
import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.StrUtil;
import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import com.pabula.fw.utility.VO;

import javax.servlet.http.HttpServletRequest;

/**
 * 添加用户级别command
 */
public class CAjaxAddMemberLevelCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) {

        MemberLevelBean bean = MemberLevelBean.newInstance();

        //接收用户级别相关数据
        int serviceId = UserHelper.getServiceID(request);
        int MemberLevelId = StrUtil.getNotNullIntValue(request.getParameter("MemberLevelId"), 0);
        String MemberLevel = request.getParameter("MemberLevel");
        String PriceOff = request.getParameter("PriceOff");

        //日志记录
        LogUtil.operLog(LogType.MEMEBER,"新增客户级别",MemberLevelId,request);

        //创建用户级别vo 并添加
        MemberLevelVO vo = new MemberLevelVO();
        vo.setLEVEL_NAME(MemberLevel);
        vo.setPRICE_OFF(PriceOff);
        vo.setMEMBER_LEVEL_ID(MemberLevelId);
        vo.setSERVICE_ID(serviceId);
        if (bean.addMemberLevel(serviceId, vo)) {
            return JsonResultUtil.ok();
        }
        return JsonResultUtil.error();
    }

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {

    }

}
