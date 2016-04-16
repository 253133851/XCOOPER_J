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
 *删除部门
 */
public class CAjaxDelDeptCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) {

        DeptBean bean = DeptBean.newInstance();

        //要删除的分类的id
        int deptId = StrUtil.getNotNullIntValue(request.getParameter("deptId"), 0);
        int serviceId =  UserHelper.getServiceID(request);

        //日志记录
        LogUtil.operLog(LogType.PRODUCT,"删除部门",deptId,request);

        if (deptId > 0) {
            //根据返回值判断有无子类 有的话不能删除
            boolean result = bean.delDeptAndChildsById(serviceId, deptId);
            if (result) {

                return JsonResultUtil.ok();
            } else {
                return JsonResultUtil.instance()
                        .addCode(JsonResultUtil.ERROR)
                        .addMsg("该部门下还有子部门，不能删除！")
                        .json();
            }
        } else {
            //没有正常的得到id
            return JsonResultUtil.instance()
                    .addCode(JsonResultUtil.ERROR)
                    .addMsg("缺少参数")
                    .json();
        }
    }

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {

    }
}
