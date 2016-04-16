package com.jiaorder.member.notify.web.command;

import com.jiaorder.common.Page;
import com.jiaorder.member.notify.busi.NotifyBean;
import com.jiaorder.member.notify.vo.NotifyVO;
import com.jiaorder.sys.log.LogType;
import com.jiaorder.sys.log.LogUtil;
import com.jiaorder.sys.login.UserHelper;
import com.jiaorder.sys.user.busi.UserBean;
import com.jiaorder.sys.user.vo.UserVO;
import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.StrUtil;
import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.exception.BusinessRuleException;
import com.pabula.fw.exception.DataAccessException;
import com.pabula.fw.exception.RuleException;
import com.pabula.fw.exception.SysException;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import com.pabula.fw.utility.VO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xq on 2016.3.21 16 39.
 */
public class CAjaxGetNotifyNotReadByIdCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) throws ServletException, BusinessRuleException, DataAccessException, SysException {
        List<UserVO> list = new ArrayList<>();
        NotifyBean notifyBean = NotifyBean.newInstance();
        NotifyVO notify = notifyBean.getNotifyByID(serviceId, notifyId).get(0);
        String type = notify.getNOTIFY_SHOW();
        list = UserBean.newInstance().getUserByFilter(serviceId, type);
        //加入page格式
        Page<UserVO> page = new Page<>();
        page.setItems(list);
        //日志记录
        LogUtil.operLog(LogType.OTHER, "获取目标条件下的通知阅读记录", "", request);
        return JsonResultUtil.instance().addData(page).json();
    }

    int serviceId, notifyId, userId;

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) throws RuleException {
        serviceId = UserHelper.getServiceID(request);

        notifyId = StrUtil.getNotNullIntValue(request.getParameter("notifyId"), 0);
        userId = UserHelper.getUID(request);
    }

}
