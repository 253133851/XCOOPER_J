package com.jiaorder.member.notify.web.command;

import com.jiaorder.common.Page;
import com.jiaorder.member.notify.busi.NotifyBean;
import com.jiaorder.member.notify.busi.NotifyReadBean;
import com.jiaorder.member.notify.vo.NotifyReadVO;
import com.jiaorder.member.notify.vo.NotifyVO;
import com.jiaorder.sys.log.LogType;
import com.jiaorder.sys.log.LogUtil;
import com.jiaorder.sys.login.UserHelper;
import com.pabula.common.util.DateUtil;
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
public class CAjaxGetNotifyByIdCommand implements Command {
    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) throws ServletException, BusinessRuleException, DataAccessException, SysException {

        NotifyReadVO notifyReadVO=new NotifyReadVO();
        notifyReadVO.setNOTIFY_ID(notifyId);
        notifyReadVO.setREAD_DATETIME(DateUtil.getCurrTime());
        notifyReadVO.setTARGET_ID(userId);
        notifyReadVO.setSERVICE_ID(serviceId);
        NotifyReadBean.newInstance().addNotifyRead(notifyReadVO);
        //加入page格式
        NotifyBean bean = NotifyBean.newInstance();
        Page<NotifyVO> page = new Page<>();
        page.setItems(bean.getNotifyByID(serviceId,notifyId));
        //日志记录
        LogUtil.operLog(LogType.OTHER,"获取目标条件下的通知","",request);
        return JsonResultUtil.instance().addData(page).json();
    }

    int serviceId,notifyId,userId;

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) throws RuleException {
        serviceId =  UserHelper.getServiceID(request);

        notifyId = StrUtil.getNotNullIntValue(request.getParameter("notifyId"), 0);
        userId = UserHelper.getUID(request);
    }
}
