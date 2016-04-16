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
 * 更新部门command
 */
public class CAjaxUpdateDeptCommand implements Command {
    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) {

        //接收分类相关数据
        int serviceId = UserHelper.getServiceID(request);
        int deptId = StrUtil.getNotNullIntValue(request.getParameter("deptId"), 0);
        String deptName = request.getParameter("deptName");
        int parentDeptId = StrUtil.getNotNullIntValue(request.getParameter("parentDeptId"), 0);

        if (DeptBean.newInstance().updateDept(serviceId, deptId, parentDeptId, deptName)) {
            return JsonResultUtil.ok();
        }
        //日志记录
        LogUtil.operLog(LogType.PRODUCT, "更新部门", deptId + "  name: " + deptName, request);
        return JsonResultUtil.error();
    }

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {

    }
}
