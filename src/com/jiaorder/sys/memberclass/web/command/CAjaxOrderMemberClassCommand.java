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
 * 交换两个用户分类(归属区)排序command
 */
public class CAjaxOrderMemberClassCommand implements Command {
  @Override public String execute(RequestHelper helper, HttpServletRequest request) {

    //得到要交换的分类id
    int serviceId = UserHelper.getServiceID(request);
    int oneClassId = StrUtil.getNotNullIntValue(request.getParameter("oneClassId"));
    int otherClassId = StrUtil.getNotNullIntValue(request.getParameter("otherClassId"));

    //日志记录
    LogUtil.operLog(LogType.MEMEBER,"改变客户归属区排序",oneClassId+"  &  "+otherClassId,request);

    MemberClassBean bean = MemberClassBean.newInstance();
    boolean result = bean.changeMemberClassOrderNum(serviceId, oneClassId, otherClassId);
    if (result) {
      return JsonResultUtil.ok();
    }
    return JsonResultUtil.error();
  }

  @Override public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {

  }
}
