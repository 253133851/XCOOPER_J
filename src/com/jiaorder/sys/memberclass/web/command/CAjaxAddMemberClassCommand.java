package com.jiaorder.sys.memberclass.web.command;

import com.jiaorder.sys.log.LogType;
import com.jiaorder.sys.log.LogUtil;
import com.jiaorder.sys.login.UserHelper;
import com.jiaorder.sys.memberclass.busi.MemberClassBean;
import com.jiaorder.sys.memberclass.vo.MemberClassVO;
import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.StrUtil;
import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import com.pabula.fw.utility.VO;

import javax.servlet.http.HttpServletRequest;

/**
 * 添加用户分类(归属区)command
 */
public class CAjaxAddMemberClassCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) {

        MemberClassBean bean = MemberClassBean.newInstance();

        //接收用户分类相关数据
        int serviceId = UserHelper.getServiceID(request);
        int parentClassId = StrUtil.getNotNullIntValue(request.getParameter("parentClassId"), 0);
        String className = request.getParameter("className");

        //日志记录
        LogUtil.operLog(LogType.MEMEBER, "新增客户归属区", parentClassId + "  name : " + className, request);

        //创建用户分类vo并添加
        MemberClassVO vo = new MemberClassVO();
        vo.setPARENT_CLASS_ID(parentClassId);
        vo.setCLASS_NAME(className);
        vo.setSERVICE_ID(serviceId);
        if (bean.addMemberClass(vo)) {
            return JsonResultUtil.ok();
        }
        return JsonResultUtil.error();
    }

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {

    }
}
