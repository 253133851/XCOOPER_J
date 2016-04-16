package com.jiaorder.sys.dept.web.command;

import com.jiaorder.sys.dept.busi.DeptBean;
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
 * 交换部门的排序command
 */
public class CAjaxOrderDeptCommand implements Command {
    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) {

        //得到要交换排序的部门id
        int serviceId = UserHelper.getServiceID(request);
        int oneDeptId = StrUtil.getNotNullIntValue(request.getParameter("oneDeptId"));
        int otherDeptId = StrUtil.getNotNullIntValue(request.getParameter("otherDeptId"));

        DeptBean bean = DeptBean.newInstance();
        boolean result = bean.changeDeptOrderNum(serviceId, oneDeptId, otherDeptId);
        if (result) {
            return JsonResultUtil.ok();
        }
        //日志记录
        LogUtil.operLog(LogType.PRODUCT, "改变部门顺序", oneDeptId + " & " + otherDeptId, request);
        return JsonResultUtil.error();
    }

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {

    }
}
