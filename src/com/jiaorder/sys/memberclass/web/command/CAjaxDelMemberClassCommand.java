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
 * 删除用户分类(归属区)command
 */
public class CAjaxDelMemberClassCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) {

        MemberClassBean bean = MemberClassBean.newInstance();

        //得到要删除的分类的id
        int classId = StrUtil.getNotNullIntValue(request.getParameter("classId"), 0);
        int serviceId = UserHelper.getServiceID(request);

        //日志记录
        LogUtil.operLog(LogType.MEMEBER, "删除客户归属区", classId, request);

        if (classId > 0) {
            //根据返回值判断有无子分类
            boolean result = bean.delMemberClassAndChildsByClassId(serviceId, classId);
            if (result) {
                return JsonResultUtil.ok();
            } else {
                return JsonResultUtil.instance()
                        .addCode(JsonResultUtil.ERROR)
                        .addMsg("该分类下还有子分类，不能删除！")
                        .json();
            }
        } else {
            //未能正确的得到分类id
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
