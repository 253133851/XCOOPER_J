package com.jiaorder.member.notifyclass.web.command;

import com.jiaorder.common.Page;
import com.jiaorder.member.notifyclass.busi.NotifyClassBean;
import com.jiaorder.member.notifyclass.vo.NotifyClassVO;
import com.jiaorder.sys.log.LogType;
import com.jiaorder.sys.log.LogUtil;
import com.jiaorder.sys.login.UserHelper;
import com.pabula.common.util.JsonResultUtil;
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
public class CAjaxGetNotifyClassCommand implements Command {
    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) throws ServletException, BusinessRuleException, DataAccessException, SysException {


        //加入page格式
        NotifyClassBean bean = NotifyClassBean.newInstance();
        Page<NotifyClassVO> page = new Page<>();
        page.setItems(bean.getNotifyClassColl(serviceId));

        //日志记录
        LogUtil.operLog(LogType.OTHER,"获取通知分类集合","",request);
        return JsonResultUtil.instance().addData(page).json();
    }

    int serviceId;

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) throws RuleException {
        serviceId =  UserHelper.getServiceID(request);
    }
}
