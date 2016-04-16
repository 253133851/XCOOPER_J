package com.jiaorder.member.notifyclass.web.command;

import com.jiaorder.member.notifyclass.busi.NotifyClassBean;
import com.jiaorder.sys.log.LogType;
import com.jiaorder.sys.log.LogUtil;
import com.jiaorder.sys.login.UserHelper;
import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.StrUtil;
import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import com.pabula.fw.utility.VO;

import javax.servlet.http.HttpServletRequest;

/**
 * 修改标签command
 */
public class CAjaxModifyNotifyClassCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) {

        NotifyClassBean bean = NotifyClassBean.newInstance();

        //接收标签相关数据
        int serviceId =  UserHelper.getServiceID(request);
        int classId = StrUtil.getNotNullIntValue(request.getParameter("classId"), 0);
        String name = request.getParameter("className");

        //日志记录
        LogUtil.operLog(LogType.OTHER,"修改通知分类",classId,request);

        if (bean.modifyNotifyClass(serviceId, classId, name)) {
            return JsonResultUtil.ok();
        }
        return JsonResultUtil.error();
    }

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {

    }

}
