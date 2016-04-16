package com.jiaorder.member.notify.web.command;

import com.jiaorder.common.Page;
import com.jiaorder.member.notify.busi.NotifyBean;
import com.jiaorder.member.notify.vo.NotifyVO;
import com.jiaorder.sys.log.LogType;
import com.jiaorder.sys.log.LogUtil;
import com.jiaorder.sys.login.UserHelper;
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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by xq on 2016.3.21 16 39.
 */
public class CAjaxGetNotifyCommand implements Command {
    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) throws ServletException, BusinessRuleException, DataAccessException, SysException {

        NotifyVO filterMember = new NotifyVO();
        filterMember.setCLASS_ID(classId);
        filterMember.setFILTER_TIME(filterTime);
        filterMember.setPageInfo(pageSize, pageIndex);
        filterMember.setNOTIFY_SHOW(show);
        //加入page格式
        NotifyBean bean = NotifyBean.newInstance();
        Page<NotifyVO> page = new Page<>();
        page.setItems(bean.getNotifyColl(serviceId, filterMember, uId));
        page.setPageSize(filterMember.getPageSize());
        page.setCurrentPage(filterMember.getPageIndex());
        page.setTotalCount(bean.getNotifyColl_count(serviceId, filterMember, uId));
        //日志记录
        LogUtil.operLog(LogType.OTHER, "获取目标条件下的通知", "", request);
        return JsonResultUtil.instance().addData(page).json();
    }

    int serviceId, classId, uId, pageSize, pageIndex;
    String filterTime;
    String show;

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) throws RuleException {

        serviceId = UserHelper.getServiceID(request);
        uId = UserHelper.getUID(request);
        String type = UserHelper.getUserInfo(request).getTYPE();
        pageSize = StrUtil.getNotNullIntValue(request.getParameter("pageSize"), 1);
        pageIndex = StrUtil.getNotNullIntValue(request.getParameter("pageIndex"), 1);
        classId = StrUtil.getNotNullIntValue(request.getParameter("classId"), -100);
        filterTime = request.getParameter("filterTime");
        if (type.equals("manager")) {
            show = "ALL_USER";
        } else if (type.equals("member")) {
            show = "ALL_MEMBER";
        } else if (type.equals("creater")) {
            show = "ALL";
        }

    }
}
