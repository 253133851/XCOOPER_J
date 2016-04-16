package com.jiaorder.member.guestbook.web.command;

import com.jiaorder.common.Page;
import com.jiaorder.member.guestbook.busi.GuestbookBean;
import com.jiaorder.member.guestbook.vo.GuestbookVO;
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
public class CAjaxGetGuestbookCommand implements Command {
    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) throws ServletException, BusinessRuleException, DataAccessException, SysException {

        GuestbookVO filter_guestbook=new GuestbookVO();
        filter_guestbook.setSTATE(state);
        filter_guestbook.setPageInfo(pageSize,pageIndex);
        //加入page格式
        GuestbookBean bean = GuestbookBean.newInstance();
        Page<GuestbookVO> page = new Page<>();
        page.setPageSize(pageSize);
        page.setCurrentPage(pageIndex);
        page.setItems(bean.getGuestbook(serviceId,filter_guestbook));
        page.setTotalCount(bean.getGuestbookcount(serviceId,filter_guestbook));
        //日志记录
        LogUtil.operLog(LogType.OTHER,"获取客户回复","",request);
        return JsonResultUtil.instance().addData(page).json();
    }

    int serviceId;
    int state,pageSize,pageIndex;

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) throws RuleException {
        serviceId =  UserHelper.getServiceID(request);
        state = StrUtil.getNotNullIntValue(request.getParameter("state"), 0);
        pageSize = StrUtil.getNotNullIntValue(request.getParameter("pageSize"), 1);
        pageIndex = StrUtil.getNotNullIntValue(request.getParameter("pageIndex"), 1);

    }

}
