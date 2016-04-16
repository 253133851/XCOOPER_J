package com.jiaorder.sys.memberclass.web.command;

import com.jiaorder.sys.log.LogType;
import com.jiaorder.sys.log.LogUtil;
import com.jiaorder.sys.login.UserHelper;
import com.jiaorder.sys.memberclass.busi.MemberClassBean;
import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.StrUtil;
import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import com.pabula.fw.utility.VO;

import javax.servlet.http.HttpServletRequest;

/**
 * 更新用户分类(归属区)id
 */
public class CAjaxUpdateMemberClassCommand implements Command {
    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) {

        int serviceId = UserHelper.getServiceID(request);

        //得到用户分类相关数据
        int classId = StrUtil.getNotNullIntValue(request.getParameter("classId"), 0);
        String className = request.getParameter("className");
        int parentClassId = StrUtil.getNotNullIntValue(request.getParameter("parentClassId"), 0);

        //日志记录
        LogUtil.operLog(LogType.MEMEBER,"更新客户归属区",parentClassId+"  name : "+className,request);

        MemberClassBean bean = MemberClassBean.newInstance();
        boolean result = bean.updateMemberClass(serviceId, classId, parentClassId, className);
        if (result) {
            return JsonResultUtil.ok();
        }
        return JsonResultUtil.error();
    }

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {

    }
}
