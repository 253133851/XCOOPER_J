package com.jiaorder.sys.user.web.command;

import com.jiaorder.sys.log.LogType;
import com.jiaorder.sys.log.LogUtil;
import com.jiaorder.sys.login.UserHelper;
import com.jiaorder.sys.user.busi.UserBean;
import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.StrUtil;
import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import com.pabula.fw.utility.VO;

import javax.servlet.http.HttpServletRequest;

/**
 * 改变用户开通禁用状态
 * Created by xq on 2016.3.19 13 25.
 */
public class CAjaxUpdateUserStateCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) {

        UserBean bean = UserBean.newInstance();

        //日志记录
        LogUtil.operLog(LogType.MEMEBER,"修改客户状态",uids4update+" 状态修改为 "+state4update,request);

        boolean result = bean.updateMemberState(serviceId,uids4update, state4update);
        if (result) {
            return JsonResultUtil.ok();
        }
        return JsonResultUtil.error();
    }

    int serviceId,state4update;
    String uids4update;

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {

        serviceId = UserHelper.getServiceID(request);

        //用于批量开通或者禁用用户
        uids4update = request.getParameter("ids");
        state4update = StrUtil.getNotNullIntValue(request.getParameter("state"), 0);
    }

}
