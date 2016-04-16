package com.jiaorder.sys.dept.web.command;

import com.jiaorder.sys.dept.busi.DeptBean;
import com.jiaorder.sys.dept.vo.DeptVO;
import com.jiaorder.sys.log.LogType;
import com.jiaorder.sys.log.LogUtil;
import com.jiaorder.sys.login.UserHelper;
import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.StrUtil;
import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.exception.DataAccessException;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import com.pabula.fw.utility.VO;

import javax.servlet.http.HttpServletRequest;

/**
 * 新增部门command
 */
public class CAjaxAddDeptCommand implements Command {
    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) {

        DeptBean bean = DeptBean.newInstance();

        //接收分类相关数据
        int serviceId = UserHelper.getServiceID(request);
        int parentDeptId = StrUtil.getNotNullIntValue(request.getParameter("parentDeptId"), 0);
        String deptName = request.getParameter("deptName");

        //创建分类vo 并添加
        DeptVO vo = new DeptVO();
        vo.setPARENT_DEPT_ID(parentDeptId);
        vo.setDEPT_NAME(deptName);
        vo.setSERVICE_ID(serviceId);

        try {
            if (bean.addDept(vo)) {  //日志记录
                LogUtil.operLog(LogType.PRODUCT, "新增部门", parentDeptId, request);
                return JsonResultUtil.ok();
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return JsonResultUtil.error();
    }

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {

    }
}
