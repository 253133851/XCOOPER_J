package com.jiaorder.sys.memberlevel.web.command;

import com.jiaorder.sys.log.LogType;
import com.jiaorder.sys.log.LogUtil;
import com.jiaorder.sys.login.UserHelper;
import com.jiaorder.sys.memberlevel.busi.MemberLevelBean;
import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.StrUtil;
import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import com.pabula.fw.utility.VO;

import javax.servlet.http.HttpServletRequest;

/**
 * 删除客户级别command
 */
public class CAjaxDelMemberLevelCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) {

        MemberLevelBean bean = MemberLevelBean.newInstance();

        //得到要删除的客户级别的id
        int levelId = StrUtil.getNotNullIntValue(request.getParameter("id"), 0);
        int serviceId = UserHelper.getServiceID(request);

        //日志记录
        LogUtil.operLog(LogType.MEMEBER,"删除客户级别",levelId,request);


        if (levelId > 0) {
            //执行删除
            boolean result = bean.delMemberLevel(serviceId, levelId);
            if (result) {
                return JsonResultUtil.ok();
            }
        } else {
            //未能正确的得到id
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
