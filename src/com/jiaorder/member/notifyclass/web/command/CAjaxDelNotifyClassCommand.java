package com.jiaorder.member.notifyclass.web.command;

import com.jiaorder.member.notify.busi.NotifyBean;
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
 *删除单位command
 */
public class CAjaxDelNotifyClassCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) {

        NotifyClassBean bean = NotifyClassBean.newInstance();
        //得到要删除的单位id
        int newId = StrUtil.getNotNullIntValue(request.getParameter("newId"), 0);
        int oldId = StrUtil.getNotNullIntValue(request.getParameter("oldId"), 0);
        int serviceId =  UserHelper.getServiceID(request);


        //日志记录
        LogUtil.operLog(LogType.PRODUCT,"删除通知分类",oldId,request);

        if (oldId > 0) {
            //得到id后执行删除操作
            boolean result_exchange= NotifyBean.newInstance().modifyNotifyId(serviceId,oldId,newId);
            if(result_exchange) {
                boolean result = bean.delNotifyClass(serviceId, oldId);
                if (result) {
                    return JsonResultUtil.ok();
                }
            }
        } else {
            //没有得到正确的id
            JsonResultUtil.instance()
                    .addCode(JsonResultUtil.ERROR)
                    .addMsg("缺少必要的参数:")
                    .json();
        }
        return JsonResultUtil.error();
    }

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {

    }
}
