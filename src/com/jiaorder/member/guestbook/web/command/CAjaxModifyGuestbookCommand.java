package com.jiaorder.member.guestbook.web.command;

import com.jiaorder.member.guestbook.busi.GuestbookBean;
import com.jiaorder.member.guestbook.vo.GuestbookVO;
import com.jiaorder.sys.log.LogType;
import com.jiaorder.sys.log.LogUtil;
import com.jiaorder.sys.login.UserHelper;
import com.pabula.common.util.DateUtil;
import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.StrUtil;
import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.exception.DataAccessException;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import com.pabula.fw.utility.VO;

import javax.servlet.http.HttpServletRequest;

/**
 * 修改标签command
 */
public class CAjaxModifyGuestbookCommand implements Command {

    @Override
    public String execute(RequestHelper helper, HttpServletRequest request) {

        GuestbookBean bean = GuestbookBean.newInstance();

        //日志记录
        LogUtil.operLog(LogType.OTHER, "修改反馈状态", ID, request);

        GuestbookVO vo = null;
        try {
            vo = bean.getGuestbookByID(serviceId, ID);
            vo.setDONE(DONE);
            vo.setIS_DONE(GuestbookVO.HAS_DONE);
            vo.setDONE_TIME(DateUtil.getCurrTime());
            bean.modifyGuestbook(vo);
            return JsonResultUtil.ok();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return JsonResultUtil.error();
    }

    int serviceId, ID;
    String DONE;

    @Override
    public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {
        //接收单位相关数据
        serviceId = UserHelper.getServiceID(request);
        DONE = request.getParameter("DONE");
        ID = StrUtil.getNotNullIntValue(request.getParameter("ID"), 0);
    }
}
