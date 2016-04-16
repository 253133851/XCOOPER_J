package com.jiaorder.member.notifyclass.web.command;

import com.jiaorder.member.notifyclass.busi.NotifyClassBean;
import com.jiaorder.member.notifyclass.vo.NotifyClassVO;
import com.jiaorder.sys.log.LogType;
import com.jiaorder.sys.log.LogUtil;
import com.jiaorder.sys.login.UserHelper;
import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import com.pabula.fw.utility.VO;

import javax.servlet.http.HttpServletRequest;

/**
 * 新增单位command
 */
public class CAjaxAddNotifyClassCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) {

        //日志记录
        LogUtil.operLog(LogType.OTHER,"新增通知分类",name,request);

        //创建单位vo对象并且新增到数据库
        NotifyClassVO vo = new NotifyClassVO();
        vo.setNAME(name);
        vo.setSERVICE_ID(serviceId);
        if (NotifyClassBean.newInstance().addNotifyClass(vo)) {
            return JsonResultUtil.ok();
        }
        return JsonResultUtil.error();
    }

    int serviceId;
    String name;

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {
        //接收单位相关数据
        serviceId =  UserHelper.getServiceID(request);
        name = request.getParameter("name");

    }

}
